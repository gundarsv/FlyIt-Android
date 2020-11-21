package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Airport;
import com.flyit.application.models.Weather;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.AirportRepository;
import com.flyit.application.repositories.WeatherRepository;

public class AirportInfoViewModel extends AndroidViewModel implements DataCallback {
    private AirportRepository airportRepository;
    private WeatherRepository weatherRepository;
    private MutableLiveData<Weather> weather = new MutableLiveData<>();
    private MutableLiveData<Airport> airport = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);
    private MutableLiveData<String> message = new MutableLiveData<>();

    public AirportInfoViewModel(@NonNull Application application) {
        super(application);
        this.airportRepository = AirportRepository.getAirportRepository(application.getApplicationContext());
        this.weatherRepository = WeatherRepository.getWeatherRepository(application.getApplicationContext());
    }

    public LiveData<Airport> getAirport() {
        return airport;
    }

    public LiveData<Weather> getWeather() {
        return weather;
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

    public void getWeatherByIcao(String icao){
        this.isLoading.setValue(true);
        this.weatherRepository.getWeatherByIcao(icao, this);
    }


    @Override
    public void onSuccess(Object data) {
        this.isLoading.setValue(false);
        if (data instanceof Airport){
            this.airport.setValue((Airport)data);
        }
        if (data instanceof Weather){
            this.weather.setValue((Weather)data);
        }
    }

    @Override
    public void onFailure(String message) {
        this.isLoading.setValue(false);
        this.message.setValue(message);
        this.airport.setValue(null);
    }
}
