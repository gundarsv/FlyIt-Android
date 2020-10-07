package com.flyit.application.networking.callbacks;

public interface AddFlightCallback {
    void onAddFlightSuccess();
    void onAddFlightFailure(String message);
}
