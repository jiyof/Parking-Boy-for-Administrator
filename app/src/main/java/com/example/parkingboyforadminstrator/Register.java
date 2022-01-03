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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button loginHere,regUserBtn;
    EditText regFullName,regEmail,regPassword, regConfPass,regMobile;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginHere = findViewById(R.id.registerLoginHere);
        regFullName = findViewById(R.id.registerFullname);
        regEmail = findViewById(R.id.registerEmail);
        regPassword = findViewById(R.id.registerPassword);
        regConfPass = findViewById(R.id.registerConfPass);
        regMobile = findViewById(R.id.registerMobile);
        regUserBtn = findViewById(R.id.registerButton);

        fAuth = FirebaseAuth.getInstance();

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        regUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName= regFullName.getText().toString();
                String eMail= regEmail.getText().toString();
                String passWord = regPassword.getText().toString();
                String confPass = regConfPass.getText().toString();
                String mobileNum = regMobile.getText().toString();


                if(fullName.isEmpty()) {
                    regFullName.setError("Full name is required!");
                    return;
                }

                if(eMail.isEmpty()) {
                    regEmail.setError("Email address is required!");
                    return;
                }

                if(passWord.isEmpty()) {
                    regPassword.setError("Password is required!");
                    return;
                }

                if(confPass.isEmpty()) {
                    regConfPass.setError("Confirmation password is required!");
                    return;
                }

                if(!passWord.equals(confPass)) {
                    regPassword.setError("Passwords do not match!");
                }

                if(mobileNum.isEmpty()) {
                    regMobile.setError("Mobile number is required!");
                    return;
                }

                Toast.makeText(Register.this, "Data is validated.", Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(eMail,passWord).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}