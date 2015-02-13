package edu.washington.gclement.quizdroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopicDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopicDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "topic";
    private static final String ARG_PARAM2 = "numQuestions";

    private String topic;
    private int numQuestions;
    private Quiz quizActivity;

    /**
     * @param topic The chosen topic.
     * @return A new instance of fragment TopicDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicDetailFragment newInstance(String topic, int numQuestions) {
        TopicDetailFragment fragment = new TopicDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, topic);
        args.putInt(ARG_PARAM2, numQuestions);
        fragment.setArguments(args);
        return fragment;
    }

    public TopicDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = getArguments().getString(ARG_PARAM1);
            numQuestions = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_detail, container, false);

        TextView title = (TextView) view.findViewById(R.id.topic_title);
        title.setText(topic);

        TextView desc = (TextView) view.findViewById(R.id.topic_description);
        desc.setText(getTopicDetail(topic));

        TextView qCount = (TextView) view.findViewById(R.id.question_count);
        qCount.setText(getString(R.string.num_questions, numQuestions));


        Button begin = (Button) view.findViewById(R.id.btn_begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizActivity.nextQuestion();
            }
        });

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

    private String getTopicDetail(String topic){
        switch (topic){
            case "Math":
                return "Really? You need a description for what math is?";
            case "Physics":
                return "Basically questions about physics. What else would a section called physics be about...";
            case "Marvel Super Heroes":
                return "You're either a nerd, or you're going to fail.";
        }
        return "Apparently this topic doesn't have a description...";
    }
}
