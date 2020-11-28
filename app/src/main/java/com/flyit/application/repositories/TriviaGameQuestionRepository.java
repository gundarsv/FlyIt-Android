package com.flyit.application.repositories;

import com.flyit.application.models.QuizAnswer;
import com.flyit.application.models.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class TriviaGameQuestionRepository {

    private static TriviaGameQuestionRepository triviaGameQuestionRepository = null;

    private TriviaGameQuestionRepository()
    {

    }

    public static TriviaGameQuestionRepository getTriviaGameQuestionRepository()
    {
        if(triviaGameQuestionRepository == null)
        {
            triviaGameQuestionRepository = new TriviaGameQuestionRepository();
        }
        return triviaGameQuestionRepository;
    }

    public List<QuizQuestion> getQuizQuestions()
    {
        QuizAnswer quizAnswer1 = new QuizAnswer("Transylvania", true );
        QuizAnswer quizAnswer2 = new QuizAnswer("2", false );
        QuizAnswer quizAnswer3 = new QuizAnswer("3", false );
        QuizAnswer quizAnswer4 = new QuizAnswer("4", false );
        List<QuizAnswer> answers = new ArrayList<>();
        answers.add(quizAnswer1);
        answers.add(quizAnswer2);
        answers.add(quizAnswer3);
        answers.add(quizAnswer4);
        QuizQuestion quizQuestion1 = new QuizQuestion("From which region of Romania do vampyires come?", answers, "https://kbimages1-a.akamaihd.net/9bba24a1-6f67-47e8-9039-e1cf0cfeca11/1200/1200/False/the-vampyre-with-12-illustrations-and-a-free-online-audio-file.jpg");
        QuizQuestion quizQuestion2 = new QuizQuestion("From which region of Romania do vampyires come?", answers, "https://kbimages1-a.akamaihd.net/9bba24a1-6f67-47e8-9039-e1cf0cfeca11/1200/1200/False/the-vampyre-with-12-illustrations-and-a-free-online-audio-file.jpg");
        QuizQuestion quizQuestion3 = new QuizQuestion("From which region of Romania do vampyires come?", answers, "https://kbimages1-a.akamaihd.net/9bba24a1-6f67-47e8-9039-e1cf0cfeca11/1200/1200/False/the-vampyre-with-12-illustrations-and-a-free-online-audio-file.jpg");
        QuizQuestion quizQuestion4 = new QuizQuestion("From which region of Romania do vampyires come?", answers, "https://kbimages1-a.akamaihd.net/9bba24a1-6f67-47e8-9039-e1cf0cfeca11/1200/1200/False/the-vampyre-with-12-illustrations-and-a-free-online-audio-file.jpg");
        List<QuizQuestion> listOfQuestions = new ArrayList<>();
        listOfQuestions.add(quizQuestion1);
        listOfQuestions.add(quizQuestion2);
        listOfQuestions.add(quizQuestion3);
        listOfQuestions.add(quizQuestion4);
        return listOfQuestions;

    }



}
