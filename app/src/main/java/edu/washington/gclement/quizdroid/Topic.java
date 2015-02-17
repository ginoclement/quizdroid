package edu.washington.gclement.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ginoclement on 2/17/15.
 * Topic is a title, short description, long description, and a collection of Question objects.
 */
public class Topic implements Serializable {
    private String name;
    private String shortDesc;
    private String longDesc;
    private ArrayList<Question> questions;

    public Topic(String name, String shortDesc, String longDesc, ArrayList<Question> questions){
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.questions = questions;
    }

    public String getName(){
        return this.name;
    }

    public String getShortDesc(){
        return this.shortDesc;
    }

    public String getLongDesc(){
        return this.longDesc;
    }

    public ArrayList<Question> getQuestions(){
        return (ArrayList<Question>) this.questions.clone();
    }

    public int getNumQuestions(){
        return questions.size();
    }
}
