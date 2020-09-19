package com.flyit.application.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingIn {
    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("password")
    private String password;

    public SingIn(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
