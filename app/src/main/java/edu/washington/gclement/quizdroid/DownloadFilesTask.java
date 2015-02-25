package edu.washington.gclement.quizdroid;

import android.os.AsyncTask;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by ginoclement on 2/24/15.
 */
public class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
    protected long doInBackground(URL... urls) {

       Toast t = Toast.makeText(this, urls, Toast.LENGTH_SHORT);
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(Long result) {

    }
}
