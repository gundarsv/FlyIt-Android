package com.flyit.application.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.SingIn;
import com.flyit.application.networking.callbacks.SessionCallback;
import com.flyit.application.repositories.LoginRepository;

public class LogInViewModel extends AndroidViewModel implements SessionCallback {
    private LoginRepository loginRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>();

    public LogInViewModel(@NonNull Application application) {
        super(application);
        this.loginRepository = LoginRepository.getLoginRepository(application.getApplicationContext());
    }

    public LiveData<String> getMessage()
    {
        return this.message;
    }

    public LiveData<Boolean> getIsLoading()
    {
        return this.isLoading;
    }

    public LiveData<Boolean> getIsLoginSuccess()
    {
        return this.isLoginSuccess;
    }

    public void logIn(String userName, String password) {
        this.isLoading.setValue(true);
        this.loginRepository.logIn(new SingIn(userName, password), this);
    }

    @Override
    public void onSuccess() {
        this.isLoading.setValue(false);
        this.isLoginSuccess.setValue(true);
    }

    @Override
    public void onFailure(String msg) {
        this.isLoading.setValue(false);
        this.isLoginSuccess.setValue(false);
        this.message.setValue(msg);
    }
}
