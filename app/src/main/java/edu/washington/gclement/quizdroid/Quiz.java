package edu.washington.gclement.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Quiz extends ActionBarActivity {
    private Map<String, ArrayList<Question>> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent launchedMe = getIntent();
        String topic = launchedMe.getStringExtra("topic");
        questions = getQuestions();

        for(Question q :questions.get(topic)){
            Intent quesActivity = new Intent(Quiz.this, AskQuestion.class);
            quesActivity.putExtra("question", q);
            startActivity(quesActivity);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
        temp.add(new Question("What is the integration of distance?", new String[]{"Speed", "Velocity", "Acceleration", "Height"}, 1));
        q.put("Physics", temp);

        //Marvel Superheroes Questions
        temp = new ArrayList<Question>();
        String[] c = {"1", "2", "3", "4"};
        temp.add(new Question("Who is not a Marvel Superhero", new String[]{"Iron Man", "Spider-Man", "Captain America", "Hellboy"}, 3));
        q.put("Marvel Super Heroes", temp);
        return q;
    }
}
