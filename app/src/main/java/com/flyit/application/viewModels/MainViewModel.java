package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.networking.callbacks.AuthCallback;
import com.flyit.application.repositories.AuthRepository;

public class MainViewModel extends AndroidViewModel implements AuthCallback {
    private AuthRepository authRepository;
    private MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.authRepository = AuthRepository.getUserRepository(application);
        this.authRepository.isUserAuthenticated(this);
    }

    public LiveData<Boolean> getIsAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void onAuthCallback(boolean isAuthenticated) {
        this.isAuthenticated.setValue(isAuthenticated);
    }

}
