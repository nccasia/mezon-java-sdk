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
import com.heroiclabs.mezon.model.ApiMessageAttachment;
import com.heroiclabs.mezon.model.ApiMessageMention;
import com.heroiclabs.mezon.model.ApiMessageRef;
import com.heroiclabs.mezon.model.GsonDateDeserializer;
import com.heroiclabs.mezon.session.Session;
import com.heroiclabs.nakama.ChannelMessageAck;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

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

    // The interval at which to send Ping frames to the server.
    public static final int DEFAULT_PING_MS = 5000;

    static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
            .registerTypeAdapter(Date.class, new GsonDateDeserializer())
            .create();

    private final String host;
    private final int port;
    private final boolean ssl;
    private final boolean verbose;

    private ExecutorService listenerThreadExec;
    private WebSocket socket;
    WebSocketAdapter adapter;
    public MezonSocket(String host, String port, boolean useSSL, boolean verbose, WebSocketAdapter adapter) {
        this.host = host;
        this.port = Integer.parseInt(port);
        this.ssl = useSSL;
        this.verbose= verbose;
        this.adapter = adapter;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public CompletableFuture<Session> connect(Session session, boolean createStatus, Integer connectTimeoutMs) {
        return null;
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

    // The connect, read and write timeout for new connections.






}
