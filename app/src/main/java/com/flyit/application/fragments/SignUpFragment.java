package com.flyit.application.fragments;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.flyit.application.viewModels.SignUpViewModel;

public class SignUpFragment extends Fragment {
    private FragmentManager fragmentManager;
    private SignUpViewModel signUpViewModel;

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mRepeatPassword;

    private TextView mLoginMessageText;

    private Button mRegisterButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        signUpViewModel = new ViewModelProvider(getActivity()).get(SignUpViewModel.class);

        mName = view.findViewById(R.id.sign_up_name);
        mEmail = view.findViewById(R.id.sign_up_email);
        mPassword = view.findViewById(R.id.sign_up_password);
        mRepeatPassword = view.findViewById(R.id.sign_up_repeat_password);

        changeEditTextColors(mName, R.color.colorWhite);
        changeEditTextColors(mEmail, R.color.colorWhite);
        changeEditTextColors(mPassword, R.color.colorWhite);
        changeEditTextColors(mRepeatPassword, R.color.colorWhite);

        mLoginMessageText = view.findViewById(R.id.sign_in_message);

        mRegisterButton = view.findViewById(R.id.sign_up_button);

        mLoginMessageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new SignInFragment(), "LoginFragment");
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpViewModel.SignUp(mName.getText().toString(), mEmail.getText().toString(), mPassword.getText().toString(), mRepeatPassword.getText().toString());
            }
        });

        signUpViewModel.getIsNameValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    changeEditTextColors(mName, R.color.colorValidGreen);
                } else {
                    changeEditTextColors(mName, R.color.colorInvalidRed);
                }
            }
        });

        signUpViewModel.getIsEmailValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    changeEditTextColors(mEmail, R.color.colorValidGreen);
                } else {
                    changeEditTextColors(mEmail, R.color.colorInvalidRed);
                }
            }
        });

        signUpViewModel.getIsPasswordValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    changeEditTextColors(mPassword, R.color.colorValidGreen);
                } else {
                    changeEditTextColors(mPassword, R.color.colorInvalidRed);
                }
            }
        });

        signUpViewModel.getIsRepeatPasswordValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    changeEditTextColors(mRepeatPassword, R.color.colorValidGreen);
                } else {
                    changeEditTextColors(mRepeatPassword, R.color.colorInvalidRed);
                }
            }
        });

        signUpViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s,
                        Toast.LENGTH_LONG).show();
            }
        });

        signUpViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    changeInputs(false);
                } else {
                    changeInputs(true);
                }
            }
        });

        return view;
    }

    private void changeInputs(boolean value) {
        mRepeatPassword.setEnabled(value);
        mName.setEnabled(value);
        mEmail.setEnabled(value);
        mPassword.setEnabled(value);
        mRegisterButton.setEnabled(value);
        mLoginMessageText.setEnabled(value);
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
