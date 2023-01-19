package com.btpn.cakra.restclient;

import com.btpn.cakra.repositories.JenkinsJobRepository;
import com.btpn.cakra.repositories.JenkinsUrlRepository;
import com.btpn.cakra.utils.BasicAuthUtil;
import io.quarkus.test.junit.QuarkusTest;
import java.net.URI;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Slf4j
class JenkinsClientApiTest {
    @Inject
    JenkinsUrlRepository urlRepository;
    @Inject
    JenkinsJobRepository jobRepository;

    @Test
    void testLogs() {
        jobRepository.streamAll().forEach(job->{
            final var url = urlRepository.findByGroudId(job.getGroupId());
            final var client = RestClientBuilder.newBuilder()
                    .baseUri(URI.create(String.format("%sjob/%s", url.getJenkinsUrl(), job.getJobName())))
                    .build(JenkinsJobApiClient.class);
            final var last = client.job(BasicAuthUtil.basicHeader(url.getJenkinsUname(), url.getJenkinsToken())).getLastBuild();
            log.info("Logs {}", client.logs(last.getNumber(), BasicAuthUtil.basicHeader(url.getJenkinsUname(), url.getJenkinsToken())));
        });
    }
}
