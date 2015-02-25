package edu.washington.gclement.quizdroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ginoclement on 2/24/15.
 */
public class QuestionDownloadIntentService extends IntentService {
    public QuestionDownloadIntentService(){
        super("QuestionDownloadIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("QuizApp", "Launched QuestionDownloadIntentService");
        Toast.makeText(getApplicationContext(), "Download would be kicked off here", Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        int interval = Integer.parseInt(prefs.getString("prefUpdateInterval", "Interval?"));
        String url = prefs.getString("prefUpdateURL", "Need a URL?");

        Log.i("QuizApp", "Downloading from " + url + " with interval " + interval);

        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.

//        long endTime = System.currentTimeMillis() + interval*1000;
//        while (System.currentTimeMillis() < endTime) {
//            synchronized (this) {
//                try {
//                    //Download
//                } catch (Exception e) {
//                }
//            }
//        }
    }
}
