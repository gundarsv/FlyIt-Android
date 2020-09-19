package com.flyit.application.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.models.Resource;
import com.flyit.application.models.User;
import com.flyit.application.viewModels.UserViewModel;

public class UserFragment extends Fragment {
    private Button mLogOutButton;
    private TextView mUserData;
    private UserViewModel userViewModel;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        mLogOutButton = view.findViewById(R.id.log_out_button);
        mUserData = view.findViewById(R.id.userData);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.logOut();
            }
        });

        Log.d("LogOutFlow", fragmentManager.getFragments().toString());

        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Resource<User>>() {
            @Override
            public void onChanged(Resource<User> userResource) {
                if (userResource == null) {
                    changeFragment(new LoginFragment(), "LoginFragment");
                } else if (userResource.getStatus().equals(Resource.Status.SUCCESS)) {
                    mUserData.setText(userResource.getData().getUserName());
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
