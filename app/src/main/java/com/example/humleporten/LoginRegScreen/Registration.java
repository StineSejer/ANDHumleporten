package com.example.humleporten.LoginRegScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.example.humleporten.MainScreen.MainActivity;
import com.example.humleporten.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registration extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword;
    FirebaseAuth fAuth;
    TextView mAlreadyU;
    Button mbtRegister;
    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        mAlreadyU = findViewById(R.id.AlreadyUser);
        mbtRegister = findViewById(R.id.btRegister);
        proBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mbtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 8) {
                    mPassword.setError("Password must be more the 8 characters");
                    return;
                }

                proBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Registration.this, "Registration failed, try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            proBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        mAlreadyU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

}

/*
    private TextView tvAlreadyUser;
    private EditText metFullName, metEmailAdd, metPassword, meTRepPassword;
    private Button mbtRegister;
    FirebaseAuth fAuch;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        metFullName = findViewById(R.id.etFullName);
        metEmailAdd = findViewById(R.id.etEmailAdd);
        metPassword = findViewById(R.id.etPassword);
        meTRepPassword = findViewById(R.id.etRepPassword);
        mbtRegister = findViewById(R.id.etRegister);
        tvAlreadyUser = findViewById(R.id.tvAlreadyUser);


            fAuch = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuch.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mbtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = metEmailAdd.getText().toString().trim();
                String password = metPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    metEmailAdd.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    metPassword.setError("Password is required");
                    return;
                }
                if (password.length() < 5)
                {
                    metPassword.setError("Password must be more than 5 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuch.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Registration.this, "Sign In successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Registration.this, "Registation failed! Try again" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


        tvAlreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
*/

