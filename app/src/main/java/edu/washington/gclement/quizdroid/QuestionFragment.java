package edu.washington.gclement.quizdroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "question";

    private Question question;
    private Quiz quizActivity;


    /**
     * @param question Question to be asked.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, question);
        fragment.setArguments(args);
        Log.i("quiz", "Question: " + question.getQuestion());
        Log.i("quiz", "Answers: " + Arrays.toString(question.getAnswers()));
        return fragment;
    }

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Set question text
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        TextView questionTextView = (TextView) view.findViewById(R.id.question);
        questionTextView.setText(question.getQuestion());

        String[] choices = question.getAnswers();

        //Submit button
        Button submit = (Button) view.findViewById(R.id.btn_submit);
        submit.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check to see if answer was correct
                RadioGroup rg = (RadioGroup) view.findViewById(R.id.choicesGroup);
                View rb = rg.findViewById(rg.getCheckedRadioButtonId());
                quizActivity.showAnswer(rg.indexOfChild(rb));
            }
        });

        //Choices onclick listener
        View.OnClickListener selectAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("quiz", "Selected " + ((RadioButton) v).getText());
                Button submit = (Button) view.findViewById(R.id.btn_submit);
                submit.setVisibility(View.VISIBLE);
            }
        };

        //Buttons
        RadioButton a1 = (RadioButton) view.findViewById(R.id.choice1);
        a1.setText(choices[0]);
        a1.setOnClickListener(selectAnswer);

        RadioButton a2 = (RadioButton) view.findViewById(R.id.choice2);
        a2.setOnClickListener(selectAnswer);
        a2.setText(choices[1]);

        RadioButton a3 = (RadioButton) view.findViewById(R.id.choice3);
        a3.setOnClickListener(selectAnswer);
        a3.setText(choices[2]);

        RadioButton a4 = (RadioButton) view.findViewById(R.id.choice4);
        a4.setOnClickListener(selectAnswer);
        a4.setText(choices[3]);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        quizActivity = (Quiz) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
