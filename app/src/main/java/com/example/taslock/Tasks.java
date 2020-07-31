package com.example.taslock;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {
    DatabaseReference databaseTasks;
    boolean clicked = false;
    //FirebaseAuth firebaseAuth;
    //FirebaseAuth.AuthStateListener authStateListener;


    String userid;
    FirebaseUser user;

    ChildEventListener PostsEventListener;
    TaskPostAdapter TaskPostsAdapter;
    ArrayList<taskedPosts> Posts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ListView tasksView = findViewById(R.id.tasked_view);

        final LottieAnimationView lottieClickedSub = findViewById(R.id.lottieAddCreate);
        lottieClickedSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked = true){
                    Intent intent = new Intent( Tasks.this, addTask.class);
                    startActivity(intent);
                    clicked = true;
                }
            }
        });

        TaskPostsAdapter = new TaskPostAdapter(this, Posts);
        tasksView.setAdapter(TaskPostsAdapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseTasks = database.getReference().child(userid);

        databaseTasks.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                taskedPosts task = dataSnapshot.getValue(taskedPosts.class);
                Posts.add(task);
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
    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Tasks.this, MainActivity.class));
    }
}
