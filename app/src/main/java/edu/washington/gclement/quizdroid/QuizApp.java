package edu.washington.gclement.quizdroid;

import android.app.Application;
import android.content.Intent;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ginoclement on 2/17/15.
 */
public class QuizApp extends Application implements TopicRepository{
    private static QuizApp instance;
    private static Map<String, Topic> topics;
    JsonReader jsonReader;
//    private static TopicRepository topicRepo;

    public QuizApp(){
        // Get the application instance
        instance = (QuizApp)getApplication();
//        topicRepo = new TopicRepository();

        if(instance == null){
            instance = this;
        } else {
            Log.e("QuizApp", "You can't have the app running more than once.");
            throw new RuntimeException("Multiple App Exception");
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        topics = new HashMap<String, Topic>();
        String fileLocation = getFilesDir().toString() + "/quizdata.json";
        Log.i("QuizApp", "Checking \"" + fileLocation + "\" for JSON file.");
        try {
            FileInputStream fileInputStream = openFileInput("quizdata.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            jsonReader = new JsonReader(inputStreamReader);
            jsonReader.beginArray();
            Log.i("QuizApp", jsonReader.toString());
//            jsonReader.beginArray();
            while (jsonReader.hasNext()){
                loadTopic();
            }
            jsonReader.endArray();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }

    public QuizApp getApplication(){
        return this.instance;
    }

    @Override
    public ArrayList<Topic> getTopics() {
        return new ArrayList<>(topics.values());
    }

    public void loadTopic(){
        try {
            jsonReader.beginObject();
            String title = "Title";
            String desc = "Description";
            ArrayList<Question> questions;
            if(jsonReader.nextName().equals("title")){
                title = jsonReader.nextString();
            }
            if(jsonReader.nextName().equals("desc")){
                desc = jsonReader.nextString();
            }
            questions = loadQuestions();
            topics.put(title, new Topic(title, desc, desc, questions));
            jsonReader.endObject();
        } catch (IOException e) {

        }


    }

    public ArrayList<Question> loadQuestions(){
        ArrayList<Question> ret = new ArrayList<>();
        try {
            if(jsonReader.nextName().equals("questions")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    String question = "Question";
                    Log.i("QuizApp", jsonReader.toString());
                    if (jsonReader.nextName().equals("text")) {
                        question = jsonReader.nextString();
                    }
                    Log.i("QuizApp", "Loading question: " + question);
                    int correct = 0;
                    Log.i("QuizApp", jsonReader.toString());
                    if (jsonReader.nextName().equals("answer")) {
                        correct = Integer.parseInt(jsonReader.nextString());
                    }
                    Log.i("QuizApp", jsonReader.toString());
                    ArrayList<String> answers = getAnswerOptions();
                    ret.add(new Question(question, answers, correct));
                    jsonReader.endObject();
                }

                jsonReader.endArray();
            }
        } catch (IOException e) {

        }
        return ret;
    }

    public ArrayList<String> getAnswerOptions(){
        ArrayList<String> ret = new ArrayList<>();
        try {
            if(jsonReader.nextName().equals("answers")) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    String temp = jsonReader.nextString();
                    ret.add(temp);
                    Log.i("QuizApp", "Adding answer " + temp);
                }
                jsonReader.endArray();
            }
        } catch (IOException e) {

        }
        return ret;
    }

}
