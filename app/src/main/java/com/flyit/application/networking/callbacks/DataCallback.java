package com.flyit.application.networking.callbacks;

public interface DataCallback {
    <T> void onSuccess(T data);
    void onFailure(String msg);
}
