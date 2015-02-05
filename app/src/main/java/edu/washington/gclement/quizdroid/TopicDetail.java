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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        description.setText(getTopicDetail(topic));

        TextView question_count = (TextView) findViewById(R.id.question_count);
        question_count.setText(getString(R.string.num_questions, getQuestionCount(topic)));

        Button btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("quiz", "Starting quiz " + topic);
                Intent nextActivity = new Intent(TopicDetail.this, AskQuestion.class);

                //Gather quiz questions
                ArrayList<Question> questions = getQuestionsForTopic(topic);
                nextActivity.putExtra("questions", questions);
                nextActivity.putExtra("numCorrect", 0);
                nextActivity.putExtra("numQuestions", questions.size());
                nextActivity.putExtra("topic", topic);
                startActivity(nextActivity);
                finish();
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

    private String getTopicDetail(String topic){
        switch (topic){
            case "Math":
                return "Really? You need a description for what math is?";
            case "Physics":
                return "Basically questions about physics. What else would a section called physics be about...";
            case "Marvel Super Heroes":
                return "You're either a nerd, or you're going to fail.";
        }
        return "Apparently this topic doesn't have a description...";
    }

    private int getQuestionCount(String topic){
        return getQuestionsForTopic(topic).size();
    }

    private ArrayList<Question> getQuestionsForTopic(String topic){
       return getQuestions().get(topic);
    }

    private Map<String, ArrayList<Question>> getQuestions(){
        Map<String, ArrayList<Question>> q = new HashMap<>();

        //Math Questions
        ArrayList<Question> temp = new ArrayList<Question>();
        temp.add(new Question("What is 2 + 2", new String[]{"1", "2", "3", "4"}, 3));
        temp.add(new Question("What is the square root of 144", new String[]{"8", "10", "12", "14"}, 2));
        temp.add(new Question("What is the derivative of x", new String[]{"0", "1", "x", "2x"}, 1));
        q.put("Math", temp);
        //Physic Questions
        temp = new ArrayList<Question>();
        temp.add(new Question("What is m in the equation 'f=ma'?", new String[]{"Material", "Momentum", "Mass", "Friction"}, 2));
        temp.add(new Question("What is the derivative of position?", new String[]{"Speed", "Velocity", "Acceleration", "Height"}, 1));
        q.put("Physics", temp);

        //Marvel Superheroes Questions
        temp = new ArrayList<Question>();
        temp.add(new Question("Who is not a Marvel Superhero", new String[]{"Iron Man", "Spider-Man", "Captain America", "Hellboy"}, 3));
        q.put("Marvel Super Heroes", temp);
        return q;
    }
}
