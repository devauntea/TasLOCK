package com.example.taslock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addTask extends AppCompatActivity {
    EditText TitleView;
    EditText TimeView;
    EditText TeacherView;
    DatabaseReference databasePosts;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        TitleView = findViewById(R.id.eventTitle);
        TimeView = findViewById(R.id.eventTime);
        TeacherView = findViewById(R.id.teacher);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasePosts = database.getReference().child("Posts");



//        final LottieAnimationView lottieClickFloat = findViewById(R.id.lottieClickSend);
//        lottieClickFloat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clicked = true){         String title = TitleView.getText().toString();
//                    TitleView.setText("");
//                    String post = PostView.getText().toString();
//                    PostView.setText("");
//                    Intent intent = new Intent( addTask.this, Tasks.class);
//                    startActivity(intent);
//
//                    taskedPosts postMessage  = new taskedPosts(title,post);
//                    databasePosts.push().setValue(postMessage);
//                    closeKeyboard();
//                    clicked = false;
//                }
//            }
//        });

    }

    public void task(View view){
        String title = TitleView.getText().toString();
        TitleView.setText("");
        String time = TimeView.getText().toString();
        TimeView.setText("");
        String teacher = TeacherView.getText().toString();
        TeacherView.setText("");

        Intent intent = new Intent( addTask.this, Tasks.class);
        startActivity(intent);

        taskedPosts postMessage  = new taskedPosts(title,time,teacher);
        databasePosts.push().setValue(postMessage);
        closeKeyboard();
        clicked = false;
    }

    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public void add(View view) {
        Intent intent = new Intent(this, Tasks.class);
        startActivity(intent);
    }

}
