package com.flyit.application.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.viewModels.LogInViewModel;

public class LoginFragment extends Fragment {
    private Button mLoginButton;
    private LogInViewModel loginViewModel;
    private TextView mUserName;
    private TextView mPassword;
    private FragmentManager fragmentManager;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mLoginButton = view.findViewById(R.id.log_in_button);
        mUserName = view.findViewById(R.id.log_in_email_address);
        mPassword = view.findViewById(R.id.log_in_password);
        mProgressBar = view.findViewById(R.id.progressBar);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        loginViewModel = new ViewModelProvider(getActivity()).get(LogInViewModel.class);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logIn(mUserName.getText().toString(), mPassword.getText().toString());
            }
        });

        Log.d("LogOutFlow", fragmentManager.getFragments().toString());

        loginViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d("LogInFlow", "isLoadingChange: " + aBoolean);
                if (aBoolean) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        loginViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("LogInFlow", "messageChange: " + s);
                Toast.makeText(getActivity(), s,
                        Toast.LENGTH_LONG).show();
            }
        });

        loginViewModel.getIsLoginSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    changeFragment(new UserFragment(), "UserFragment");
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void changeFragment(Fragment fragment, String tag) {
        getActivity().getViewModelStore().clear();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        Fragment frag = fragmentManager.findFragmentByTag(tag);
        if (frag == null) {
            frag = fragment;
        }

        ft.replace(R.id.fragment_container, frag);
        ft.commit();
    }
}
