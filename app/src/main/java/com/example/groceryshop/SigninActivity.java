package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SigninActivity extends AppCompatActivity {

    private EditText SignMail;
    private EditText SignPass;
    private Button Signinbtn;
    private Button redirectbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        SignMail =  findViewById(R.id.signinemail);
        SignPass =  findViewById(R.id.signinpass);
        Signinbtn = findViewById(R.id.signinbtn);
        redirectbtn = findViewById(R.id.Signuppagedirect);




    }
}