package edu.washington.gclement.quizdroid;

import android.app.Application;
import android.util.Log;

/**
 * Created by ginoclement on 2/17/15.
 */
public class QuizApp extends Application{
    private static QuizApp instance;

    public QuizApp(){
        // Get the application instance
        instance = (QuizApp)getApplication();

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
}
