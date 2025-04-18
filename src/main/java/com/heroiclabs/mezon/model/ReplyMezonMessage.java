package com.heroiclabs.mezon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyMezonMessage {

    private String clanId;
    private String channelId;
    private boolean isPublic;
    private int mode;
    private ChannelMessageContent msg;
    private List<ApiMessageMention> mentions;
    private List<ApiMessageAttachment> attachments;
    private List<ApiMessageRef> ref;

}
