package com.example.taslock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {
    atabaseReference databasePosts;
    //FirebaseAuth firebaseAuth;
    //FirebaseAuth.AuthStateListener authStateListener;
    ChildEventListener PostsEventListener;
    TaskPostAdapter ForumPostsAdapter;
    ArrayList<taskedPosts> Posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
    }
}
