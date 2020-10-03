package com.flyit.application.networking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticationService {
    private static final String BASE_URL = "https://flyit.azurewebsites.net/api/";
    public Retrofit retrofit;
    private static AuthenticationService authenticationService = null;

    private AuthenticationService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        this.retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static AuthenticationService getInstance() {
        if (authenticationService == null) {
            authenticationService = new AuthenticationService();
        }

        return authenticationService;
    }
}
