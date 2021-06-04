package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {


    private TextView SignUptv;
    private EditText SignUpemail;
    private EditText SignUppass;
    private EditText Phoneev;
    private EditText personev;
    private Button signupbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        SignUptv = findViewById(R.id.signuptv);
        SignUpemail = findViewById(R.id.signupemail);
        SignUppass = findViewById(R.id.Signuppass);
        Phoneev = findViewById(R.id.phoneev);
        personev = findViewById(R.id.personev);
        signupbtn = findViewById(R.id.signupbtn);



    }
}