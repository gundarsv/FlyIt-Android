package com.flyit.application.fragments.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Response;

public class MessageUtils {
    public static String getErrorMessage(Response response) {
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<String[]>() {
            }.getType();
            String[] error = gson.fromJson(response.errorBody().charStream(), type);
            return error[0];
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
