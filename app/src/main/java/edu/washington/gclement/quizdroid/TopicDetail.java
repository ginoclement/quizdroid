package edu.washington.gclement.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TopicDetail extends ActionBarActivity {
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        String timestamp = launchedMe.getStringExtra("timestamp");  // get data that was passed from first activity
        topic = launchedMe.getStringExtra("topic");
        TextView title = (TextView) findViewById(R.id.topic_title);
        title.setText(topic);

        TextView description = (TextView) findViewById(R.id.topic_description);
        description.setText(topic);

        TextView question_count = (TextView) findViewById(R.id.question_count);
        question_count.setText(getString(R.string.num_questions, getQuestionCount(topic)));

        Button btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("quiz", "Starting quiz " + topic);
                Intent nextActivity = new Intent(TopicDetail.this, Quiz.class);
                nextActivity.putExtra("topic", topic);
                startActivity(nextActivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_detail, menu);
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

    private int getQuestionCount(String topic){
        switch(topic){
            case "Math":
                return 3;
            case "Physics":
                return 2;
            case "Marvel Super Heroes":
                return 1;
        }
        return 0;
    }
}
