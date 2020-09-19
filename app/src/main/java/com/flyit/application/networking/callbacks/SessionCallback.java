package com.flyit.application.networking.callbacks;

public interface SessionCallback {
    void onSuccess();
    void onFailure(String msg);
}
