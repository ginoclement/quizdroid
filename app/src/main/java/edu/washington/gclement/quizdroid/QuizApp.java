package edu.washington.gclement.quizdroid;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ginoclement on 2/17/15.
 */
public class QuizApp extends Application{
    private static QuizApp instance;
    private static Map<String, Topic> topics;
    private static TopicRepository topicRepo;

    public QuizApp(){
        // Get the application instance
        instance = (QuizApp)getApplication();
        topicRepo = new TopicRepository();

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
    }

    public QuizApp getApplication(){
        return this.instance;
    }

    public TopicRepository getTopicRepo(){
        return this.topicRepo;
    }
}
