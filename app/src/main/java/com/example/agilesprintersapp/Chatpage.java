package com.example.agilesprintersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Chatpage extends AppCompatActivity {
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/test_WHATSAPP.php";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   getJSON("https://lamp.ms.wits.ac.za/home/s2141916/test_WHATSAPP.php");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav2);

        bottomNavigationView.setSelectedItemId(R.id.Chats);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Chats:
//                        startActivity(new Intent(getApplicationContext(), ChatWindow.class));
//                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.contacts:
                        startActivity(new Intent(getApplicationContext(), ContactList.class));
                        overridePendingTransition(0,0);
                     return true;

                }
                return false;
            }
        });


    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL("https://lamp.ms.wits.ac.za/home/s2141916/test_WHATSAPP.php");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    StringBuilder sb = new StringBuilder();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while ((json = bufferedReader.readLine()) != null) {

                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


}