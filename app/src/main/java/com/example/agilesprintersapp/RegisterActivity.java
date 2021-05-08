package com.example.agilesprintersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.agilesprintersapp.Model.User;
import com.example.agilesprintersapp.Model.UserInfo;
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
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
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
                    Toast.makeText(RegisterActivity.this, "All fields must be filled out", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(fName)) {
                    Name1.setError("Input First Name");

                } else if (TextUtils.isEmpty(lName)) {
                    Name2.setError("Input Surname");

                } else if (TextUtils.isEmpty(username)) {
                    Username.setError("Input Username");

                } else if (TextUtils.isEmpty(email)) {
                    Email.setError("Input Email Address");

                } else if (TextUtils.isEmpty(phone)) {
                    Phone.setError("Input Phone Number");

                } else if (TextUtils.isEmpty(password)) {
                    Pwd.setError("Input Password");

                } else if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters ", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(confirmation)) {
                    ConfirmPwd.setError("Input Password");

                } else if (!password.equals(confirmation)) {
                    ConfirmPwd.setError("Passwords do not match");
                }

                else{
                    Register(fName, lName, username, email, phone, password);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

        });
    }


    private void Register(String fName, String lName, String username, String email, String phone, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();

//                            user = new UserInfo(userID, imageURL, fName, lName, username, email, phone);
//
//                            user.setId(userID);
//                            user.setImageURL(imageURL);
//                            user.setFirstName(fName);
//                            user.setLastName(lName);
//                            user.setUsername(username);
//                            user.setEmail(email);
//                            user.setPhoneNumber(phone);

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

//                                    FirebaseDatabase.getInstance().getReference("UserInfo")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
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