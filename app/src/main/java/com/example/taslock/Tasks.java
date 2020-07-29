package com.example.taslock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {
    DatabaseReference databaseTasks;
    //FirebaseAuth firebaseAuth;
    //FirebaseAuth.AuthStateListener authStateListener;

    ChildEventListener PostsEventListener;
    TaskPostAdapter TaskPostsAdapter;
    ArrayList<taskedPosts> Posts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ListView tasksView = findViewById(R.id.tasked_view);

        TaskPostsAdapter = new TaskPostAdapter(this, Posts);
        tasksView.setAdapter(TaskPostsAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseTasks = database.getReference().child("Posts");

        databaseTasks.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Posts.add(dataSnapshot.getValue(taskedPosts.class));
                TaskPostsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void creat(View view) {
        Intent intent = new Intent(this, addTask.class);
        startActivity(intent);
    }
}
