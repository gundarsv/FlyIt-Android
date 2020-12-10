package com.flyit.application.networking.callbacks;

import com.flyit.application.models.Flight;

public interface UpdateFlightCallback {
    void onUpdateFlightSuccess(Flight flight);
    void onUpdateFlightFailure(String message);

}
