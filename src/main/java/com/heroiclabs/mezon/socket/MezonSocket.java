package com.heroiclabs.mezon.socket;/*
 * Copyright 2020 The Nakama Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.google.common.io.BaseEncoding;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.*;

import com.google.type.Date;
import com.heroiclabs.mezon.model.*;
import com.heroiclabs.mezon.session.Session;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * WebSocket implementation of Socket interface.
 */
@Slf4j
public class MezonSocket implements SocketClient {
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    public static final int DefaultHeartbeatTimeoutMs = 10000;
    public static final int DefaultSendTimeoutMs = 10000;
    public static final int DefaultConnectTimeoutMs = 30000;

    // The interval at which to send Ping frames to the server.
    public static final int DEFAULT_PING_MS = 5000;

    static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
            .registerTypeAdapter(Date.class, new GsonDateDeserializer())
            .create();
    private final int heartbeatTimeoutMs = DefaultHeartbeatTimeoutMs;
    private final String host;
    private final String port;
    private final boolean ssl;
    private final boolean verbose;
    private Session session;
    private WebSocket socket;
    WebSocketAdapter adapter;
    public MezonSocket(String host, String port, boolean useSSL, boolean verbose, WebSocketAdapter adapter) {
        this.host = host;
        this.port = port;
        this.ssl = useSSL;
        this.verbose= verbose;
        this.adapter = adapter;
    }

    @SneakyThrows
    @Override
    public void close() throws IOException {
        adapter.close();
    }

    @Override
    public boolean isOpen() {
        return adapter.isOpen();
    }

    public CompletableFuture<Session> connect(Session session, boolean createStatus, Integer connectTimeoutMs) {
        CompletableFuture<Session> future = new CompletableFuture<>();
        String scheme = (this.ssl) ? "wss://" : "ws://";

        try {
            // Kết nối với adapter
            adapter.connect(scheme, this.host, this.port, createStatus, session.getAuthToken());

            this.adapter.setOnOpen((evt) -> {
                if (this.verbose) {
                    System.out.println("Connection opened: " + evt);
                }
                this.pingPong();
                future.complete(session);
            });

            this.adapter.setOnClose((closeReason) -> {
                System.out.println("Connection closed: " + closeReason.getReasonPhrase());
                System.out.println("Close code: " + closeReason.getCloseCode());
            });

            // Xử lý sự kiện khi có lỗi xảy ra
            this.adapter.setOnError((throwable) -> {
                System.err.println("Connection error: " + throwable.getMessage());
                future.completeExceptionally(throwable);
                try {
                    this.adapter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            this.adapter.setOnMessage((message) -> {
                if (this.verbose) {
                    System.out.println("Response: " + message.toString());
                }
            });

            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(connectTimeoutMs);
                    if (!future.isDone()) {
                        future.completeExceptionally(new TimeoutException("The socket timed out when trying to connect."));
                        this.adapter.close();
                    }
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                }
            });

        } catch (IOException | DeploymentException e) {
            future.completeExceptionally(e);
        }

        return future;
    }

    public void pingPong() {
        if (!this.adapter.isOpen()) {
            return;
        }

        sendPingWithTimeout().exceptionally(ex -> {
            if (this.adapter.isOpen()) {
                if (this.verbose) {
                    System.err.println("Server unreachable from heartbeat.");
                }
                this.onHeartbeatTimeout();
                try {
                    this.adapter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        });
    }

    private CompletableFuture<Void> sendPingWithTimeout() {
        CompletableFuture<Void> future = new CompletableFuture<>();

        // Gửi ping với timeout
        this.sendPing()
                .orTimeout(this.heartbeatTimeoutMs, TimeUnit.MILLISECONDS)
                .thenAccept(response -> future.complete(null))
                .exceptionally(ex -> {
                    future.completeExceptionally(ex);
                    return null;
                });

        return future;
    }

    private CompletableFuture<Void> sendPing() {
        return CompletableFuture.runAsync(() -> {
            try {
                adapter.send("{\"ping\": {}}");
            } catch (Exception e) {
                throw new RuntimeException("Failed to send ping", e);
            }
        });
    }

    private void onHeartbeatTimeout() {
        System.err.println("Heartbeat timeout occurred.");
    }

    @Override
    public CompletableFuture<ChannelMessageAck> writeChatMessage(String clanId, String channelId, int mode, boolean isPublic, Object content, List<ApiMessageMention> mentions, List<ApiMessageAttachment> attachments, List<ApiMessageRef> references) {
        return null;
    }

    @Override
    public CompletableFuture<Session> joinClanChat(String clanId) {
        return null;
    }

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public JsonElement serialize(final byte[] src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(BaseEncoding.base64().encode(src));
        }

        @Override
        public byte[] deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return BaseEncoding.base64().decode(jsonElement.getAsString());
        }
    }

}
