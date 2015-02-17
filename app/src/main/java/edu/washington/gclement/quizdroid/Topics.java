package edu.washington.gclement.quizdroid;

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
import java.util.Date;
import java.util.Map;


public class Topics extends ActionBarActivity {
    private Map<String, Topic> topics;
    private ArrayList<String> topics_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        topics_list = new ArrayList<String>();
        ArrayAdapter<String> topics_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics_list);

        QuizApp app = (QuizApp) getApplication();
        topics = app.getTopicRepo().getTopics();

        for(String topic : topics.keySet()){
            topics_adapter.add(topic);
        }

        final ListView topics_listview = (ListView) findViewById(R.id.topics_list);
        topics_listview.setAdapter(topics_adapter);
        topics_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(Topics.this, Quiz.class);
                Topic topic = topics.get((String) (topics_listview.getItemAtPosition(position)));
                nextActivity.putExtra("Topic", topic);
                Log.i("QuizApp", "Selected topic: " + topic.getName());
                startActivity(nextActivity);
            }
        });
    }
}
