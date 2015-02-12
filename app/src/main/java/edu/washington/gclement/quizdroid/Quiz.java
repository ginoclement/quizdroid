package edu.washington.gclement.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Quiz extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Get data from parent activity
        Intent launchedMe = getIntent();
        final ArrayList<Question> questions = (ArrayList<Question>) launchedMe.getSerializableExtra("questions");
        final int numQuestions = launchedMe.getIntExtra("numQuestions", 0);
        final int numCorrect = launchedMe.getIntExtra("numCorrect", 0);
        final String topic = launchedMe.getStringExtra("topic");

        //Get random question
        Random rand = new Random();
        final Question q = questions.remove(rand.nextInt(questions.size()));

        final String[] choices = q.getAnswers();
        Log.i("quiz", "Answers: " + Arrays.toString(choices));


        FragmentManager fManager = getFragmentManager();

        //Launch QuestionFragment
        FragmentTransaction fTransaction = fManager.beginTransaction();
        QuestionFragment questionFragment = QuestionFragment.newInstance(choices, q.getQuestion());

        //Launch AnswerFragment
        String yourAnswer = "Some answer";
        AnswerFragment answerFragment = AnswerFragment.newInstance(yourAnswer, q.getCorrectAnswer(), numCorrect, numQuestions, questions.size() > 0);

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
}
