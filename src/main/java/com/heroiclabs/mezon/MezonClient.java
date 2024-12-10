package com.heroiclabs.mezon;

import com.heroiclabs.mezon.model.*;
import com.heroiclabs.mezon.api.MezonApi;
import com.heroiclabs.mezon.constant.Constant;
import com.heroiclabs.mezon.session.DefaultSession;
import com.heroiclabs.mezon.session.Session;
import com.heroiclabs.mezon.socket.MezonSocket;
import com.heroiclabs.mezon.socket.WebSocketAdapter;
import com.heroiclabs.mezon.socket.WebSocketAdapterText;
import com.heroiclabs.mezon.socket.SocketClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MezonClient implements Client {
    private final String apiKey;
    private final String host;
    private final String port;
    private final boolean useSSL;
    private final int timeout;
    private final boolean autoRefreshSession;
    private final MezonApi apiClient;
    private final SocketClient socket;
    private Session session;
    private boolean isHardDisconnect;
    private String scheme;
    public MezonClient(String apiKey, String host, int port, boolean useSSL, int timeout) {
        this.apiKey = Constant.DEFAULT_API_KEY;
        this.host = Constant.DEFAULT_HOST;
        this.port = Constant.DEFAULT_PORT;
        this.useSSL = Constant.DEFAULT_SSL;
        this.timeout = Constant.DEFAULT_TIMEOUT_MS;
        this.autoRefreshSession = true;
        String scheme = useSSL ? "https://" : "http://";
        apiKey = Constant.DEFAULT_API_KEY;
        String basePath = scheme + host + ":" + port;
        this.apiClient = new MezonApi(apiKey, basePath, timeout);
        this.socket = createSocket(useSSL, false, new WebSocketAdapterText());
    }

    /** Authenticate a user with an ID against the server. */
    @Override
    public CompletableFuture<String> authenticate() {
        return apiClient.mezonAuthenticate(apiClient.getApiKey(), "", new ApiAuthenticateRequest(new ApiAccountApp(this.apiKey)))
                .thenCompose(apiSession -> {
                    Session sockSession = new DefaultSession(apiSession.getToken(), apiSession.getRefreshToken(), apiSession.getCreated());
                    return socket.connect(sockSession, true, timeout)
                            .thenCompose(connectedSession -> {
                                if (connectedSession == null) {
                                    return CompletableFuture.completedFuture("error authenticate");
                                }
                                this.session = connectedSession;
                                this.isHardDisconnect = false;
                                try {
                                    return connectSocket().thenApply(v -> "connect successful");
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                });
    }

    /** Send a message to a channel. */
    @Override
    public CompletableFuture<ChannelMessageAck> sendMessage(
            String clanId,
            String channelId,
            int mode,
            boolean isPublic,
            ChannelMessageContent msg,
            List<ApiMessageMention> mentions,
            List<ApiMessageAttachment> attachments,
            List<ApiMessageRef> refs) {
        return socket.writeChatMessage(clanId, channelId, mode, isPublic, msg, mentions, attachments, refs);
    }

    @Override
    public void on(String event, EventCallback func) {

    }

    @Override
    public void remove(String event, EventCallback func) {

    }

    /** Connect the socket and join necessary channels. */
    private CompletableFuture<Void> connectSocket() throws Exception {
        return apiClient.listClanDescs(session.getAuthToken()).thenCompose(clans -> {
            CompletableFuture<?>[] joinFutures = clans.getClandesc().stream()
                    .map(clan -> socket.joinClanChat(clan.getClanId()))
                    .toArray(CompletableFuture[]::new);

            return CompletableFuture.allOf(joinFutures);
        });
    }

    /** Create a socket with the client's configuration. */
    private SocketClient createSocket(boolean useSSL, boolean verbose, WebSocketAdapter adapter) {
        return new MezonSocket(this.host, this.port, useSSL, verbose, adapter);
    }

    /** Close the socket connection. */
    public void closeSocket() {
        this.isHardDisconnect = true;
        socket.close();
    }

    // Implement other methods as needed...
}
