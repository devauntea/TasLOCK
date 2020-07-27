package com.example.taslock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskPostAdapter extends ArrayAdapter<taskedPosts> {
    public TaskPostAdapter(Context context, ArrayList<taskedPosts> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.tasked_list_iteam, parent, false);
        taskedPosts taskmessage = getItem(position);
        TextView topicTextView = convertView.findViewById(R.id.topicTextView);
        topicTextView.setText(taskmessage.getPostMsg());
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);
        timeTextView.setText(taskmessage.getTitle());
        TextView teacherTextView = convertView.findViewById(R.id.teacherTextView);
        teacherTextView.setText(taskmessage.getTitle());
        //Use a cool circular ImageView for the profile pic. This comes from the library dependecy you imported.
        //if there is a profile pic from the authenticated user, load that.
        //otherwise, load the default profile icon.
        return convertView;
    }

}
