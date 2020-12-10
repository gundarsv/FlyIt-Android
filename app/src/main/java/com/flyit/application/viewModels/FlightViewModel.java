package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Flight;
import com.flyit.application.models.Resource;
import com.flyit.application.models.Tuple;
import com.flyit.application.networking.callbacks.DeleteFlightCallback;
import com.flyit.application.networking.callbacks.UpdateFlightCallback;
import com.flyit.application.repositories.FlightsRepository;

import java.util.ArrayList;

public class FlightViewModel extends AndroidViewModel implements UpdateFlightCallback, DeleteFlightCallback {
    private FlightsRepository flightsRepository;
    private MutableLiveData<Resource<ArrayList<Flight>>> flight;
    public ArrayList<Flight> flightArrayList;

    private MutableLiveData<Tuple<String, Flight>> updateData = new MutableLiveData<>();

    public FlightViewModel(@NonNull Application application) {
        super(application);
        this.flightsRepository = FlightsRepository.getFLightsRepository(application.getApplicationContext());
    }

    public LiveData<Resource<ArrayList<Flight>>> getFlight() {
        if (flight == null) {
            this.flight = flightsRepository.getFlights();
        }
        return this.flight;
    }


    public void deleteFlight(int id) {
        flightsRepository.deleteFlight(id, this);
    }


    public void refreshFlight(int id) {
        flightsRepository.getFlightById(id, this);
    }

    public LiveData<Tuple<String, Flight>> getUpdateData() {
        return updateData;
    }


    @Override
    public void onUpdateFlightSuccess(Flight flight) {
        updateData.setValue(new Tuple<>("update", flight));
    }
    
    @Override
    public void onUpdateFlightFailure(String message) {

    }

    @Override
    public void onDeleteFlightSuccess(Flight flight) {
        updateData.setValue(new Tuple<>("delete", flight));
    }

    @Override
    public void onDeleteFlightFailure(String message) {

    }
}
