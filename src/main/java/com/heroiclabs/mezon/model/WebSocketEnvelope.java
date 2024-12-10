package com.heroiclabs.mezon.model;


import lombok.Data;

/**
 * Realtime message envelope.
 */
@Data
public class WebSocketEnvelope {
    private String cid;
    private WebSocketError error;
    private RpcMessage rpc;
    private Channel channel;
    private ChannelJoinMessage channelJoin;
    private ChannelLeaveMessage channelLeave;
    private ChannelMessage channelMessage;
    private ChannelMessageAck channelMessageAck;
    private ChannelSendMessage channelMessageSend;
    private ChannelUpdateMessage channelMessageUpdate;
    private ChannelRemoveMessage channelRemoveMessage;
    private ChannelPresenceEvent channelPresenceEvent;
    private NotificationList notifications;
    private Status status;
    private StatusFollowMessage statusFollow;
    private StatusPresenceEvent statusPresenceEvent;
    private StatusUnfollowMessage statusUnfollow;
    private StatusUpdateMessage statusUpdate;
    private StreamData streamData;
    private StreamPresenceEvent streamPresenceEvent;

}