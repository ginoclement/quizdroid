package edu.washington.gclement.quizdroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ginoclement on 2/17/15.
 * As a developer, use the "Repository" pattern to create a TopicRepository interface;
 * create one implementation that simply stores elements in memory from a hard-coded list initialized on startup.
 */
public class TopicRepository {
    private Map<String, Topic> topics;

    public TopicRepository(){
        topics = generateTopics();
    }

    public Map<String, Topic> getTopics(){
        return this.topics;
    }

    private Map<String, Topic> generateTopics(){
        Map<String, Topic> topics = new HashMap<String, Topic>();

        String name = "";
        String shortDesc = "";
        String longDesc = "";
        ArrayList<Question> questions = new ArrayList<Question>();

        //Math Questions
        name = "Math";
        shortDesc = "Questions about math";
        longDesc = "Really? You need a description for what math is?";
        questions.add(new Question("What is 2 + 2", new String[]{"1", "2", "3", "4"}, 3));
        questions.add(new Question("What is the square root of 144", new String[]{"8", "10", "12", "14"}, 2));
        questions.add(new Question("What is the derivative of x", new String[]{"0", "1", "x", "2x"}, 1));
        Topic topic = new Topic(name, shortDesc, longDesc, questions);
        topics.put(name, topic);

        //Physic Questions
        name = "Physics";
        shortDesc = "Questions about physics";
        longDesc = "Basically questions about physics. What else would a section called physics be about...";
        questions = new ArrayList<Question>();
        questions.add(new Question("What is m in the equation 'f=ma'?", new String[]{"Material", "Momentum", "Mass", "Friction"}, 2));
        questions.add(new Question("What is the derivative of position?", new String[]{"Speed", "Velocity", "Acceleration", "Height"}, 1));
        topic = new Topic(name, shortDesc, longDesc, questions);
        topics.put(name, topic);

        //Marvel Superheroes Questions
        name = "Marvel Super Heroes";
        shortDesc = "Questions about superheroes";
        longDesc = "You're either a nerd, or you're going to fail.";
        questions = new ArrayList<Question>();
        questions.add(new Question("Who is not a Marvel Superhero", new String[]{"Iron Man", "Spider-Man", "Captain America", "Hellboy"}, 3));
        topic = new Topic(name, shortDesc, longDesc, questions);
        topics.put(name, topic);

        return topics;
    }
}
