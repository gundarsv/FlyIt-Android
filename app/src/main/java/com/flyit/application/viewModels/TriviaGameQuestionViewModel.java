package com.flyit.application.viewModels;

import android.app.Application;
import android.media.Image;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.flyit.application.models.QuizAnswer;
import com.flyit.application.repositories.TriviaGameQuestionRepository;

import java.util.List;

public class TriviaGameQuestionViewModel extends AndroidViewModel {
    private MutableLiveData<String> question;
    private MutableLiveData<String> image;
    private MutableLiveData<Integer> progress;
    private MutableLiveData<Integer> score;
    private MutableLiveData<List<QuizAnswer>> answers;
    private TriviaGameQuestionRepository triviaGameQuestionRepository;
    private MutableLiveData<Boolean> isFinished;
    private MutableLiveData<String> scoreString;
    private MutableLiveData<Integer> questionsCount;

    public TriviaGameQuestionViewModel(@NonNull Application application) {
        super(application);
        triviaGameQuestionRepository = TriviaGameQuestionRepository.getTriviaGameQuestionRepository();

        question = new MutableLiveData<>(triviaGameQuestionRepository.getQuizQuestions().get(0).getQuestion());
        image = new MutableLiveData<>(triviaGameQuestionRepository.getQuizQuestions().get(0).getImageurl());
        answers = new MutableLiveData<>(triviaGameQuestionRepository.getQuizQuestions().get(0).getQuizAnswers());
        progress = new MutableLiveData<>( 0);
        score = new MutableLiveData<>(0);
        isFinished = new MutableLiveData<>(false);
        scoreString = new MutableLiveData<>(score.getValue().toString() + "/" + triviaGameQuestionRepository.getQuizQuestions().size());
        questionsCount = new MutableLiveData<>(triviaGameQuestionRepository.getQuizQuestions().size());
    }

    public LiveData<String> getQuestion() {
        return question;
    }

    public LiveData<String> getImage() {
        return image;
    }

    public LiveData<Integer> getProgress() {
        return progress;
    }

    public LiveData<List<QuizAnswer>> getAnswers() {
        return answers;
    }

    public MutableLiveData<Integer> getQuestionsCount() {
        return questionsCount;
    }

    public MutableLiveData<Integer> getScore() {
        return score;
    }

    public MutableLiveData<String> getScoreString() {
        return scoreString;
    }

    public MutableLiveData<Boolean> getIsFinished() {
        return isFinished;

    }

    public void answerQuestion(int value) {
        if (answers.getValue().get(value).isCorrect()) {
            score.setValue(score.getValue().intValue() + 1);
            scoreString.setValue(score.getValue() + "/" + triviaGameQuestionRepository.getQuizQuestions().size());
        }
        Log.d("TriviaFlow", " progress =" + progress.getValue().intValue());
        Log.d("TriviaFlow", " quiz size =" + triviaGameQuestionRepository.getQuizQuestions().size());
        if (progress.getValue()+1 == triviaGameQuestionRepository.getQuizQuestions().size()) {
            isFinished.setValue(true);
            return;
        }
        progress.setValue(progress.getValue().intValue() +1);
        answers.setValue(triviaGameQuestionRepository.getQuizQuestions().get(progress.getValue()).getQuizAnswers());
        question.setValue(triviaGameQuestionRepository.getQuizQuestions().get(progress.getValue()).getQuestion());
        image.setValue(triviaGameQuestionRepository.getQuizQuestions().get(progress.getValue()).getImageurl());

    }
}
