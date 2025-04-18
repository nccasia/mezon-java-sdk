///*
// * Copyright 2020 The Nakama Authors
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.heroiclabs.mezon.socket;
//
//
//import com.heroiclabs.mezon.model.*;
//import com.heroiclabs.mezon.model.Error;
//
///**
// * A listener for receiving {@code Client} events.
// */
//public interface SocketListener {
//    /**
//     * Called when the client socket disconnects.
//     *
//     * @param t Throwable t is set if an error caused the disconnect.
//     */
//    void onDisconnect(final Throwable t);
//
//    /**
//     * Called when the client receives an error.
//     *
//     * @param error The {@code Error} received.
//     */
//    void onError(final Error error);
//
//    /**
//     * Called when a new topic message has been received.
//     *
//     * @param message The {@code ChannelMessage} received.
//     */
//    void onChannelMessage(final ChannelMessageAck message);
//
//    /**
//     * Called when a new topic presence update has been received.
//     *
//     * @param presence The {@code ChannelPresenceEvent} received.
//     */
//
//
//    /**
//     * Called when the client receives stream presence updates.
//     *
//     * @param presence Updated {@code StreamPresenceEvent} presence.
//     */
//    void onStreamPresence(final StreamPresenceEvent presence);
//
//    /**
//     * Called when the client receives stream data.
//     *
//     * @param data Stream {@code StreamData} data received.
//     */
//    void onStreamData(final StreamData data);
//
//
//}
