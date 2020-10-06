package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.flyit.application.models.AuthenticationToken;
import com.flyit.application.models.SignUp;
import com.flyit.application.models.SingIn;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.networking.callbacks.SessionCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccessRepository {
    private FlyItApi flyItApi;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static AccessRepository accessRepository = null;

    private AccessRepository(Context context) {
        this.flyItApi = RetrofitService.getRetrofitInstance(context).create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static AccessRepository getAccessRepository(Context context) {
        if (accessRepository == null) {
            accessRepository = new AccessRepository(context);
        }

        return accessRepository;
    }

    public void signIn(SingIn singIn, final SessionCallback callback) {
        flyItApi.SignIn(singIn).enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                if (response.isSuccessful()) {
                    mPrefsEdit.putString("accessToken", response.body().getAccessToken()).apply();
                    mPrefsEdit.putString("refreshToken", response.body().getRefreshToken()).apply();
                    mPrefsEdit.putLong("expiresAt", response.body().getExpiresAt()).apply();
                    callback.onSuccess();
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
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void signOut(final SessionCallback sessionCallback) {
        mPrefsEdit.remove("accessToken").apply();
        mPrefsEdit.remove("refreshToken").apply();
        mPrefsEdit.remove("expiresAt").apply();

        sessionCallback.onSuccess();
    }

    public void signUp(SignUp signUp, final DataCallback<Void> callback) {
        flyItApi.SignUp(signUp).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
