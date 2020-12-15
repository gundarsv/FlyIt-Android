package com.flyit.application.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.flyit.application.LanguageLearningGameActivity;
import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;

public class EntertainmentFragment extends Fragment {
    private ImageView languageLearningGame;
    private ImageView triviaGame;
    private FragmentManager mFragmentManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertainment, container, false);
        this.mFragmentManager = getActivity().getSupportFragmentManager();
        languageLearningGame = view.findViewById(R.id.language_game_logo_image);
        triviaGame = view.findViewById(R.id.trivia_game_logo_image);
        triviaGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new StartTriviaFragment(), "TriviaQuizFragment", getArguments(), R.id.fragment_container);
            }
        });
        languageLearningGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LanguageLearningGameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new ControlCenterMenuFragment(), "ControlCenterMenuFragment", getArguments(), R.id.fragment_container);
            }
        });
        return view;
    }

}
