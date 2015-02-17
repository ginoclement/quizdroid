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

public class TopicDetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "topic";

    private Topic topic;
    private Quiz quizActivity;

    /**
     * @param topic The chosen topic.
     * @return A new instance of fragment TopicDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicDetailFragment newInstance(Topic topic) {
        TopicDetailFragment fragment = new TopicDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, topic);
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
            topic = (Topic) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_detail, container, false);

        TextView title = (TextView) view.findViewById(R.id.topic_title);
        title.setText(topic.getName());

        TextView desc = (TextView) view.findViewById(R.id.topic_description);
        desc.setText(topic.getLongDesc());

        TextView qCount = (TextView) view.findViewById(R.id.question_count);
        qCount.setText(getString(R.string.num_questions, topic.getNumQuestions()));


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
}
