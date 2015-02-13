package edu.washington.gclement.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Quiz extends ActionBarActivity {
    private ArrayList<Question> questionList;
    private FragmentManager fManager;
    private Question currentQuestion;
    private int numCorrect;
    private int numQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        final String topic = launchedMe.getStringExtra("topic");
        questionList = getQuestionsForTopic(topic);
        numQuestions = questionList.size();

        fManager = getFragmentManager();
        FragmentTransaction tdfTrans = fManager.beginTransaction();
        TopicDetailFragment tdf = TopicDetailFragment.newInstance(topic, numQuestions);
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
        QuestionFragment questionFragment = QuestionFragment.newInstance(currentQuestion);
        fTransaction.replace(R.id.quizFrame, questionFragment);
        fTransaction.commit();
    }

    public void showAnswer(int guess){
        if(currentQuestion.isCorrect(guess)){
            numCorrect++;
        }
        FragmentTransaction fTransaction = fManager.beginTransaction();
        AnswerFragment answerFragment = AnswerFragment.newInstance(currentQuestion.getAnswers()[guess], currentQuestion.getCorrectAnswer(), numCorrect, numQuestions, !questionList.isEmpty());
        fTransaction.replace(R.id.quizFrame, answerFragment);
        fTransaction.commit();
    }


    /*
    * These are all topics relating to the topic and questions
    */
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
