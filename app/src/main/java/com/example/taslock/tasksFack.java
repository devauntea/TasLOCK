package com.example.taslock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class tasksFack extends AppCompatActivity {

    boolean clicked = false;
    boolean clickedChat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_fack);

        final LottieAnimationView lottieClickNews = findViewById(R.id.lottieAddTask);
        lottieClickNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked = true){
                    Intent intent = new Intent( tasksFack.this, addTask.class);
                    startActivity(intent);
                    clicked = true;
                }
            }
        });

    }

}