package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.flyit.application.fragments.utils.MessageUtils;
import com.flyit.application.models.AuthenticationToken;
import com.flyit.application.models.SignUp;
import com.flyit.application.models.SingIn;
import com.flyit.application.networking.FlyItApi;
import com.flyit.application.networking.RetrofitService;
import com.flyit.application.networking.callbacks.DataCallback;

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

    public void signIn(SingIn singIn, final DataCallback callback) {
        flyItApi.SignIn(singIn).enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                if (response.isSuccessful()) {
                    mPrefsEdit.putString("accessToken", response.body().getAccessToken()).apply();
                    mPrefsEdit.putString("refreshToken", response.body().getRefreshToken()).apply();
                    mPrefsEdit.putLong("expiresAt", response.body().getExpiresAt()).apply();
                    callback.onSuccess(null);
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void signOut(final DataCallback sessionCallback) {
        mPrefsEdit.remove("accessToken").apply();
        mPrefsEdit.remove("refreshToken").apply();
        mPrefsEdit.remove("expiresAt").apply();

        sessionCallback.onSuccess(null);
    }

    public void signUp(SignUp signUp, final DataCallback callback) {
        flyItApi.SignUp(signUp).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure(MessageUtils.getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
