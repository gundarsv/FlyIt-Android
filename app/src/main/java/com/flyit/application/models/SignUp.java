package com.flyit.application.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp {
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("fullName")
    private String fullName;

    @Expose
    @SerializedName("password")
    private String password;

    public SignUp(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

