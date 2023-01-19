package com.btpn.cakra.sockets;

import com.btpn.cakra.repositories.*;
import com.btpn.cakra.utils.JenkinsLogExecutor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/job/{name}")
public class JenkinsLogSocket {
    Map<String, JenkinsLogExecutor> executors = new ConcurrentHashMap<>();

    @Inject
    private JenkinsJobRepository jobRepository;
    @Inject
    private JenkinsUrlRepository urlRepository;

    @OnOpen
    public void opening(Session session, @PathParam("name") String name) {
        if (!executors.containsKey(name)) {
            final var executor = new JenkinsLogExecutor(name, jobRepository, urlRepository);
            executor.login(session);
            executors.put(name, executor);
            new Thread(executor).start();
        } else executors.get(name).login(session);
    }

    @OnClose
    public void closing(Session session, @PathParam("name") String name) {
        final var executor = executors.get(name);
        executor.logout(session);
        if (executor.isDone()) executors.remove(name);
    }
}
