package com.flyit.application.models;

import java.util.List;

public class QuizQuestion {
    private String question;
    private List<QuizAnswer> quizAnswers;
    private String imageurl;

    public QuizQuestion(String question, List<QuizAnswer> quizAnswers, String imageurl) {
        this.question = question;
        this.quizAnswers = quizAnswers;
        this.imageurl = imageurl;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuizAnswer> getQuizAnswers() {
        return quizAnswers;
    }

    public void setQuizAnswers(List<QuizAnswer> quizAnswers) {
        this.quizAnswers = quizAnswers;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
