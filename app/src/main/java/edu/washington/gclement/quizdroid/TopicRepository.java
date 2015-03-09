package edu.washington.gclement.quizdroid;

import java.util.ArrayList;

/**
 * Created by ginoclement on 3/7/15.
 */
public interface TopicRepository {

    public ArrayList<Topic> getTopics();
    public ArrayList<String> getAnswerOptions();

}
