package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.flyit.application.networking.callbacks.AuthCallback;

public class AuthRepository {
    private final String TAG = getClass().getSimpleName();
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEdit;

    private static AuthRepository authRepository = null;

    private AuthRepository(Context context) {
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.mPrefsEdit = mPrefs.edit();
    }

    public static AuthRepository getUserRepository(Context context) {
        if (authRepository == null) {
            authRepository = new AuthRepository(context);
        }

        return authRepository;
    }

    public void isUserAuthenticated(final AuthCallback callback) {
        String accessToken = mPrefs.getString("accessToken", null);

        if (accessToken != null && !accessToken.trim().isEmpty()) {
            callback.onAuthCallback(true);
        } else {
            callback.onAuthCallback(false);
        }
    }
}
