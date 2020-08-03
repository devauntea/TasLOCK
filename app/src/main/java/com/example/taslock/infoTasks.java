package com.example.taslock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class infoTasks extends AppCompatActivity {
    DatabaseReference databaseinfo;
    String userid;
    FirebaseUser user;
    TextView Titleview;
    TextView Subjectview;
    TextView Teacherview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tasks);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();
        databaseinfo = database.getReference().child(userid);
        databaseinfo.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                taskedPosts task = dataSnapshot.getValue(taskedPosts.class);
                Titleview = findViewById(R.id.TitleView);
                Titleview.setText(task.getTitle());
                Subjectview = findViewById(R.id.SubjectView);
                Subjectview.setText(task.getSubject());
                Teacherview = findViewById(R.id.TeacherView);
                Teacherview.setText(task.getTeacher());

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
}