package com.flyit.application.networking.callbacks;

import com.flyit.application.models.ChatroomMessage;

public interface MessageCallback {
    void onReceived(ChatroomMessage chatroomMessage);
}
