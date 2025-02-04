package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.flyit.application.fragments.utils.MessageUtils;
import com.flyit.application.models.Flight;
import com.flyit.application.models.FlightSearch;
import com.flyit.application.models.Resource;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.AddFlightCallback;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.networking.callbacks.DeleteFlightCallback;
import com.flyit.application.networking.callbacks.UpdateFlightCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsRepository {
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static FlightsRepository flightsRepository = null;

    private FlightsRepository(Context context) {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }


    public static FlightsRepository getFLightsRepository(Context context) {
        if (flightsRepository == null) {
            flightsRepository = new FlightsRepository(context);
        }

        return flightsRepository;
    }

    public MutableLiveData<Resource<ArrayList<Flight>>> getFlights() {
        final MutableLiveData<Resource<ArrayList<Flight>>> flightMutableLiveData = new MutableLiveData<>();
        flyItApi.getFlight().enqueue(new Callback<ArrayList<Flight>>() {
            @Override
            public void onResponse(Call<ArrayList<Flight>> call, Response<ArrayList<Flight>> response) {
                if (response.isSuccessful()) {
                    flightMutableLiveData.setValue(Resource.success(response.body()));
                } else {
                    try {
                        Gson gson = new Gson();
                        Type type = new TypeToken<String[]>() {
                        }.getType();
                        String[] error = gson.fromJson(response.errorBody().charStream(), type);
                        flightMutableLiveData.setValue(Resource.error(error[0], null));
                    } catch (Exception e) {
                        flightMutableLiveData.setValue(Resource.failure(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Flight>> call, Throwable t) {
                flightMutableLiveData.setValue(Resource.failure(t.getMessage()));
            }
        });
        return flightMutableLiveData;
    }

    public void searchFlight(String flightNo, final DataCallback<FlightSearch> callback) {
        flyItApi.getSearchFlight(flightNo).enqueue(new Callback<FlightSearch>() {
            @Override
            public void onResponse(Call<FlightSearch> call, Response<FlightSearch> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<FlightSearch> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void addFlight(FlightSearch flightSearch, final AddFlightCallback callback) {
        flyItApi.addFlight(flightSearch).enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight> response) {
                if (response.isSuccessful()) {
                    callback.onAddFlightSuccess();
                } else {
                    callback.onAddFlightFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                callback.onAddFlightFailure(t.getMessage());
            }
        });
    }

    public void deleteFlight(int id, final DeleteFlightCallback callback){
        flyItApi.deleteFlight(id).enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight> response) {
                if (response.isSuccessful()) {
                    callback.onDeleteFlightSuccess(response.body());
                } else {
                    callback.onDeleteFlightFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                callback.onDeleteFlightFailure(t.getMessage());
            }
        });
    }


    public void getFlightById(int id, final UpdateFlightCallback callback){
        flyItApi.getFlightById(id).enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight> response) {
                if (response.isSuccessful()) {
                    callback.onUpdateFlightSuccess(response.body());
                } else {
                    callback.onUpdateFlightFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                callback.onUpdateFlightFailure(t.getMessage());
            }
        });
    }
}
