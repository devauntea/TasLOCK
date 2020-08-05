package com.example.taslock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    public static final int RC_SIGN_IN = 1;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e("newToken", newToken);
            this.getPreferences(Context.MODE_PRIVATE).edit().putString("fb", newToken).apply();
        });

        Log.d("newToken", this.getPreferences(Context.MODE_PRIVATE).getString("fb", "empty :("));


        firebaseAuth = FirebaseAuth.getInstance();


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //user is signed in
                    Toast.makeText(MainActivity.this, "Signed in.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Tasks.class);
                    startActivity(intent);
//                    onSignedIn(user.getDisplayName(), user.getPhotoUrl());
                } else {
                    //user is signed out
                }
            }
        };

    }


    /*public void test(View view) {
        Intent intent = new Intent(this, Tasks.class);
        startActivity(intent);
    }*/
    public void plans(View view) {
        Intent intent = new Intent(this, Plans.class);
        startActivity(intent);
    }
    public void goSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void Signin(View view){
        Intent intent = new Intent(this, LognPage.class);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
