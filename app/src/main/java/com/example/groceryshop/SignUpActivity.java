package com.example.groceryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private TextView SignUptv;
    private EditText SignUpemail, SignUppass, Phoneev, personev;
    private Button signupbtn;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        SignUptv = findViewById(R.id.signuptv);
        SignUpemail = findViewById(R.id.signupemail);
        SignUppass = findViewById(R.id.Signuppass);
        Phoneev = findViewById(R.id.phoneev);
        personev = findViewById(R.id.personev);
        signupbtn = findViewById(R.id.signupbtn);
        progressBar = findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                registerUser();
            }
        });

    }

    public void registerUser() {

        String email = SignUpemail.getText().toString().trim();
        String password = SignUppass.getText().toString().trim();
        String Phone = Phoneev.getText().toString().trim();
        String name = personev.getText().toString().trim();


        if (email.isEmpty()) {

            SignUpemail.setError("Email is required!");
            SignUpemail.requestFocus();
            return;
        }

        if (Phone.isEmpty()) {

            Phoneev.setError("Email is required!");
            Phoneev.requestFocus();
            return;
        }

        if (name.isEmpty()) {

            personev.setError("Email is required!");
            personev.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUpemail.setError("Please provide a valid email!");
            SignUpemail.requestFocus();
            return;
        }


        if (!Patterns.PHONE.matcher(Phone).matches()) {
            Phoneev.setError("Please provide a valid email!");
            Phoneev.requestFocus();
            return;
        }

        if (password.isEmpty()) {

            SignUppass.setError("Password is required!");
            SignUppass.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            UserInfo userinfo = new UserInfo(name, email, Phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "User has been Registered Successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        clearForm((ViewGroup) findViewById(R.id.signupactivity));
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Failed to Register,Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        clearForm((ViewGroup) findViewById(R.id.signupactivity));
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to Register,Try Again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            clearForm((ViewGroup) findViewById(R.id.signupactivity));

                        }
                    }
                });


    }
    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }
}