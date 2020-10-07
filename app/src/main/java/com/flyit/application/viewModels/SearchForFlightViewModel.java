package com.flyit.application.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Flight;
import com.flyit.application.models.FlightSearch;
import com.flyit.application.networking.callbacks.AddFlightCallback;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.FlightsRepository;

import java.util.Date;

public class SearchForFlightViewModel extends AndroidViewModel implements DataCallback<FlightSearch>, AddFlightCallback {

    private FlightsRepository flightsRepository;
    private MutableLiveData<FlightSearch> searchedFlight;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public SearchForFlightViewModel(@NonNull Application application) {
        super(application);
        this.flightsRepository = FlightsRepository.getFLightsRepository(application.getApplicationContext());
    }

    public void searchFlight(String flightNo){
        this.flightsRepository.searchFlight(flightNo,this);
        Log.d("SearchFlow", "flightNumber" + flightNo);
    }

    public LiveData<FlightSearch> getSearchedFlights() {
        if(searchedFlight == null){
            this.searchedFlight = new MutableLiveData<FlightSearch>();
        }
        return searchedFlight;
    }

    public void addFlight(String flightNo){
        this.isLoading.setValue(true);
        this.flightsRepository.addFlight(new FlightSearch(flightNo, "2020-10-07T00:00:00+00:00"), this);
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Override
    public void onSuccess(FlightSearch data) {
        this.searchedFlight.setValue(data);
    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onAddFlightSuccess() {
        Log.d("AddFlightFlow", "Success");
        this.isLoading.setValue(false);

    }

    @Override
    public void onAddFlightFailure(String message) {
        Log.d("AddFlightFlow", "Failure" + message);
        this.isLoading.setValue(false);
    }
}
