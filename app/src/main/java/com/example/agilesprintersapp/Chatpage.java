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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Chatpage extends AppCompatActivity {
    //final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/test_WHATSAPP.php";
    ListView ls;
    TextView t ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   getJSON("https://lamp.ms.wits.ac.za/home/s2141916/test_WHATSAPP.php");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);
        ls = (ListView)findViewById(R.id.contactList);

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

                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void loadIntoListView(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        String[] heroes = new String[jsonArray.length()];
        String[] heroes2 = new String[jsonArray.length()];
        String[] heroes3 = new String[jsonArray.length()];
        ArrayList<Chats_list> list = new ArrayList<>();
        Chats_list one = new Chats_list("","","");

        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);

            //getting the name from the json object and putting it inside string array
            heroes[i] = obj.getString("TEXTMESSAGE");
            heroes2[i] = obj.getString("SENDER_NAME");
            heroes3[i] = obj.getString("TIMESTAMP");

            one = new Chats_list(heroes2[i], heroes[i], heroes3[i]);
            list.add(one);
        }

        chatListAdapter adapter = new chatListAdapter(this,R.layout.listview_adapter,list);



        //the array adapter to load data into list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);


        //attaching adapter to listview
        ls.setAdapter(adapter);

    }


}