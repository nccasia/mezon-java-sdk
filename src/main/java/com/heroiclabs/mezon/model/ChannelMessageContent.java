package com.heroiclabs.mezon.model;

import lombok.Data;
import java.util.List;

@Data
public class ChannelMessageContent {

    /**
     * The textual content of the message.
     * Optional field.
     */
    private String t;

    /**
     * The thread associated with the message content.
     * Represents a reference to a conversation thread.
     */
    private String contentThread;

    /**
     * A list of hashtags included in the message.
     * Each hashtag is represented by a `HashtagOnMessage` object.
     */
    private List<HashtagOnMessage> hg;

    /**
     * A list of emojis present in the message.
     * Each emoji is represented by an `EmojiOnMessage` object.
     */
    private List<EmojiOnMessage> ej;

    /**
     * A list of links included in the message.
     * Each link is represented by a `LinkOnMessage` object.
     */
    private List<LinkOnMessage> lk;

    /**
     * A list of markdown elements within the message.
     * Each markdown element is represented by a `MarkdownOnMessage` object.
     */
    private List<MarkdownOnMessage> mk;

    /**
     * A list of links to voice rooms included in the message.
     * Each voice room link is represented by a `LinkVoiceRoomOnMessage` object.
     */
    private List<LinkVoiceRoomOnMessage> vk;

    /**
     * A list of embedded properties included in the message.
     * Each embed property is represented by an `IEmbedProps` object.
     */
    private List<IEmbedProps> embed;

    /**
     * A list of action row components or additional components in the message.
     * Each component is represented by an `IMessageActionRow` object or a generic type.
     */
    private List<IMessageActionRow> components;
}
