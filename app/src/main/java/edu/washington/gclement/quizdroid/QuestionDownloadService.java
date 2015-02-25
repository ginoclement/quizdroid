package edu.washington.gclement.quizdroid;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by ginoclement on 2/24/15.
 */
public class QuestionDownloadService extends Service {
    private String url;
    private int interval;

    public void onCreate(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = prefs.getString("prefUpdateURL", "No URL?");
        interval = Integer.parseInt(prefs.getString("prefUpdateInterval", "Hmm..."));
    }

    public void onDestroy(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleCommand(intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    public void handleCommand(Intent intent){
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
        stopSelf();
    }

}
