package com.apress.gerber.bodyplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassword extends AppCompatActivity {

    private EditText emailAdd;
    private Button resetP;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailAdd = findViewById(R.id.email);
        resetP = findViewById(R.id.resetBtn);

        auth = FirebaseAuth.getInstance();

        resetP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }


    private void resetPassword(){
        String email = emailAdd.getText().toString().trim();

        if (email.isEmpty()){
            emailAdd.setError("please enter your email address");
            emailAdd.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailAdd.setError("incorrect email address");
            emailAdd.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(resetPassword.this, "Reset Link has been sent to Email" +
                            "Address", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(resetPassword.this, "Something went wrong!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}