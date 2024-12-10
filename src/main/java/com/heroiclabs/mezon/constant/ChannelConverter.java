package com.heroiclabs.mezon.constant;

public class ChannelConverter {

    public static Object convertChanneltypeToChannelMode(Object channelType) {
        int channelTypeInt = (channelType instanceof String) ? Integer.parseInt((String) channelType) : (int) channelType;

        switch (channelTypeInt) {
            case 3:  // ChannelType.CHANNEL_TYPE_DM
                return Events.ChannelStreamMode.STREAM_MODE_DM;
            case 2:  // ChannelType.CHANNEL_TYPE_GROUP
                return Events.ChannelStreamMode.STREAM_MODE_GROUP;
            case 1:  // ChannelType.CHANNEL_TYPE_TEXT
                return Events.ChannelStreamMode.STREAM_MODE_CHANNEL;
            default:
                return false;
        }
    }
}
