package edu.washington.gclement.quizdroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;


public class AskQuestion extends ActionBarActivity {
    private int sel_col;
    private int unsel_col;
    private RadioButton current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        Intent launchedMe = getIntent();
        final Question q = (Question) launchedMe.getSerializableExtra("question");
//        topic = launchedMe.getStringExtra("Topic");

        sel_col = Color.rgb(0, 200, 0);
        unsel_col = Color.rgb(0, 150, 255);
        current = null;
        String[] choices = q.getAnswers();
        Log.i("question", "Answers: " + Arrays.toString(choices));

        //Question
        TextView question = (TextView) findViewById(R.id.question);
        Log.i("question", "Question: " + q.getQuestion());
        question.setText(q.getQuestion());

        //Submit button
        final Button submit = (Button) findViewById(R.id.submit_btn);
        submit.setClickable(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Submit answer
                Log.i("quiz", "Submitting");
                boolean correct = q.isCorrect(current.getText());

                Log.i("question", "Guess was " + correct);

                //Starting answer summary for that question
                Intent ansActivity = new Intent(AskQuestion.this, AnswerSummary.class);
                ansActivity.putExtra("question", q);
                startActivity(ansActivity);

            }
        });

        //Choices onclick listener
        View.OnClickListener selectAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current != null){
//                    current.setBackgroundColor(unsel_col);
                }

//                v.setBackgroundColor(sel_col);
//                v.setBackgroundColor(sel_col);
                submit.setClickable(true);
                Log.i("quiz", "Selected " + v.toString());
                current = (RadioButton) v;

            }
        };

        //Buttons
        RadioButton a1 = (RadioButton) findViewById(R.id.choice1);
        a1.setText(choices[0]);
        a1.setOnClickListener(selectAnswer);

        RadioButton a2 = (RadioButton) findViewById(R.id.choice2);
        a2.setOnClickListener(selectAnswer);
        a2.setText(choices[1]);

        RadioButton a3 = (RadioButton) findViewById(R.id.choice3);
        a3.setOnClickListener(selectAnswer);
        a3.setText(choices[2]);

        RadioButton a4 = (RadioButton) findViewById(R.id.choice4);
        a4.setOnClickListener(selectAnswer);
        a4.setText(choices[3]);
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
