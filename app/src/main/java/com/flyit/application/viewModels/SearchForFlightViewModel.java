package com.flyit.application.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Flight;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.FlightsRepository;

public class SearchForFlightViewModel extends AndroidViewModel implements DataCallback {

    private FlightsRepository flightsRepository;
    private MutableLiveData<Flight> searchedFlight;

    public SearchForFlightViewModel(@NonNull Application application) {
        super(application);
        this.flightsRepository = FlightsRepository.getFLightsRepository(application.getApplicationContext());
    }

    public void searchFlight(String flightNo){
        this.flightsRepository.searchFlight(flightNo,this);
        Log.d("SearchFlow", "flightNumber" + flightNo);
    }

    public LiveData<Flight> getSearchedFlights() {
        if(searchedFlight == null){
            this.searchedFlight = new MutableLiveData<Flight>();
        }
        return searchedFlight;
    }


    @Override
    public <T> void onSuccess(T data) {
        this.searchedFlight.setValue((Flight) data);
    }

    @Override
    public void onFailure(String msg) {

    }
}
