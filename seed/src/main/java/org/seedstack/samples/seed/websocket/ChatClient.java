package org.seedstack.samples.seed.websocket;

import org.seedstack.seed.web.internal.websocket.SeedClientEndpointConfigurator;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.function.Consumer;

@ClientEndpoint(configurator = SeedClientEndpointConfigurator.class)
public class ChatClient {
    private Session session;
    private Consumer<String> handler;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void processMessage(String message) {
        if (handler != null) {
            handler.accept(message);
        }
    }

    public void setHandler(Consumer<String> handler) {
        this.handler = handler;
    }

    public void sendMessage(String message) {
        if (session == null) {
            throw new IllegalStateException("No active session");
        }

        session.getAsyncRemote().sendText(message);
    }
}