package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.flyit.application.models.AuthenticationToken;
import com.flyit.application.models.SingIn;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.SessionCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private final String TAG = getClass().getSimpleName();
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static LoginRepository loginRepository = null;

    private LoginRepository(Context context)
    {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static LoginRepository getLoginRepository(Context context)
    {
        if (loginRepository == null)
        {
            loginRepository = new LoginRepository(context);
        }

        return loginRepository;
    }

    public void logIn(SingIn singIn, final SessionCallback callback) {
        flyItApi.singIn(singIn).enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                Log.d("LogInFlow", "response: " + response);
                if (response.isSuccessful()) {

                    mPrefsEdit.putString("accessToken", response.body().getAccessToken()).apply();
                    mPrefsEdit.putString("refreshToken", response.body().getRefreshToken()).apply();
                    mPrefsEdit.putLong("expiresAt", response.body().getExpiresAt()).apply();
                    callback.onSuccess();
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
