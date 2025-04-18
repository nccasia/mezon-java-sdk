package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessageMention {
    // The UNIX time (for gRPC clients) or ISO string (for REST clients) when the message was created.
    private String createTime;
    
    private String id;
    
    private String userId;
    
    private String username;
    
    // Role ID
    private String roleId;
    
    // Role name
    private String rolename;
    
    // Start position
    private Integer s;
    
    // End position
    private Integer e;
    
    // The channel this message belongs to
    private String channelId;
    
    // The mode
    private Integer mode;
    
    // The channel label
    private String channelLabel;
    
    // The message that the user reacted to
    private String messageId;
    
    // Message sender, usually a user ID
    private String senderId;
}
