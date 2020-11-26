package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Chatroom;
import com.flyit.application.models.ChatroomMessage;
import com.flyit.application.networking.callbacks.ConnectionCallback;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.networking.callbacks.MessageCallback;
import com.flyit.application.repositories.ChatRepository;

public class ChatViewModel extends AndroidViewModel implements DataCallback<Chatroom>, ConnectionCallback, MessageCallback {
    private ChatRepository chatRepository;
    private MutableLiveData<Chatroom> chatroom = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);
    private MutableLiveData<ChatroomMessage> incomingMessage;
    private MutableLiveData<Boolean> isFinished = new MutableLiveData<>(false);

    public ChatViewModel(@NonNull Application application) {
        super(application);
        this.chatRepository = ChatRepository.getChatRepository(application.getApplicationContext());
    }

    public LiveData<Chatroom> getChatroom() {
        return this.chatroom;
    }

    public LiveData<String> getMessage() {
        return this.message;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }

    public LiveData<Boolean> getIsFinished() {
        return this.isFinished;
    }

    public MutableLiveData<ChatroomMessage> getIncomingMessage() {
        if (this.incomingMessage == null)
        {
            this.incomingMessage = new MutableLiveData<>();
        }

        return this.incomingMessage;
    }

    public void startConnection() {
        this.isLoading.setValue(true);
        this.chatRepository.startConnection(this, this, this);
    }

    public void getChatroomById(String id) {
        this.isLoading.setValue(true);
        chatRepository.getChatroomById(id, this);
    }

    public void joinChatroomById(String id) {
        this.isLoading.setValue(true);
        chatRepository.joinChatroomById(id, this);
    }

    public void leaveChatroomById(String id) {
        this.isLoading.setValue(true);
        chatRepository.leaveChatroomById(id, this);
    }

    public void startChatConnection(int chatroomId) {
        this.isLoading.setValue(true);
        this.chatRepository.startChatroomConnection(chatroomId);
    }

    public void endChatConnection(int chatroomId) {
        this.isLoading.setValue(true);
        this.chatRepository.endChatroomConnection(chatroomId);
    }

    public void sendMessage(int chatroomId, String message) {
        this.chatRepository.sendChatMessage(message, chatroomId);
    }

    @Override
    public void onSuccess(Chatroom data) {
        this.isLoading.postValue(false);
        this.chatroom.setValue(data);
    }

    @Override
    public void onFailure(String message) {
        this.isLoading.setValue(false);
        this.message.setValue(message);
    }

    @Override
    public void onConnected() {
        this.isLoading.postValue(false);
        this.isFinished.postValue(true);
    }

    @Override
    public void onReceived(ChatroomMessage chatroomMessage) {
        this.incomingMessage.postValue(chatroomMessage);
    }
}
