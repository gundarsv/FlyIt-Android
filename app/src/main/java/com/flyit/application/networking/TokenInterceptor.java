package com.flyit.application.networking;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private final static List<String> ALLOWED_URLS = Arrays.asList("/api/Auth/SignIn", "/api/Auth/SignUp", "/api/Auth/Revoke");
    private static TokenInterceptor tokenInterceptor = null;
    private SharedPreferences mPrefs;

    private TokenInterceptor(Context context) {
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static TokenInterceptor getInstance(Context context) {
        if (tokenInterceptor == null) {
            tokenInterceptor = new TokenInterceptor(context);
        }

        return tokenInterceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (ALLOWED_URLS.contains(chain.request().url().encodedPath())) {
            return chain.proceed(chain.request());
        }

        Request newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + mPrefs.getString("accessToken", ""))
                .build();

        return chain.proceed(newRequest);
    }
}
