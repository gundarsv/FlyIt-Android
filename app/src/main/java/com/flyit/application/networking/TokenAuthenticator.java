package com.flyit.application.networking;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.flyit.application.models.AuthenticationToken;
import com.flyit.application.models.AuthenticationTokenRefresh;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;

public class TokenAuthenticator implements Authenticator {
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;
    private static FlyItApi flyItApi;

    private static TokenAuthenticator tokenAuthenticator = null;

    private TokenAuthenticator(Context context) {
        this.flyItApi = AuthenticationService.getInstance().retrofit.create(FlyItApi.class);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static TokenAuthenticator getInstance(Context context) {
        if (tokenAuthenticator == null) {
            tokenAuthenticator = new TokenAuthenticator(context);
        }

        return tokenAuthenticator;
    }

    @Override
    public Request authenticate(Route route, Response response) {
        if (refreshToken()) {
            return response.request().newBuilder()
                    .header("Authorization", mPrefs.getString("accessToken", ""))
                    .build();
        }

        return null;
    }

    public boolean refreshToken() {
        final String refreshToken = mPrefs.getString("refreshToken", "");
        final String accessToken = mPrefs.getString("accessToken", "");

        if (refreshToken.trim().isEmpty() || accessToken.trim().isEmpty()) {
            return false;
        }

        final MutableLiveData<AuthenticationToken> authenticationToken = new MutableLiveData<>();

        Call<AuthenticationToken> authenticationTokenCall = flyItApi.revoke(new AuthenticationTokenRefresh(accessToken, refreshToken));

        authenticationTokenCall.enqueue(new Callback<AuthenticationToken>() {
            @Override
            public void onResponse(Call<AuthenticationToken> call, retrofit2.Response<AuthenticationToken> response) {
                if (response.isSuccessful()) {
                    authenticationToken.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                authenticationToken.setValue(null);
            }
        });

        if (authenticationToken.getValue() == null) {
            return false;
        }

        mPrefsEdit.putLong("expiresAt", authenticationToken.getValue().getExpiresAt());
        mPrefsEdit.putString("accessToken", authenticationToken.getValue().getAccessToken());
        mPrefsEdit.putString("refreshToken", authenticationToken.getValue().getRefreshToken());

        return true;
    }
}
