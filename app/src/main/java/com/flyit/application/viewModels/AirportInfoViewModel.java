package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Airport;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.AirportRepository;

public class AirportInfoViewModel extends AndroidViewModel implements DataCallback<Airport> {
    private AirportRepository airportRepository;
    private MutableLiveData<Airport> airport = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);
    private MutableLiveData<String> message = new MutableLiveData<>();

    public AirportInfoViewModel(@NonNull Application application) {
        super(application);
        this.airportRepository = AirportRepository.getAirportRepository(application.getApplicationContext());
    }

    public LiveData<Airport> getAirport() {
        return airport;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void getAirportByIata(String iata) {
        this.isLoading.setValue(true);
        this.airportRepository.getGetAirportByIata(iata, this);
    }

    @Override
    public void onSuccess(Airport data) {
        this.isLoading.setValue(false);
        this.airport.setValue(data);
    }

    @Override
    public void onFailure(String message) {
        this.isLoading.setValue(false);
        this.message.setValue(message);
        this.airport.setValue(null);
    }
}
