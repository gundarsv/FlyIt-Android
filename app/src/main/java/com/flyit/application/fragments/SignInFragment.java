package com.flyit.application.fragments;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.viewModels.SignInViewModel;

public class SignInFragment extends Fragment {
    private Button mSignInButton;
    private EditText mEmail;
    private EditText mPassword;

    private SignInViewModel signInViewModel;
    private FragmentManager fragmentManager;

    private ProgressBar mProgressBar;
    private TextView mSignUpText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mSignInButton = view.findViewById(R.id.sign_in_button);
        mEmail = view.findViewById(R.id.sign_in_email_address);
        mPassword = view.findViewById(R.id.sign_in_password);
        mProgressBar = view.findViewById(R.id.progressBar);
        mSignUpText = view.findViewById(R.id.sign_up_message);

        changeEditTextColors(mEmail, R.color.colorWhite);
        changeEditTextColors(mPassword, R.color.colorWhite);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        signInViewModel = new ViewModelProvider(getActivity()).get(SignInViewModel.class);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInViewModel.signIn(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });

        mSignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new SignUpFragment(), "RegisterFragment");
            }
        });

        signInViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPassword.setEnabled(false);
                    mEmail.setEnabled(false);
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mPassword.setEnabled(true);
                    mEmail.setEnabled(true);
                }
            }
        });

        signInViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("LogInFlow", "messageChange: " + s);
                Toast.makeText(getActivity(), s,
                        Toast.LENGTH_LONG).show();
            }
        });

        signInViewModel.getIsSignInSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new UserFragment(), "UserFragment");
                }
            }
        });

        signInViewModel.getIsEmailValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    changeEditTextColors(mEmail, R.color.colorInvalidRed);
                }
            }
        });

        signInViewModel.getIsPasswordValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    changeEditTextColors(mPassword, R.color.colorInvalidRed);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void changeEditTextColors(EditText editText, int colorResource) {
        setTextViewDrawableColor(editText, colorResource);
        editText.setTextColor(ContextCompat.getColor(getContext(), colorResource));
        editText.setHintTextColor(ContextCompat.getColor(getContext(), colorResource));
        editText.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), colorResource)));
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }
}