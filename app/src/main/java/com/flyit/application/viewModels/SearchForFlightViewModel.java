package com.flyit.application.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.FlightSearch;
import com.flyit.application.networking.callbacks.AddFlightCallback;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.FlightsRepository;

public class SearchForFlightViewModel extends AndroidViewModel implements DataCallback<FlightSearch>, AddFlightCallback {

    private FlightsRepository flightsRepository;
    private MutableLiveData<FlightSearch> searchedFlight;
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isFlightNumberValid = new MutableLiveData<>();

    public SearchForFlightViewModel(@NonNull Application application) {
        super(application);
        this.flightsRepository = FlightsRepository.getFLightsRepository(application.getApplicationContext());
    }

    public LiveData<FlightSearch> getSearchedFlights() {
        if (searchedFlight == null) {
            this.searchedFlight = new MutableLiveData<FlightSearch>();
        }
        return searchedFlight;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public LiveData<Boolean> getIsFlightNumberValid() {
        return isFlightNumberValid;
    }

    public void addFlight(FlightSearch flightSearch) {
        this.isLoading.setValue(true);
        this.flightsRepository.addFlight(flightSearch, this);
    }

    public void searchFlight(String flightNo) {
        if (flightNo.trim().isEmpty())
        {
            this.isFlightNumberValid.setValue(false);
            this.message.setValue("Please insert flight number");
            return;
        }

        this.isFlightNumberValid.setValue(true);
        this.isLoading.setValue(true);
        this.flightsRepository.searchFlight(flightNo, this);
    }

    @Override
    public void onSuccess(FlightSearch data) {
        this.isLoading.setValue(false);
        this.searchedFlight.setValue(data);
    }

    @Override
    public void onFailure(String msg) {
        this.isLoading.setValue(false);
        this.message.setValue(msg);
    }

    @Override
    public void onAddFlightSuccess() {
        this.message.setValue("Flight was added");
        this.isLoading.setValue(false);
    }

    @Override
    public void onAddFlightFailure(String message) {
        this.message.setValue(message);
        this.isLoading.setValue(false);
    }
}
