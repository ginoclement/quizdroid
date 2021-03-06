package edu.washington.gclement.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;


public class TopicsList extends ActionBarActivity {
    private Map<String, Topic> topics;
//    private ArrayList<Topic> topics_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_list);
        QuizApp app = (QuizApp) getApplication();
//        topics = app.getTopics();
//        topics_list = new ArrayList<>(topics.values());
        TopicsListAdapter topics_adapter = new TopicsListAdapter(this, R.layout.topics_list_row, app.getTopics());
//        Log.i("QuizApp", "Topics: " + topics_list.toString());

        final ListView topics_listview = (ListView) findViewById(R.id.topics_list);
        topics_listview.setAdapter(topics_adapter);
        topics_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextActivity = new Intent(TopicsList.this, Quiz.class);
                Log.i("QuizApp", topics_listview.getItemAtPosition(position).toString());
                Topic topic = (Topic) (topics_listview.getItemAtPosition(position));
                nextActivity.putExtra("Topic", topic);
                Log.i("QuizApp", "Selected topic: " + topic);
                startActivity(nextActivity);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_preferences){
            Intent nextActivity = new Intent(TopicsList.this, PreferencesActivity.class);
            startActivity(nextActivity);
        }
        return false;
    }
}
