package com.flyit.application.repositories;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.flyit.application.models.Flight;
import com.flyit.application.models.FlightSearch;
import com.flyit.application.models.Resource;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.AddFlightCallback;
import com.flyit.application.networking.callbacks.DataCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsRepository {
    private final String TAG = getClass().getSimpleName();
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static FlightsRepository flightsRepository = null;

    private FlightsRepository(Context context)
    {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }


    public static FlightsRepository getFLightsRepository(Context context)
    {
        if (flightsRepository == null)
        {
            flightsRepository = new FlightsRepository(context);
        }

        return flightsRepository;
    }

    public MutableLiveData<Resource<ArrayList<Flight>>> getFlights() {
        final MutableLiveData<Resource<ArrayList<Flight>>> flightMutableLiveData = new MutableLiveData<>();
        flyItApi.getFlight().enqueue(new Callback<ArrayList<Flight>>() {
            @Override
            public void onResponse(Call<ArrayList<Flight>> call, Response<ArrayList<Flight>> response) {
                if(response.isSuccessful()){
                    flightMutableLiveData.setValue(Resource.success(response.body()));
                }
                else{
                    flightMutableLiveData.setValue(Resource.error(response.body().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Flight>> call, Throwable t) {
                flightMutableLiveData.setValue(Resource.failure(t.getMessage()));

            }
        });
        return flightMutableLiveData;
    }

    public void searchFlight(String flightNo, final DataCallback callback){
        flyItApi.getSearchFlight(flightNo).enqueue(new Callback<FlightSearch>() {
            @Override
            public void onResponse(Call<FlightSearch> call, Response<FlightSearch> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<FlightSearch> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void addFlight (FlightSearch flightSearch, final AddFlightCallback callback){
        flyItApi.addFlight(flightSearch).enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight> response) {
                if(response.isSuccessful()){
                    callback.onAddFlightSuccess();
                }
                else {
                    callback.onAddFlightFailure(response.message());
                    Log.d("AddFlightFlow", "Failure" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                callback.onAddFlightFailure(t.getMessage());
            }
        });
    }
}
