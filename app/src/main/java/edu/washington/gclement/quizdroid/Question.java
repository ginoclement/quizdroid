package edu.washington.gclement.quizdroid;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ginoclement on 2/3/15.
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

    public boolean isCorrect(CharSequence response) {
        return answers[this.correct].equals(response);
    }
}
