package com.example.taslock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskPostAdapter extends ArrayList<taskedPosts> {
    public TaskPostAdapter(Context context, ArrayList<taskedPosts> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.forum_list_item, parent, false);
        forumPosts chatMessage = getItem(position);
        TextView messageTextView = convertView.findViewById(R.id.messageTextView);
        messageTextView.setText(chatMessage.getPostMsg());
        TextView usernameTextView = convertView.findViewById(R.id.usernameTextView);
        usernameTextView.setText(chatMessage.getTitle());
        //Use a cool circular ImageView for the profile pic. This comes from the library dependecy you imported.
        //if there is a profile pic from the authenticated user, load that.
        //otherwise, load the default profile icon.
        return convertView;
    }
}
