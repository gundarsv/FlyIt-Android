package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;


public class TriviaGameResultFragment extends Fragment {
    private FragmentManager mFragmentManager;
    private Button mFinishButton;
    private TextView scoreResult;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_trivia_game_result, container, false);


        mFinishButton = view.findViewById(R.id.trivia_finish_button);
        scoreResult = view.findViewById(R.id.trivia_score_result);

        scoreResult.setText("Your score is " + getArguments().getString("TriviaResult"));

        this.mFragmentManager = getActivity().getSupportFragmentManager();

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new EntertainmentFragment(), "EntertainmentFragment", getArguments(), R.id.fragment_container);
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new EntertainmentFragment(), "EntertainmentFragment", getArguments(), R.id.fragment_container);
            }
        });


        return view;
    }
}
