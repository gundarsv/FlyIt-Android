package com.flyit.application.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull
    private Status status;
    @Nullable
    private T data;
    @Nullable
    private String message;

    public Resource(@NonNull Status status, @Nullable T data,
                     @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> successNoBody() {
        return new Resource<>(Status.SUCCESS, null, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> unauthorized() {
        return new Resource<>(Status.UNAUTHORIZED, null, "Unauthorized");
    }

    public static <T> Resource<T> failure(String msg) {
        return new Resource<>(Status.FAILURE, null, msg);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    public enum Status {SUCCESS, ERROR, UNAUTHORIZED, FAILURE}
}
