package com.heroiclabs.mezon.socket;

import com.heroiclabs.mezon.model.*;
import com.heroiclabs.mezon.session.Session;


import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SocketClient {
    void close();

    boolean isOpen();

    CompletableFuture<Session> connect(Session session, boolean createStatus,
                                       Integer connectTimeoutMs);


    CompletableFuture<ChannelMessageAck> writeChatMessage(String clanId, String channelId, int mode,
                                                          boolean isPublic, Object content,
                                                          List<ApiMessageMention> mentions,
                                                          List<ApiMessageAttachment> attachments,
                                                          List<ApiMessageRef> references);

    CompletableFuture<Session> joinClanChat(String clanId);
}
