package com.btpn.cakra.sockets;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@ServerEndpoint("/health/{name}")
@Slf4j
public class HealthSocket {
    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void opening(Session session, @PathParam("name") String name) {
        sessions.put(name, session);
    }

    @OnClose
    public void closing(Session session, @PathParam("name") String name) {
        sessions.remove(name);
        broadcast(String.format("User %s left", name));
    }

    @OnMessage
    public void messaging(String msg, @PathParam("name") String name) {
        broadcast(String.format(">> %s: %s", name, msg));
    }

    private void broadcast(String msg) {
        sessions.values()
                .forEach(s->s.getAsyncRemote().sendText(msg, r->{
                    if (Objects.nonNull(r.getException())) 
                        log.info("Error : ", r.getException());
                }));
    }
}
