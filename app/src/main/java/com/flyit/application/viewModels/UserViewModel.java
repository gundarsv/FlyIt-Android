package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Resource;
import com.flyit.application.models.User;
import com.flyit.application.networking.callbacks.SessionCallback;
import com.flyit.application.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel implements SessionCallback {
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Resource<User>> user;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = UserRepository.getUserRepository(application.getApplicationContext());
    }

    public LiveData<Resource<User>> getUser()
    {
        if (user == null)
        {
            this.user = userRepository.getUser();
        }
        return this.user;
    }

    public LiveData<Boolean> getIsLoading()
    {
        return this.isLoading;
    }

    public void logOut()
    {
        this.isLoading.setValue(true);
        this.userRepository.logOut(this);
    }

    @Override
    public void onSuccess() {
        this.isLoading.setValue(false);
        this.user.setValue(null);
    }

    @Override
    public void onFailure(String msg) {

    }
}
