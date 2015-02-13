package edu.washington.gclement.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "yourAnswer";
    private static final String ARG_PARAM2 = "correctAnswer";
    private static final String ARG_PARAM3 = "numCorrect";
    private static final String ARG_PARAM4 = "numQuestions";
    private static final String ARG_PARAM5 = "moreQuestions";

    private String yourAnswer;
    private String correctAnswer;
    private int numCorrect;
    private int numQuestions;
    private boolean moreQuestions;
    private Quiz quizActivity;

    /**
     * @param yourAnswer The answer the luser provided.
     * @param correctAnswer The correct answer to the question.
     * @param numCorrect Number of questions that luser has gotten correct.
     * @param numQuestions The number of questions in this quiz.
     * @param moreQuestions Whether there are more questions in the quiz.
     * @return A new instance of fragment AnswerFragment.
     */
    public static AnswerFragment newInstance(String yourAnswer, String correctAnswer, int numCorrect, int numQuestions, boolean moreQuestions) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, yourAnswer);
        args.putString(ARG_PARAM2, correctAnswer);
        args.putInt(ARG_PARAM3, numCorrect);
        args.putInt(ARG_PARAM4, numQuestions);
        args.putBoolean(ARG_PARAM5, moreQuestions);
        fragment.setArguments(args);
        return fragment;
    }

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            yourAnswer = getArguments().getString(ARG_PARAM1);
            correctAnswer = getArguments().getString(ARG_PARAM2);
            numCorrect = getArguments().getInt(ARG_PARAM3);
            numQuestions = getArguments().getInt(ARG_PARAM4);
            moreQuestions = getArguments().getBoolean(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        TextView correctView = (TextView) view.findViewById(R.id.correctAnswer);
        correctView.setText((CharSequence) correctAnswer);

        TextView answerView = (TextView) view.findViewById(R.id.yourAnswer);
        answerView.setText((CharSequence) yourAnswer);

        TextView correctCount = (TextView) view.findViewById(R.id.correctCount);
        correctCount.setText(getString(R.string.correct_count_text, numCorrect, numQuestions));

        Button btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_next.setText((moreQuestions) ? "Next" : "Finish");
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moreQuestions) {
                    quizActivity.nextQuestion();
                } else {
                    quizActivity.finish();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        quizActivity = (Quiz) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
