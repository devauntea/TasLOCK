package com.example.taslock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LognPage extends AppCompatActivity {
    EditText pass, email;
    TextView signinBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn_page);

        pass = findViewById(R.id.Pass2View);
        email = findViewById(R.id.Email2View);
        signinBtn = findViewById(R.id.signview);
        fAuth = FirebaseAuth.getInstance();
//
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mpass = pass.getText().toString().trim();
                String memail = email.getText().toString().trim();

                if(TextUtils.isEmpty(memail)){
                    email.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(mpass)){
                    pass.setError("Password is Required.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(memail,mpass) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LognPage.this, "Successful Sign In.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LognPage.this, Tasks.class));
                        } else {
                            Toast.makeText(LognPage.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


}