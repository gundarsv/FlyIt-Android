package com.flyit.application.models;

public class AuthenticationTokenRefresh {
    private String accessToken;

    private String refreshToken;

    public AuthenticationTokenRefresh(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
