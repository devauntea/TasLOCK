package com.example.taslock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addTask extends AppCompatActivity {
    EditText TitleView;
    EditText TimeView;
    EditText TeacherView;
    TextView SubjectView;
    DatabaseReference databasePosts;
    boolean clicked = false;

    TextView Timer;
    int t1hour, t1minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Timer = findViewById(R.id.Timer);
        TitleView = findViewById(R.id.eventTitle);
        TimeView = findViewById(R.id.eventTime);
        TeacherView = findViewById(R.id.teacher);
        SubjectView = findViewById(R.id.subTextView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasePosts = database.getReference().child("Posts");

        Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        addTask.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1hour = hourOfDay;
                                t1minute = minute;

                                String time = t1hour + ":" + t1minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    Timer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1hour,t1minute);
                timePickerDialog.show();
            }
        });



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
        String subject = SubjectView.getText().toString();
        SubjectView.setText("");
        taskedPosts postMessage  = new taskedPosts(title,time,teacher);
        databasePosts.push().setValue(postMessage);

        Intent intent = new Intent( addTask.this, Tasks.class);
        startActivity(intent);


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


}
