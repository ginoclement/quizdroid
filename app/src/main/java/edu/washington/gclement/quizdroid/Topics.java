package edu.washington.gclement.quizdroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


public class Topics extends ActionBarActivity {
//    private Map<String, String> topics_list = new TreeMap<String, String>();
    private ArrayList<String> topics_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        ArrayAdapter<String> topics_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics_list);
        topics_list.add("Math");
        topics_list.add("Physics");
        topics_list.add("Marvel Super Heroes");
        final ListView topics_listview = (ListView) findViewById(R.id.topics_list);
        topics_listview.setAdapter(topics_adapter);
        topics_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(Topics.this, TopicDetail.class);
                nextActivity.putExtra("timestamp", new Date().toString());
                String selectedFromList =(String) (topics_listview.getItemAtPosition(position));
                nextActivity.putExtra("Topic", selectedFromList);
                Log.i("Temp", selectedFromList);
                startActivity(nextActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
