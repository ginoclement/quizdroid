package edu.washington.gclement.quizdroid;

import java.io.Serializable;

/**
 * Created by ginoclement on 2/3/15.
 * Quiz is question text, four answers, and an integer saying which of the four answers is correct
 *
 * I decided to rename this to Question instead of Quiz because it makes more sense.
 */
public class Question implements Serializable{
    private int correct;
    private String question;
    private String[] answers;

    public Question(String question, String[] answers, int correct) {
        this.correct = correct;
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public int getCorrectAnswer(){
        return this.correct;
    }
}
