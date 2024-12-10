package com.heroiclabs.mezon;

import com.heroiclabs.mezon.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Client {

    /**
     * Authenticate the client and return a token.
     *
     * @return A CompletableFuture that resolves to the authentication token as a String.
     */
    CompletableFuture<String> authenticate();

    /**
     * Send a message to a channel.
     *
     * @param clanId      The ID of the clan.
     * @param channelId   The ID of the channel.
     * @param mode        The mode of the channel.
     * @param isPublic    Whether the message is public.
     * @param msg         The content of the message.
     * @param mentions    Optional: A list of message mentions.
     * @param attachments Optional: A list of message attachments.
     * @param refs        Optional: A list of message references.
     * @return A CompletableFuture that resolves to a ChannelMessageAck object.
     */
    CompletableFuture<ChannelMessageAck> sendMessage(
            String clanId,
            String channelId,
            int mode,
            boolean isPublic,
            ChannelMessageContent msg,
            List<ApiMessageMention> mentions,
            List<ApiMessageAttachment> attachments,
            List<ApiMessageRef> refs
    );

    /**
     * Register an event listener for a specific event.
     *
     * @param event The name of the event.
     * @param func  The callback function to handle the event.
     */
    void on(String event, EventCallback func);

    /**
     * Remove an event listener for a specific event.
     *
     * @param event The name of the event.
     * @param func  The callback function to remove.
     */
    void remove(String event, EventCallback func);

    /**
     * A functional interface for event callbacks.
     */
    @FunctionalInterface
    interface EventCallback {
        void handle(Object... args);
    }
}
