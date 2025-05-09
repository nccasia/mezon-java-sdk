/*
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

package com.heroiclabs.mezon.model;

import lombok.Data;

import java.util.Date;

/**
 * A message sent on a channel.
 *
 */
// This message type is only used for GSON, and not exposed to the Client interface.
@Data
class ChannelMessage {
    private String channelId;
    private String messageId;
    private int code;
    private String senderId;
    private String username;
    private String content;
    private Date createTime;
    private Date updateTime;
    private boolean persistent;
    private String roomName;
    private String groupId;
    private String userIdOne;
    private String userIdTwo;
}

