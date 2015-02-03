package edu.washington.gclement.quizdroid;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Quiz extends ActionBarActivity {
    private int sel_col;
    private int unsel_col;
    private View current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        sel_col = Color.rgb(0, 200,0);
        unsel_col = Color.rgb(0, 150, 255);
        current = null;

        final Button submit = (Button) findViewById(R.id.submit_btn);
        submit.setClickable(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Submit answer
                Log.i("quiz", "Submitting");
            }
        });
        View.OnClickListener selectAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current != null){
                    current.setBackgroundColor(unsel_col);
                }

                v.setBackgroundColor(sel_col);
                v.setBackgroundColor(sel_col);
                submit.setClickable(true);
                Log.i("quiz", "Selected " + v.toString());
                current = v;

            }
        };
        Button a1 = (Button) findViewById(R.id.answer_1);
        a1.setOnClickListener(selectAnswer);

        Button a2 = (Button) findViewById(R.id.answer_2);
        a2.setOnClickListener(selectAnswer);

        Button a3 = (Button) findViewById(R.id.answer_3);
        a3.setOnClickListener(selectAnswer);

        Button a4 = (Button) findViewById(R.id.answer_4);
        a4.setOnClickListener(selectAnswer);
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
