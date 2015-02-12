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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "yourAnswer";
    private static final String ARG_PARAM2 = "correctAnswer";
    private static final String ARG_PARAM3 = "numCorrect";
    private static final String ARG_PARAM4 = "numQuestions";
    private static final String ARG_PARAM5 = "moreQuestions";

    // TODO: Rename and change types of parameters
    private String yourAnswer;
    private String correctAnswer;
    private int numCorrect;
    private int numQuestions;
    private boolean moreQuestions;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param yourAnswer The answer the luser provided.
     * @param correctAnswer The correct answer to the question.
     * @param numCorrect Number of questions that luser has gotten correct.
     * @param numQuestions The number of questions in this quiz.
     * @param moreQuestions Whether there are more questions in the quiz.
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        View self = getView();

        TextView correctAnswer = (TextView) self.findViewById(R.id.correctAnswerLabel);
        correctAnswer.setText((CharSequence) correctAnswer);

        TextView yourAnswer = (TextView) self.findViewById(R.id.yourAnswer);
        yourAnswer.setText((CharSequence) yourAnswer);

        TextView correctCount = (TextView) self.findViewById(R.id.correctCount);
        correctCount.setText(getString(R.string.correct_count_text, numCorrect, numQuestions));

        Button next_btn = (Button) self.findViewById(R.id.btn_next);
        next_btn.setText((moreQuestions) ? "Next" : "Finish");
//        next_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("quiz", "More questions: " + moreQuestions);
//                if (moreQuestions){
//                    Intent nextQuestion = new Intent(AnswerSummary.this, AskQuestion.class);
//                    nextQuestion.putExtra("questions", questions);
//                    nextQuestion.putExtra("numCorrect", numCorrect);
//                    nextQuestion.putExtra("numQuestions", numQuestions);
//                    nextQuestion.putExtra("topic", topic);
//                    startActivity(nextQuestion);
//                } else {
//                    Intent topic = new Intent(AnswerSummary.this, Topics.class);
//                    startActivity(topic);
//                }
//                finish();
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
