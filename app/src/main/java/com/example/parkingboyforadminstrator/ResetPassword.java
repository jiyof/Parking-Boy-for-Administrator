package com.example.parkingboyforadminstrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {
    Button saveNewPassBtn;
    EditText newPassword, confNewPassWord;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newPassword = findViewById(R.id.resPwNewPw);
        confNewPassWord = findViewById(R.id.resPwConfNewPw);

        user = FirebaseAuth.getInstance().getCurrentUser();

        saveNewPassBtn = findViewById(R.id.resetPwSaveBtn);
        saveNewPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newPassword.getText().toString().isEmpty()) {
                    newPassword.setError("Field is required.");
                    return;
                }
                if(confNewPassWord.getText().toString().isEmpty()) {
                    confNewPassWord.setError("Field is required.");
                    return;
                }

                if(!newPassword.getText().toString().equals(confNewPassWord.getText().toString())) {
                    newPassword.setError("Passwords do not match!");
                    return;
                }

                user.updatePassword(newPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ResetPassword.this, "Password has been successfully changed!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}