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

public class SignUp extends AppCompatActivity {
    EditText name, pass, email;
    TextView signupBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.NameView);
        pass = findViewById(R.id.PassView);
        email = findViewById(R.id.EmailView);
        signupBtn = findViewById(R.id.btnview);
        fAuth = FirebaseAuth.getInstance();



        signupBtn.setOnClickListener(new View.OnClickListener() {
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

                fAuth.createUserWithEmailAndPassword(memail,mpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, Survey.class));
                        } else {
                            Toast.makeText(SignUp.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public void signUp(View view){
        String fullName = name.getText().toString();
        name.setText("First n Last");
        String Password = pass.getText().toString();
        pass.setText("Password123");
        String Email = email.getText().toString();
        email.setText("Email@mail.org");
    };
    public void mainPG2(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}