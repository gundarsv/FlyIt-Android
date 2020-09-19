package com.flyit.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.flyit.application.fragments.LoadingFragment;
import com.flyit.application.fragments.LoginFragment;
import com.flyit.application.fragments.UserFragment;
import com.flyit.application.viewModels.MainViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, new LoadingFragment());
        ft.commit();

        initViewModel();
    }

    public void initViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        this.mainViewModel.getIsAuthenticated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (aBoolean) {
                            changeFragment(new UserFragment(), "UserFragment");
                        } else {
                            changeFragment(new LoginFragment(), "LoginFragment");
                        }
                    }
                }, 2000);
            }
        });
    }

    public void changeFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        Fragment frag = fragmentManager.findFragmentByTag(tag);
        if (frag == null) {
            frag = fragment;
        }

        ft.replace(R.id.fragment_container, frag);
        ft.commit();
    }
}