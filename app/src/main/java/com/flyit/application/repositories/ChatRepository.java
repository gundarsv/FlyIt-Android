package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.flyit.application.fragments.utils.MessageUtils;
import com.flyit.application.models.Chatroom;
import com.flyit.application.models.ChatroomMessage;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.ConnectionCallback;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.networking.callbacks.MessageCallback;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;

import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRepository {
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private HubConnection hubConnection;

    private static ChatRepository chatRepository = null;

    private ChatRepository(Context context) {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static ChatRepository getChatRepository(Context context) {
        if (chatRepository == null) {
            chatRepository = new ChatRepository(context);
        }

        return chatRepository;
    }

    public void startConnection(final ConnectionCallback connectionCallback, final MessageCallback messageCallback, final DataCallback dataCallback) {
        this.hubConnection = HubConnectionBuilder
                .create("https://flyit.azurewebsites.net/chathub")
                .withHeader("Authorization", "Bearer " + mPrefs.getString("accessToken", ""))
                .build();

        hubConnection.on("receiveChatMessage", (message) -> {
            messageCallback.onReceived(message);
        }, ChatroomMessage.class);

        hubConnection.on("receiveMessage", (message) -> {
            dataCallback.onFailure(message);
        }, String.class);

        Completable completable = this.hubConnection.start();

        completable.subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onError(Throwable e) {
                dataCallback.onFailure(e.getMessage());
            }

            @Override
            public void onComplete() {
                connectionCallback.onConnected();
            }
        });
    }

    public void getChatroomById(String id, final DataCallback<Chatroom> callback) {
        flyItApi.getChatroomById(id).enqueue(new Callback<Chatroom>() {
            @Override
            public void onResponse(Call<Chatroom> call, Response<Chatroom> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Chatroom> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void leaveChatroomById(String id, final DataCallback<Chatroom> callback) {
        flyItApi.leaveChatroom(id).enqueue(new Callback<Chatroom>() {
            @Override
            public void onResponse(Call<Chatroom> call, Response<Chatroom> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Chatroom> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void joinChatroomById(String id, final DataCallback<Chatroom> callback) {
        flyItApi.joinChatroom(id).enqueue(new Callback<Chatroom>() {
            @Override
            public void onResponse(Call<Chatroom> call, Response<Chatroom> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Chatroom> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void sendChatMessage(String message, int chatroomId) {
        if (hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
            hubConnection.send("SendMessage", chatroomId, message);
        }
    }

    public void startChatroomConnection(int chatroomId) {
        if (hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
            hubConnection.send("StartChat", chatroomId);
        }
    }

    public void endChatroomConnection(int chatroomId) {
        if (hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
            hubConnection.send("EndChat", chatroomId);
            if (hubConnection.getConnectionState() == HubConnectionState.CONNECTED) {
                hubConnection.close();
            }
        }
    }
}
