package com.example.agilesprintersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {


    private Button Signin;
    private Button Signup;

    //final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Signin= (Button) findViewById(R.id.SignIn);
        Signup = (Button) findViewById(R.id.SignUp);


        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignInIntent = new Intent(MainActivity.this, SignIn.class);
                SignInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(SignInIntent);
                finish();
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignUpIntent = new Intent(MainActivity.this, SignIn.class);
                SignUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(SignUpIntent);
                finish();
            }
        });
    }


}