package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.flyit.application.repositories.AuthRepository;

public class MainViewModel extends AndroidViewModel {
    private AuthRepository authRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.authRepository = AuthRepository.getUserRepository(application);
    }

    public boolean getIsAuthenticated() {
        return authRepository.isUserAuthenticated();
    }
}
