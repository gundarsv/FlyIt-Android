package com.flyit.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.flyit.application.fragments.FlightsFragment;
import com.flyit.application.fragments.LoadingFragment;
import com.flyit.application.fragments.SignInFragment;
import com.flyit.application.fragments.UserFragment;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.viewModels.MainViewModel;

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
                if (aBoolean) {
                    FragmentUtils.changeFragment(getViewModelStore(), fragmentManager, new FlightsFragment(), "FlightsFragment");
                } else {
                    FragmentUtils.changeFragment(getViewModelStore(), fragmentManager, new SignInFragment(), "LoginFragment");
                }
            }
        });
    }
}