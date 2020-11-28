package com.flyit.application.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.QuizAnswer;
import com.flyit.application.viewModels.TriviaGameQuestionViewModel;

import java.util.List;

public class TriviaGameQuestionFragment extends Fragment {
    private FragmentManager mFragmentManager;
    private TextView answerOneButton;
    private TextView answerTwoButton;
    private TextView answerThreeButton;
    private TextView answerFourButton;
    private TextView questionText;
    private ImageView questionImage;
    private TriviaGameQuestionViewModel triviaGameQuestionViewModel;
    private ProgressBar progressBar;
    private TextView score;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_trivia_game_question, container, false);

        answerOneButton = view.findViewById(R.id.answer_option_one);
        answerTwoButton = view.findViewById(R.id.answer_option_two);
        answerThreeButton = view.findViewById(R.id.answer_option_three);
        answerFourButton = view.findViewById(R.id.answer_option_four);
        questionText = view.findViewById(R.id.question_text);
        questionImage = view.findViewById(R.id.question_image);
        progressBar = view.findViewById(R.id.progress_bar);
        score = view.findViewById(R.id.text_score);


        triviaGameQuestionViewModel = new ViewModelProvider(this).get(TriviaGameQuestionViewModel.class);

        triviaGameQuestionViewModel.getAnswers().observe(getViewLifecycleOwner(), new Observer<List<QuizAnswer>>() {
            @Override
            public void onChanged(List<QuizAnswer> quizAnswers) {
                if (quizAnswers != null) {
                    answerOneButton.setText(quizAnswers.get(0).getAnswer());
                    answerTwoButton.setText(quizAnswers.get(1).getAnswer());
                    answerThreeButton.setText(quizAnswers.get(2).getAnswer());
                    answerFourButton.setText(quizAnswers.get(3).getAnswer());
                }
            }
        });

        triviaGameQuestionViewModel.getQuestion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    questionText.setText(s);
                }
            }
        });

        triviaGameQuestionViewModel.getProgress().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer != null) {
                    progressBar.setProgress(integer.intValue());
                }
            }
        });

        triviaGameQuestionViewModel.getScoreString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    score.setText(s);
                }
            }
        });

        answerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triviaGameQuestionViewModel.answerQuestion(0);
            }
        });

        answerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triviaGameQuestionViewModel.answerQuestion(1);
            }
        });

        answerThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triviaGameQuestionViewModel.answerQuestion(2);
            }
        });

        answerFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triviaGameQuestionViewModel.answerQuestion(3);
            }
        });

        this.mFragmentManager = getActivity().getSupportFragmentManager();

        triviaGameQuestionViewModel.getIsFinished().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == null)
                {
                    return;
                }
                if(aBoolean.booleanValue())
                {
                    Bundle bundle = getArguments();
                    bundle.putString("TriviaResult", triviaGameQuestionViewModel.getScoreString().getValue());
                    FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new TriviaGameResultFragment(), "TriviaGameResultFragment", bundle, R.id.fragment_container);
                }
            }
        });

        triviaGameQuestionViewModel.getQuestionsCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer !=null)
                {
                    progressBar.setMax(integer.intValue());
                }
            }
        }) ;

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new EntertainmentFragment(), "EntertainmentFragment", getArguments(), R.id.fragment_container);
            }
        });
        
        return view;
    }
}
