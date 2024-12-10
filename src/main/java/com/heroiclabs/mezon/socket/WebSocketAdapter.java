package com.heroiclabs.mezon.socket;
import javax.websocket.*;
import java.io.IOException;
import java.util.function.Consumer;

public interface WebSocketAdapter {


    void setOnClose(Consumer<CloseReason> onClose);

    /**
     * Dispatched when the web socket receives an error.
     */
    void setOnError(Consumer<Throwable> onError);

    /**
     * Dispatched when the web socket receives a normal message.
     */
    void setOnMessage(Consumer<String> onMessage);

    /**
     * Dispatched when the web socket opens.
     */
    void setOnOpen(Consumer<Session> onOpen);

    boolean isOpen();

    void close() throws IOException;

    void connect(String scheme, String host, String port, boolean createStatus, String token) throws IOException, DeploymentException;

    void send(String message) throws IOException;
}