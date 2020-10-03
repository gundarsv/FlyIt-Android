package com.flyit.application.models;

public class User {
    private int id;
    private String email;
    private String fullName;

    public User(int id, String userName, String fullName) {
        this.id = id;
        this.email = userName;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
