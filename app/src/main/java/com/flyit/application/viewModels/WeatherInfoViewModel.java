//package com.flyit.application.viewModels;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.flyit.application.models.Weather;
//import com.flyit.application.networking.callbacks.DataCallback;
//import com.flyit.application.repositories.WeatherRepository;
//
//public class WeatherInfoViewModel extends AndroidViewModel implements DataCallback<Weather> {
//    private WeatherRepository weatherRepository;
//    private MutableLiveData<Weather> weather = new MutableLiveData<>();
//    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);
//    private MutableLiveData<String> message = new MutableLiveData<>();
//
//    public WeatherInfoViewModel(@NonNull Application application) {
//        super(application);
//        this.weatherRepository = WeatherRepository.getWeatherRepository(application.getApplicationContext());
//    }
//
//    public LiveData<Weather> getWeather() {
//        return weather;
//    }
//
//    public LiveData<Boolean> getIsLoading() {
//        return isLoading;
//    }
//
//    public LiveData<String> getMessage() {
//        return message;
//    }
//
//    public void getWeatherByIcao(String icao){
//        this.isLoading.setValue(true);
//        this.weatherRepository.getWeatherByIcao(icao, this);
//    }
//
//    @Override
//    public void onSuccess(Weather data) {
//        this.isLoading.setValue(false);
//        this.weather.setValue(data);
//    }
//
//    @Override
//    public void onFailure(String message) {
//        this.isLoading.setValue(false);
//        this.message.setValue(message);
//        this.weather.setValue(null);
//    }
//}
