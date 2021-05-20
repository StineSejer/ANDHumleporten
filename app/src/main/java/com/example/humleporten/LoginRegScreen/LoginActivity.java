package com.example.humleporten.LoginRegScreen;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.humleporten.MainScreen.MainActivity;
import com.example.humleporten.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;
    TextView mCreatebt;
    Button mLoginBt;
    ProgressBar probar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        mCreatebt = findViewById(R.id.createAccount);
        mLoginBt = findViewById(R.id.btLogIn);
        probar1 = findViewById(R.id.progressBar);

        mLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email Address is Required for login");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    mPassword.setError("Password is Required for login");
                    return;
                }
                if (password.length() < 8) {
                    mPassword.setError("Password must be at least 8 characters");
                    return;
                }
               probar1.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed, try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            probar1.setVisibility(View.GONE);
                        }
                    }
                });
            }

        });

        mCreatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });
    }

}

/* Source: https://www.youtube.com/watch?v=TwHmrZxiPA8&ab_channel=SmallAcademy /*/


