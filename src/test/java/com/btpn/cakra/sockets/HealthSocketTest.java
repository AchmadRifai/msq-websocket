package com.btpn.cakra.sockets;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import java.net.URI;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Slf4j
class HealthSocketTest {
    private static final LinkedBlockingDeque<String> MSGS = new LinkedBlockingDeque<>();

    @TestHTTPResource("/health/cakra")
    URI uri;

    @Test
    void testSocket() throws Exception {
        try (var sesi = ContainerProvider.getWebSocketContainer().connectToServer(Client.class, uri)) {
            Assertions.assertTrue(sesi.isOpen());
            sesi.getAsyncRemote().sendText("Hello world");
            Assertions.assertEquals(">> cakra: Hello world", MSGS.poll(10, TimeUnit.SECONDS));
        }
    }

    @ClientEndpoint
    public static class Client {
        @OnOpen
        void opened() {
            log.info("Health Socket client opened");
        }

        @OnMessage
        void onMessage(String msg) {
            MSGS.add(msg);
        }
    }
}
