package com.heroiclabs.mezon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessageAttachment {
    private String filename;
    private String filetype;
    private Integer height;
    private Integer size;
    private String url;
    private Integer width;
    
    /** The channel this message belongs to. */
    private String channelId;
    
    /** The mode. */
    private Integer mode;
    
    /** The channel label. */
    private String channelLabel;
    
    /** The message that user react to. */
    private String messageId;
    
    /** Message sender, usually a user ID. */
    private String senderId;
}
