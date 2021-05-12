package com.example.agilesprintersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    int count = 0;
    private static int SPLASH_TIME_OUT = 8000; //8000
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar prg = (ProgressBar) findViewById(R.id.progresswelcome);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                count ++;
                prg.setProgress(count);
                if (count == 50)
                {
                    timer.cancel();
                }

            }
        }; timer.schedule(timerTask,0,50);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent welcomeIntent = new Intent(MainActivity.this, LandingActivity.class);
                startActivity(welcomeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}