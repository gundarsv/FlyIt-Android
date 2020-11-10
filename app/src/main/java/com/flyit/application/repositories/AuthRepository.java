package com.flyit.application.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class AuthRepository {
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

    public boolean isUserAuthenticated() {
        String accessToken = mPrefs.getString("accessToken", null);

        if (accessToken != null && !accessToken.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
