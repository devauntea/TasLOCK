package com.example.taslock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Plans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);
    }
    public void mainPG3(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}