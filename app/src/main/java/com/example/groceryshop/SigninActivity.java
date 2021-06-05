package com.example.groceryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {

    private EditText SignMail, SignPass;
    private Button Signinbtn, redirectbtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        SignMail = findViewById(R.id.signinemail);
        SignPass = findViewById(R.id.signinpass);
        Signinbtn = findViewById(R.id.signinbtn);
        redirectbtn = findViewById(R.id.Signuppagedirect);
        progressBar = findViewById(R.id.progressBar2);


        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);

        redirectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUpActivity();
            }

            public void OpenSignUpActivity() {
                Intent intent = new Intent(SigninActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                UserLogin();
            }
        });


    }


    public void UserLogin() {


        String email = SignMail.getText().toString().trim();
        String password = SignPass.getText().toString().trim();

        if (email.isEmpty()) {

            SignMail.setError("Email is required!");
            SignMail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignPass.setError("Please provide a valid email!");
            SignPass.requestFocus();
            return;
        }


        if (password.isEmpty()) {

            SignPass.setError("Password is required!");
            SignPass.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            OpenDAshBoard();

                        } else {
                            clearForm((ViewGroup) findViewById(R.id.Signinactivity));
                            Toast.makeText(SigninActivity.this, "Failed to Log in,Try Again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });


    }

    public void OpenDAshBoard() {
        Intent intent = new Intent(SigninActivity.this, UserDashBoard.class);
        startActivity(intent);
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }

}