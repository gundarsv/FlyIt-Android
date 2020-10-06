package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.flyit.application.models.Resource;
import com.flyit.application.models.User;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.SessionCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final String TAG = getClass().getSimpleName();
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static UserRepository userRepository = null;

    private UserRepository(Context context) {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static UserRepository getUserRepository(Context context) {
        if (userRepository == null) {
            userRepository = new UserRepository(context);
        }

        return userRepository;
    }

    public MutableLiveData<Resource<User>> getUser() {
        final MutableLiveData<Resource<User>> userMutableLiveData = new MutableLiveData<>();
        flyItApi.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userMutableLiveData.setValue(Resource.success(response.body()));
                } else {
                    userMutableLiveData.setValue(Resource.error(response.body().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userMutableLiveData.setValue(Resource.failure(t.getMessage()));
            }
        });

        return userMutableLiveData;
    }
}
