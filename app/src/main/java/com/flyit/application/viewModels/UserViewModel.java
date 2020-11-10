package com.flyit.application.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.Resource;
import com.flyit.application.models.User;
import com.flyit.application.networking.callbacks.DataCallback;
import com.flyit.application.repositories.AccessRepository;
import com.flyit.application.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel implements DataCallback {
    private MutableLiveData<Resource<User>> user;
    private UserRepository userRepository;
    private AccessRepository accessRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = UserRepository.getUserRepository(application.getApplicationContext());
        this.accessRepository = AccessRepository.getAccessRepository(application.getApplicationContext());
    }

    public LiveData<Resource<User>> getUser() {
        if (user == null) {
            this.user = userRepository.getUser();
        }
        return this.user;
    }

    public void signOut() {
        this.accessRepository.signOut(this);
    }

    @Override
    public void onSuccess(Object data) {
        this.user.setValue(null);
    }

    @Override
    public void onFailure(String msg) {

    }
}
