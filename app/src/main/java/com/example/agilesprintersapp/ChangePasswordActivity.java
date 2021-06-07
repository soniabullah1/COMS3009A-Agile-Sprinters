package com.example.agilesprintersapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText NewPasswordText, ConfirmPasswordText;
    Button Btn_Change;
    Button Btn_ReturnHome;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        NewPasswordText = findViewById(R.id.NewPasswordText);
        ConfirmPasswordText = findViewById(R.id.ConfirmPasswordText);
        Btn_Change = findViewById(R.id.Btn_Change);
        Btn_ReturnHome = findViewById(R.id.Btn_ReturnHome);

        Btn_ReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        Btn_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = NewPasswordText.getText().toString().trim();
                String ConfirmPassword = ConfirmPasswordText.getText().toString().trim();

                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(ConfirmPassword)) {
                    Toast.makeText(ChangePasswordActivity.this, "All fields must be filled out", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(password)) {
                    NewPasswordText.setError("Input Password");

                } else if (password.length() < 6) {
                    Toast.makeText(ChangePasswordActivity.this, "Password must be at least 6 characters ", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(ConfirmPassword)) {
                    ConfirmPasswordText.setError("Input Password");

                } else if (!password.equals(ConfirmPassword)) {
                    ConfirmPasswordText.setError("Passwords do not match");
                }

                else{
                    ChangePassword(password, ConfirmPassword);
                }
                Intent intent = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


    }

    public void ChangePassword(String password, String ConfrimPassword) {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String newPassword= ConfrimPassword;

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ChangePasswordActivity.this, "Password successfully changed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ChangePasswordActivity.this, "Password failed to update", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}