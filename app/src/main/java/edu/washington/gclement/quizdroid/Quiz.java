package edu.washington.gclement.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.Random;


public class Quiz extends ActionBarActivity {
    private Topic topic;
    private ArrayList<Question> questionList;
    private FragmentManager fManager;
    private Question currentQuestion;
    private int numCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        this.topic = (Topic) launchedMe.getSerializableExtra("Topic");
        this.questionList = topic.getQuestions();
        this.numCorrect = 0;

        fManager = getFragmentManager();
        FragmentTransaction tdfTrans = fManager.beginTransaction();
        TopicDetailFragment tdf = TopicDetailFragment.newInstance(topic);
        tdfTrans.add(R.id.quizFrame, tdf);
        tdfTrans.commit();
    }

    public void nextQuestion(){
        Log.i("quiz", "Clicked next questions");
        //Get random question
        Random rand = new Random();
        currentQuestion = questionList.remove(rand.nextInt(questionList.size()));

        //Launch QuestionFragment
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left);
        QuestionFragment questionFragment = QuestionFragment.newInstance(currentQuestion);
        fTransaction.replace(R.id.quizFrame, questionFragment);
        fTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fTransaction.commit();
    }

    public void showAnswer(int guess){
        if(guess == currentQuestion.getCorrectAnswer()){
            numCorrect++;
        }
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left);
        String[] choices = currentQuestion.getAnswers();
        AnswerFragment answerFragment = AnswerFragment.newInstance(choices[guess], choices[currentQuestion.getCorrectAnswer()], numCorrect, topic.getNumQuestions(), !questionList.isEmpty());
        fTransaction.replace(R.id.quizFrame, answerFragment);
        fTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
