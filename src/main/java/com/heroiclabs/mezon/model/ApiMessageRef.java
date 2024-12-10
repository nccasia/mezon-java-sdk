package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessageRef {
    private String messageId;
    private String messageRefId;
    private Integer refType;
    private String messageSenderId;
    
    // Original message sender username
    private String messageSenderUsername;
    
    // Original message sender avatar
    private String messageSenderAvatar;
    
    // Original sender clan nickname
    private String messageSenderClanNick;
    
    // Original sender display name
    private String messageSenderDisplayName;
    
    private String content;
    
    private boolean hasAttachment;
    
    /** The channel this message belongs to. */
    private String channelId;
    
    /** The mode. */
    private int mode;
    
    /** The channel label. */
    private String channelLabel;
}
