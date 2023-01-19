package com.btpn.cakra.utils;

import com.btpn.cakra.repositories.JenkinsJobRepository;
import com.btpn.cakra.repositories.JenkinsUrlRepository;
import com.btpn.cakra.restclient.JenkinsJobApiClient;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

@Slf4j
public class JenkinsLogExecutor implements Runnable {
    private final List<Session> sessions;
    private final String jobName;
    private final JenkinsJobRepository jobRepository;
    private final JenkinsUrlRepository urlRepository;

    public JenkinsLogExecutor(String jobName, JenkinsJobRepository jobRepository, JenkinsUrlRepository urlRepository) {
        this.jobName = jobName;
        this.jobRepository = jobRepository;
        this.urlRepository = urlRepository;
        sessions = new CopyOnWriteArrayList<>();
    }

    public boolean isDone() {
        return sessions.isEmpty();
    }

    public void login(Session session) {
        sessions.add(session);
    }

    public void logout(Session session) {
        sessions.removeIf(s -> Objects.deepEquals(s, session));
    }

    @Override
    public void run() {
        while (!sessions.isEmpty()) try {
            final var jobEntity = jobRepository.findByJobName(jobName);
            final var url = urlRepository.findByGroudId(jobEntity.getGroupId());
            final var client = RestClientBuilder.newBuilder()
                    .baseUri(URI.create(String.format("%sjob/%s", url.getJenkinsUrl(), jobEntity.getJobName())))
                    .build(JenkinsJobApiClient.class);
            final var last = client.job(BasicAuthUtil.basicHeader(url.getJenkinsUname(), url.getJenkinsToken())).getLastBuild();
            broadcast(client.logs(last.getNumber(), BasicAuthUtil.basicHeader(url.getJenkinsUname(), url.getJenkinsToken())));
        } catch(Exception e) {
            log.info("error {}", e);
        }
    }

    private void broadcast(String logs) throws Exception {
        for (var s : sessions) 
            s.getBasicRemote().sendText(logs);
        Thread.sleep(100);
    }
}
