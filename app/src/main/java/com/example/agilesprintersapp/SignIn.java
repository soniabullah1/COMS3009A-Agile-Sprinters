package com.example.agilesprintersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignIn extends AppCompatActivity {

    Button Return, Login;
    EditText UsernameLogin, PasswordLogin;
    final String url_loginUser = "https://lamp.ms.wits.ac.za/home/s2141916/L2.php";
    CheckBox chkPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Return = (Button) findViewById(R.id.bReturn);
        Login = findViewById(R.id.bSignIn);
        UsernameLogin = findViewById(R.id.editTextTextPersonName);
        PasswordLogin = findViewById(R.id.editTextTextPassword);

        chkPass = (CheckBox)findViewById(R.id.checkBoxPassword);
        chkPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show Password
                    PasswordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    // Hide Password
                    PasswordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Username = UsernameLogin.getText().toString().trim();
                String Password = PasswordLogin.getText().toString().trim();

                if (TextUtils.isEmpty(Username)) {
                    UsernameLogin.setError("Input Username");
                } else if (TextUtils.isEmpty(Password)) {
                    PasswordLogin.setError("Input Password");
                } else{
                    new loginUser().execute(Username, Password);
                }

            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ReturnIntent = new Intent(SignIn.this, MainActivity.class);
                ReturnIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ReturnIntent);
                finish();
            }
        });

    }

    public class loginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Username = strings[0];
            String Password = strings[1];

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("USERNAME", Username)
                    .add("PASSWORD", Password)
                    .build();

            Request request = new Request.Builder()
                    .url(url_loginUser)
                    .post(formBody)
                    .build();

            Response response = null;



            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String result = response.body().string();

                    if (result.equalsIgnoreCase("login")) {
                        showToast("Login Successful");
                        Intent i = new Intent(SignIn.this, Chatpage.class);
                        i.putExtra(Intent.EXTRA_TEXT,Username);
                        startActivity(i);
                        finish();
                    } else {
                        showToast("Username or Password mismatched!");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void showToast(final String text) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignIn.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
