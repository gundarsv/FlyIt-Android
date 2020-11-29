package com.flyit.application.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;

public class StartTriviaFragment extends Fragment {
    private FragmentManager mFragmentManager;
    private Button mStartButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.fragment_start_trivia, container, false);

        mStartButton = view.findViewById(R.id.trivia_start_button);

        this.mFragmentManager = getActivity().getSupportFragmentManager();

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new TriviaGameQuestionFragment(), "TriviaGameQuestionFragment", getArguments(), R.id.fragment_container);
            }
        });

        return view;
    }

}
