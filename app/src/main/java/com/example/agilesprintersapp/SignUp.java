package com.example.agilesprintersapp;

import androidx.appcompat.app.AppCompatActivity;

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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {

    EditText Name1,Name2,Username,Email,Phone,Pwd, ConfirmPwd;
    CheckBox chkPwd;
    final String url_Register = "https://lamp.ms.wits.ac.za/home/s2141916/R2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name1= (EditText) findViewById(R.id.FName);
        Name2 = (EditText) findViewById(R.id.LName);
        Username = (EditText) findViewById(R.id.editTextUsername);
        Email = (EditText) findViewById(R.id.editTextEmailAddress);
        Phone = (EditText) findViewById(R.id.editTextPhone);
        Pwd = (EditText) findViewById(R.id.editTextTextPassword2);
        ConfirmPwd = (EditText) findViewById(R.id.editTextTextPassword3);
        chkPwd = (CheckBox)findViewById(R.id.checkBoxPwd);

        chkPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show Password
                    Pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ConfirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    // Hide Password
                    Pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ConfirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        Button Return = (Button) findViewById(R.id.button4);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, MainActivity.class);
                SignUp.this.startActivity(i);
            }
        });
        Button SignUp = (Button) findViewById(R.id.button2);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserName = Username.getText().toString().trim();
                String FirstName = Name1.getText().toString().trim();
                String LastName = Name2.getText().toString().trim();
                String Email2 = Email.getText().toString().trim();
                String Phone2 = Phone.getText().toString().trim();
                String Password = Pwd.getText().toString().trim();
                String ConfirmPWD = ConfirmPwd.getText().toString().trim();

                if (TextUtils.isEmpty(FirstName)) {
                    Name1.setError("Input First Name");

                } else if (TextUtils.isEmpty(LastName)) {
                    Name2.setError("Input Surname");

                } else if (TextUtils.isEmpty(UserName)) {
                    Username.setError("Input Username");

                } else if (TextUtils.isEmpty(Email2)) {
                    Email.setError("Input Email Address");

                } else if (TextUtils.isEmpty(Phone2)) {
                    Phone.setError("Input Phone Number");

                } else if (TextUtils.isEmpty(Password)) {
                    Pwd.setError("Input Password");

                } else if (TextUtils.isEmpty(ConfirmPWD)) {
                    ConfirmPwd.setError("Input Password");

                } else if(!Password.equals(ConfirmPWD)){
                    ConfirmPwd.setError("Passwords do not match");

                } else{
                    new Register_User().execute(UserName,FirstName, LastName, Email2,Phone2,Password);

                }
            }
        });

    }

    public class Register_User extends AsyncTask<String,Void,String> {

        @Override

        protected String doInBackground(String... strings) {
            String Username = strings[0];
            String FirstN = strings[1];
            String LastN = strings[2];
            String Email = strings[3];
            String Phone = strings[4];
            String Password = strings[5];

            String finalURL = url_Register + "?USERNAME=" + Username +
                    "&FNAME=" + FirstN +
                    "&LNAME=" + LastN +
                    "&EMAIL=" + Email +
                    "&CONTACT_NO=" + Phone +
                    "&PASSWORD=" + Password;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(finalURL)
                    .get()
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String result = response.body().string();

                    if (result.equalsIgnoreCase("User registered successfully")) {

                        showToast("Registration successful");
                        Intent i = new Intent(SignUp.this,  SignIn.class);
                        startActivity(i);
                        finish();

                    } else if (result.equalsIgnoreCase("User already exists")) {
                        showToast("User already exists");
                    } else {
                        showToast("oops! please try again!");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final String text)
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignUp.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }

}