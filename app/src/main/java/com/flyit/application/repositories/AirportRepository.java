package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.flyit.application.fragments.utils.MessageUtils;
import com.flyit.application.models.Airport;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.DataCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportRepository {
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
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Airport> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
