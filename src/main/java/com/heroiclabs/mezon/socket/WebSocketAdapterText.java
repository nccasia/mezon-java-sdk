package com.heroiclabs.mezon.socket;


import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.function.Consumer;

public class WebSocketAdapterText implements WebSocketAdapter {

    private Session session;
    private Consumer<CloseReason> onClose;
    private Consumer<Throwable> onError;
    private Consumer<String> onMessage;
    private Consumer<Session> onOpen;

    @Override
    public void setOnClose(Consumer<CloseReason> onClose) {
        this.onClose = onClose;
    }

    @Override
    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    @Override
    public void setOnMessage(Consumer<String> onMessage) {
        this.onMessage = onMessage;
    }

    @Override
    public void setOnOpen(Consumer<Session> onOpen) {
        this.onOpen = onOpen;
    }

    @Override
    public boolean isOpen() {
        return session != null && session.isOpen();
    }

    @Override
    public void close() throws IOException {
        if (session != null) {
            session.close();
            session = null;
        }
    }

    @Override
    public void connect(String scheme, String host, String port, boolean createStatus, String token) throws IOException, DeploymentException {
        String uri = String.format("%s://%s:%s/ws?lang=en&status=%b&token=%s", scheme, host, port, createStatus, token);
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(new Endpoint() {
            @Override
            public void onOpen(Session session, EndpointConfig config) {
                WebSocketAdapterText.this.session = session;
                if (onOpen != null) onOpen.accept(session);
            }

            @Override
            public void onClose(Session session, CloseReason closeReason) {
                if (onClose != null) onClose.accept(closeReason);
            }

            @Override
            public void onError(Session session, Throwable thr) {
                if (onError != null) onError.accept(thr);
            }

        }, URI.create(uri));
    }

    @Override
    public void send(String message) throws IOException {
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(message);
        }
    }
}