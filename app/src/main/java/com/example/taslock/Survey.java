package com.example.taslock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class Survey extends AppCompatActivity {

    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        final LottieAnimationView lottieClickedSub = findViewById(R.id.lottieSub);
        lottieClickedSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked = true){
                    lottieClickedSub.playAnimation();
                    Intent intent = new Intent( Survey.this, tasksFack.class);
                    startActivity(intent);
                    clicked = true;
                }
            }
        });
    }
}
