package com.example.taslock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
        topicTextView.setText(taskmessage.getTitle());

        TextView timeTextView = convertView.findViewById(R.id.timeTextView);
        timeTextView.setText("Due by " + taskmessage.getTime());

        TextView teacherTextView = convertView.findViewById(R.id.teacherTextView);
        teacherTextView.setText("Teacher: " + taskmessage.getTeacher());

        TextView SubTextView = convertView.findViewById(R.id.subTextView);
        SubTextView.setText("Subject: " + taskmessage.getSubject());

//        LinearLayout BoarderView = convertView.findViewById(R.id.boarder);
//        BoarderView.setBackground( "@drawable/btn" + taskmessage.getBoarder());


        return convertView;
    }

}
