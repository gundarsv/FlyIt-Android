package com.flyit.application.networking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "https://flyit.azurewebsites.net/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getClient(context))
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient(Context context) {
        TokenAuthenticator tokenAuthenticator = TokenAuthenticator.getInstance(context);
        TokenInterceptor tokenInterceptor = TokenInterceptor.getInstance(context);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .authenticator(tokenAuthenticator)
                .addInterceptor(tokenInterceptor)
                .build();

        return okClient;
    }
}
