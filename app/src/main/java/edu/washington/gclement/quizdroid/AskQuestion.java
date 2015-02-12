package edu.washington.gclement.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class AskQuestion extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

//        //Get data from parent activity
//        Intent launchedMe = getIntent();
//        final ArrayList<Question> questions = (ArrayList<Question>) launchedMe.getSerializableExtra("questions");
//        final int numQuestions = launchedMe.getIntExtra("numQuestions", 0);
//        final int numCorrect = launchedMe.getIntExtra("numCorrect", 0);
//        final String topic = launchedMe.getStringExtra("topic");
//
//        //Get random question
//        Random rand = new Random();
//        final Question q = questions.remove(rand.nextInt(questions.size()));
//
//        final String[] choices = q.getAnswers();
//        Log.i("quiz", "Answers: " + Arrays.toString(choices));
//
//        //Question
//        TextView question = (TextView) findViewById(R.id.question);
//        Log.i("quiz", "Question: " + q.getQuestion());
//        question.setText(q.getQuestion());

//        //Submit button
//        Button submit = (Button) findViewById(R.id.submit_btn);
//        submit.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Submit answer
                Log.i("quiz", "Submitting");

                //Check to see if answer was correct
                RadioGroup rg = (RadioGroup) findViewById(R.id.choicesGroup);
                View rb = rg.findViewById(rg.getCheckedRadioButtonId());
                int index = rg.indexOfChild(rb);
                boolean correct = q.isCorrect(index);
                int tempNumCorrect = numCorrect + ((correct) ? 1 : 0);
                Log.i("quiz", "Guess was " + correct);

                //Starting answer summary for that question
                Intent ansActivity = new Intent(AskQuestion.this, AnswerSummary.class);
                ansActivity.putExtra("topic", topic);
                ansActivity.putExtra("guess", choices[index]);
                ansActivity.putExtra("numCorrect", tempNumCorrect);
                ansActivity.putExtra("numQuestions", numQuestions);
                ansActivity.putExtra("questions", questions);
                ansActivity.putExtra("prevQuestion", q);

                startActivity(ansActivity);

            }
        });

//        //Choices onclick listener
//        View.OnClickListener selectAnswer = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("quiz", "Selected " + ((RadioButton) v).getText());
//                Button submit = (Button) findViewById(R.id.submit_btn);
//                submit.setVisibility(View.VISIBLE);
//            }
//        };

//        //Buttons
//        RadioButton a1 = (RadioButton) findViewById(R.id.choice1);
//        a1.setText(choices[0]);
//        a1.setOnClickListener(selectAnswer);
//
//        RadioButton a2 = (RadioButton) findViewById(R.id.choice2);
//        a2.setOnClickListener(selectAnswer);
//        a2.setText(choices[1]);
//
//        RadioButton a3 = (RadioButton) findViewById(R.id.choice3);
//        a3.setOnClickListener(selectAnswer);
//        a3.setText(choices[2]);
//
//        RadioButton a4 = (RadioButton) findViewById(R.id.choice4);
//        a4.setOnClickListener(selectAnswer);
//        a4.setText(choices[3]);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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

