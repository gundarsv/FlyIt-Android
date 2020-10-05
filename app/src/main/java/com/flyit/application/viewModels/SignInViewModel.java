package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.SingIn;
import com.flyit.application.networking.callbacks.SessionCallback;
import com.flyit.application.repositories.AccessRepository;

public class SignInViewModel extends AndroidViewModel implements SessionCallback {
    private AccessRepository accessRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Boolean> isSignInSuccess = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEmailValid = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPasswordValid = new MutableLiveData<>();

    public SignInViewModel(@NonNull Application application) {
        super(application);
        this.accessRepository = AccessRepository.getAccessRepository(application.getApplicationContext());
    }

    public LiveData<String> getMessage() {
        return this.message;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }

    public LiveData<Boolean> getIsSignInSuccess() {
        return this.isSignInSuccess;
    }

    public LiveData<Boolean> getIsEmailValid() {
        return isEmailValid;
    }

    public LiveData<Boolean> getIsPasswordValid() {
        return isPasswordValid;
    }

    public void signIn(String email, String password) {
        this.isLoading.setValue(true);

        boolean hasValidInputs = validateInputs(email, password);

        if (hasValidInputs) {
            this.accessRepository.signIn(new SingIn(email, password), this);
        } else {
            this.isLoading.setValue(false);
        }
    }

    public boolean validateInputs(String email, String password) {
        boolean isPasswordValid = true;
        boolean isEmailValid = true;

        if (email.isEmpty()) {
            isEmailValid = false;
        }

        if (password.isEmpty()) {
            isPasswordValid = false;
        }

        this.isEmailValid.setValue(isEmailValid);
        this.isPasswordValid.setValue(isPasswordValid);

        return isEmailValid && isPasswordValid;
    }

    @Override
    public void onSuccess() {
        this.isLoading.setValue(false);
        this.isSignInSuccess.setValue(true);
    }

    @Override
    public void onFailure(String msg) {
        this.isLoading.setValue(false);
        this.isSignInSuccess.setValue(false);
        this.message.setValue(msg);
        this.isPasswordValid.setValue(false);
        this.isEmailValid.setValue(false);
    }
}
