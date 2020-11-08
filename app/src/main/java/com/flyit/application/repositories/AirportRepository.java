package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.flyit.application.models.Airport;
import com.flyit.application.models.News;
import com.flyit.application.models.Resource;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.DataCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportRepository {
    private final String TAG = getClass().getSimpleName();
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static AirportRepository airportRepository = null;

    private AirportRepository(Context context) {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static AirportRepository getAirportRepository(Context context) {
        if (airportRepository == null) {
            airportRepository = new AirportRepository(context);
        }

        return airportRepository;
    }

    public void getGetAirportByIata(String iata, final DataCallback<Airport> callback) {
        flyItApi.getAirportByIata(iata).enqueue(new Callback<Airport>() {
            @Override
            public void onResponse(Call<Airport> call, Response<Airport> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        Gson gson = new Gson();
                        Type type = new TypeToken<String[]>() {
                        }.getType();
                        String[] error = gson.fromJson(response.errorBody().charStream(), type);
                        callback.onFailure(error[0]);
                    } catch (Exception e) {
                        callback.onFailure(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
