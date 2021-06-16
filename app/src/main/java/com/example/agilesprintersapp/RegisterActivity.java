package com.example.agilesprintersapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agilesprintersapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText Name1, Name2, Username, Email, Phone, Pwd, ConfirmPwd;
    CheckBox chkPwd;
    Button btn_Register, btn_return;
    ProgressBar progressBar;

    User user;

    private FirebaseAuth mAuth;
    DatabaseReference reference;

    public String unitTest = "True";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_Register = (Button) findViewById(R.id.button2);

        Name1 = (EditText) findViewById(R.id.FName);
        Name2 = (EditText) findViewById(R.id.LName);
        Username = (EditText) findViewById(R.id.editTextUsername);
        Email = (EditText) findViewById(R.id.editTextEmailAddress);
        Phone = (EditText) findViewById(R.id.editTextPhone);
        Pwd = (EditText) findViewById(R.id.editTextTextPassword2);
        ConfirmPwd = (EditText) findViewById(R.id.editTextTextPassword3);
        chkPwd = (CheckBox) findViewById(R.id.checkBoxPwd);

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

        btn_return = (Button) findViewById(R.id.button4);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LandingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

        });

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        btn_Register = (Button) findViewById(R.id.button2);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fName = Name1.getText().toString().trim();
                String lName = Name2.getText().toString().trim();
                String username = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String phone = Phone.getText().toString().trim();
                String password = Pwd.getText().toString().trim();
                String confirmation = ConfirmPwd.getText().toString().trim();

                if (TextUtils.isEmpty(fName) || TextUtils.isEmpty(lName) || TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmation)) {
                    Name1.setError("Please enter your first name");
                    Name2.setError("Please enter your last name");
                    Username.setError("Please enter a username");
                    Email.setError("Please enter your email address");
                    Phone.setError("Please enter your phone number");
                    Pwd.setError("Please enter a password");
                    ConfirmPwd.setError("Please enter a password");
                    Name1.requestFocus();
                    Name2.requestFocus();
                    Username.requestFocus();
                    Email.requestFocus();
                    Phone.requestFocus();
                    Pwd.requestFocus();
                    ConfirmPwd.requestFocus();
                    Toast.makeText(RegisterActivity.this, "All fields must be filled out", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(fName)) {
                    Name1.setError("Input First Name");
                    Name1.requestFocus();

                } else if (TextUtils.isEmpty(lName)) {
                    Name2.setError("Input Surname");
                    Name2.requestFocus();

                } else if (TextUtils.isEmpty(username)) {
                    Username.setError("Input Username");
                    Username.requestFocus();

                } else if (TextUtils.isEmpty(email)) {
                    Email.setError("Input Email Address");
                    Email.requestFocus();

                } else if (TextUtils.isEmpty(phone)) {
                    Phone.setError("Input Phone Number");
                    Phone.requestFocus();

                } else if (TextUtils.isEmpty(password)) {
                    Pwd.setError("Input Password");
                    Pwd.requestFocus();

                } else if (password.length() < 6) {
                    Pwd.setError("Password must be at least 6 characters");
                    Pwd.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters ", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(confirmation)) {
                    ConfirmPwd.setError("Input Password");
                    ConfirmPwd.requestFocus();

                } else if (!password.equals(confirmation)) {
                    ConfirmPwd.setError("Passwords do not match");
                    ConfirmPwd.requestFocus();
                }

                else{
                    Register(fName, lName, username, email, phone, password);
                    Toast.makeText(getApplicationContext(),"Registration Successful :D",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

        });
    }


    public void Register(String fName, String lName, String username, String email, String phone, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("User").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("imageURL", "default");
                            hashMap.put("firstName", fName);
                            hashMap.put("lastName", lName);
                            hashMap.put("username", username);
                            hashMap.put("email", email);
                            hashMap.put("contactNumber", phone);


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>(){
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        //display a failure message
                                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful - Please try again.", Toast.LENGTH_LONG).show();
                                        unitTest ="False";
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}