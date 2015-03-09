package edu.washington.gclement.quizdroid;

import android.app.DownloadManager;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ginoclement on 2/24/15.
 */
public class QuestionDownloadService extends IntentService {
    public static final String TAG = IntentService.class.getSimpleName();
    private String url;
    private int interval;

    public QuestionDownloadService(){
        super(TAG);
    }

    @Override
    public void onHandleIntent(Intent intent){
//        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = prefs.getString("prefUpdateURL", "No URL?");
        interval = Integer.parseInt(prefs.getString("prefUpdateInterval", "Hmm..."));
        Log.i("QuizApp", "Attempting to download from " + url);
        Log.i("QuizApp", "Downloading to " + getFilesDir() + "/quizdata.json");
        try {
            BufferedReader input = null;
            PrintWriter output = null;
            try {
                URL download = new URL(url);
                input = new BufferedReader(new InputStreamReader(download.openStream()));
                output = new PrintWriter(new FileOutputStream(getFilesDir() + "/quizdata.json"));

                String line;
                while ((line = input.readLine()) != null) {
                    output.println(line);
                    Log.i("QuizApp", "Reading string: " + line);
                }
            } finally {
                if (input != null){
                    input.close();
                }
                if (output != null){
                    output.close();
                }
            }
        } catch (MalformedURLException e) {
            Log.i("QuizApp", "Malformed URL");
        } catch (IOException e) {
            Log.i("QuizApp", "IO Exception");
        }

    }

}
