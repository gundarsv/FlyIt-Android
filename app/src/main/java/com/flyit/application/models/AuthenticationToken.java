package com.flyit.application.models;

public class AuthenticationToken {

    private String accessToken;

    private String refreshToken;

    private long expiresAt;

    public AuthenticationToken(String accessToken, String refreshToken, long expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpiresAt() {
        return expiresAt;
    }
}
