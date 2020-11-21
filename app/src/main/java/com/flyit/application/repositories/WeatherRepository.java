package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.flyit.application.fragments.utils.MessageUtils;
import com.flyit.application.models.Weather;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.DataCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static WeatherRepository weatherRepository = null;

    private WeatherRepository (Context context){
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static WeatherRepository getWeatherRepository (Context context){
        if (weatherRepository == null){
            weatherRepository = new WeatherRepository(context);
        }
        return weatherRepository;
    }

    public void getWeatherByIcao (String icao, final DataCallback<Weather> callback){
        flyItApi.getWeatherByIcao(icao).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
