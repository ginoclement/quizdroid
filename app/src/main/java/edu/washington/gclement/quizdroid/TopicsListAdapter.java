package edu.washington.gclement.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ginoclement on 2/17/15.
 */
public class TopicsListAdapter extends ArrayAdapter<Topic> {
    Context context;
    int layoutResourceId;
    ArrayList<Topic> data = null;

    public TopicsListAdapter(Context context, int layoutResourceId, ArrayList<Topic> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TopicListHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TopicListHolder();
            holder.topicImage = (ImageView)row.findViewById(R.id.tListImage);
            holder.topicName = (TextView)row.findViewById(R.id.tListTitle);
            holder.topicDesc = (TextView)row.findViewById(R.id.tListDesc);

            row.setTag(holder);
        }
        else
        {
            holder = (TopicListHolder)row.getTag();
        }

        Topic topic = data.get(position);
        holder.topicName.setText(topic.getName());
        holder.topicDesc.setText(topic.getShortDesc());
        holder.topicImage.setImageResource(topic.getImageResource());

        return row;
    }


    static class TopicListHolder {
        ImageView topicImage;
        TextView topicName;
        TextView topicDesc;
    }
}
