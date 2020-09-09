package com.nan.carlight.model;

import com.nan.carlight.constant.Answer;

public class TestBean {

    private String title;
    @Answer
    private int answer;
    @Answer
    private int userAnswer = Answer.UNKNOWN;

    public TestBean() {
    }

    public TestBean(String title, @Answer int answer) {
        this.title = title;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Answer
    public int getAnswer() {
        return answer;
    }

    public void setAnswer(@Answer int answer) {
        this.answer = answer;
    }

    @Answer
    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(@Answer int userAnswer) {
        this.userAnswer = userAnswer;
    }
}
