package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.SignUp;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.AccessRepository;

public class SignUpViewModel extends AndroidViewModel implements DataCallback<Void> {
    private AccessRepository accessRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNameValid = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEmailValid = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPasswordValid = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRepeatPasswordValid = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        this.accessRepository = AccessRepository.getAccessRepository(application.getApplicationContext());
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }

    public LiveData<String> getMessage() {
        return this.message;
    }

    public LiveData<Boolean> getIsNameValid() {
        return this.isNameValid;
    }

    public LiveData<Boolean> getIsEmailValid() {
        return this.isEmailValid;
    }

    public LiveData<Boolean> getIsPasswordValid() {
        return this.isPasswordValid;
    }

    public LiveData<Boolean> getIsRepeatPasswordValid() {
        return this.isRepeatPasswordValid;
    }

    public void SignUp(String name, String email, String password, String repeatPassword) {
        this.isLoading.setValue(true);

        boolean isValid = ValidateSignUp(name, email, password, repeatPassword);

        if (isValid) {
            accessRepository.signUp(new SignUp(email, name, password), this);
        } else {
            this.isLoading.setValue(false);
        }
    }

    public boolean ValidateSignUp(String name, String email, String password, String repeatPassword) {
        boolean isNameValid = true;
        boolean isEmailValid = true;
        boolean isPasswordValid = true;
        boolean isRepeatPasswordValid = true;

        if (name.isEmpty()) {
            isNameValid = false;
        }

        if (email.isEmpty()) {
            isEmailValid = false;
        }

        if (!ValidatePassword(password)) {
            isPasswordValid = false;
        }

        if (!ValidatePassword(repeatPassword)) {
            isRepeatPasswordValid = false;
        }

        if (!password.equals(repeatPassword)) {
            isRepeatPasswordValid = false;
            isPasswordValid = false;
        }

        this.isNameValid.setValue(isNameValid);
        this.isEmailValid.setValue(isEmailValid);
        this.isPasswordValid.setValue(isPasswordValid);
        this.isRepeatPasswordValid.setValue(isRepeatPasswordValid);

        return isEmailValid && isNameValid && isPasswordValid && isRepeatPasswordValid;
    }

    private boolean ValidatePassword(String password) {
        if (password.isEmpty()) {
            return false;
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            return false;
        } else if (!password.matches("^.*[^a-zA-Z0-9 ].*$")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onSuccess(Void data) {
        this.isLoading.setValue(false);
        this.message.setValue("User is registered");
    }

    @Override
    public void onFailure(String message) {
        this.isLoading.setValue(false);
        this.message.setValue(message);
    }
}
