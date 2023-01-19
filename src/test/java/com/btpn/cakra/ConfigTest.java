package com.btpn.cakra;

import com.btpn.cakra.models.*;
import com.btpn.cakra.repositories.*;
import com.btpn.cakra.restclient.*;
import com.btpn.cakra.utils.BasicAuthUtil;
import com.mongodb.client.MongoClient;
import io.quarkus.test.junit.QuarkusTest;
import java.net.URI;
import java.util.Objects;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class ConfigTest {
    @Inject
    MongoClient client;
    @ConfigProperty(name = "quarkus.mongodb.database")
    String dbName;
    @Inject
    JenkinsUrlRepository urlRepository;
    @Inject
    JenkinsJobRepository jobRepository;

    @Test
    void testDb() {
        Assertions.assertNotNull(client.getDatabase(dbName));
    }

    @Test
    void testJenkinsJob() {
        Assertions.assertNotNull(client.getDatabase(dbName).getCollection("jenkins_job"));
    }

    @Test
    void testUrl() {
        Assertions.assertNotNull(client.getDatabase(dbName).getCollection("jenkins_url"));
    }

    @Test
    void insertDataUrl(){
        final var url = JenkinsUrl.builder()
                .groupId(1L)
                .jenkins(true)
                .jenkinsUrl("http://localhost:2101/")
                .jenkinsUname("admin")
                .jenkinsToken("01e00873f078435f8aeb2a31602bd3af")
                .name("local")
                .build();
        if (!urlRepository.streamAll().anyMatch(j->Objects.deepEquals(j.getGroupId(), url.getGroupId()) &&
                Objects.deepEquals(j.getJenkins(), url.getJenkins()) &&
                Objects.deepEquals(j.getJenkinsUrl(), url.getJenkinsUrl()) &&
                Objects.deepEquals(j.getJenkinsUname(), url.getJenkinsUname()) &&
                Objects.deepEquals(j.getJenkinsToken(), url.getJenkinsToken()) &&
                Objects.deepEquals(j.getName(), url.getName()))) 
            urlRepository.persist(url);
        Assertions.assertFalse(urlRepository.listAll().isEmpty());
    }

    @Test
    void insertDataJob() {
        jobRepository.deleteAll();
        urlRepository.streamAll().forEach(url->{
            final var jenkinsClient = RestClientBuilder.newBuilder().baseUri(URI.create(url.getJenkinsUrl())).build(JenkinsApiClient.class);
            final var jenkins = jenkinsClient.json(BasicAuthUtil.basicHeader(url.getJenkinsUname(), url.getJenkinsToken()));
            jobRepository.persist(jenkins.getJobs().stream().map(job->{
                final var jobClient = RestClientBuilder.newBuilder().baseUri(URI.create(job.getUrl())).build(JenkinsJobApiClient.class);
                final var jobRes = jobClient.job(BasicAuthUtil.basicHeader(url.getJenkinsUname(), url.getJenkinsToken()));
                return JenkinsJob.builder()
                        .jobStage(JobStage.builder()
                                .description(jobRes.getDescription())
                                .build())
                        .groupId(url.getGroupId())
                        .groupName(url.getName())
                        .jobName(jobRes.getName())
                        .jobStatus(JobStatus.builder()
                                .className(jobRes.get_class())
                                .color(jobRes.getColor())
                                .name(jobRes.getName())
                                .lastSuccessfulBuild(LastSuccessfulBuild.builder()
                                        .className(jobRes.get_class())
                                        .displayName(jobRes.getDisplayName())
                                        .build())
                                .build())
                        .build();
            }));
        });
        Assertions.assertFalse(jobRepository.listAll().isEmpty());
    }
}
