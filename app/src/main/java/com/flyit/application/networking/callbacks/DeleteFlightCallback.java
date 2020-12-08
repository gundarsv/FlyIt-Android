package com.flyit.application.networking.callbacks;

import com.flyit.application.models.Flight;

public interface DeleteFlightCallback {
    void onDeleteFlightSuccess(Flight flight);
    void onDeleteFlightFailure(String message);
}
