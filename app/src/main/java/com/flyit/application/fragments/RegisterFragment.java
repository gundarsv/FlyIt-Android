package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;

public class RegisterFragment extends Fragment {
    private TextView mLoginMessageText;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        mLoginMessageText = view.findViewById(R.id.log_in_message);

        mLoginMessageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity(), fragmentManager, new LoginFragment(), "LoginFragment");
            }
        });

        return view;
    }
}
