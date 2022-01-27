package com.apress.gerber.bodyplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register, reset;
    private EditText email, password;
    private Button logIn;
    private ProgressBar LoadWidget;

    private FirebaseAuth appAuth;

    private View appView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        //initialisation of variables

        appAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        logIn = findViewById(R.id.signIn);
        logIn.setOnClickListener(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        LoadWidget = findViewById(R.id.progressCircle);

        reset = findViewById(R.id.forgotPassword);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, resetPassword.class));
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus){
            appView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {

        return
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                userSignIn();
                break;

            case R.id.register:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }

    }

    private void userSignIn() {
                /*
                User credential input conversion to String to allow validation process
                 */

        String emailAddress = email.getText().toString().trim();
        String pWord = password.getText().toString().trim();

        if(emailAddress.isEmpty() ) {
            email.setError("incomplete entry, please re-enter");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            email.setError("incorrect entry, please correct and try again");
            email.requestFocus();
            return;
        }

        if(pWord.isEmpty()) {
            password.setError("incorrect entry, please re-enter");
            password.requestFocus();
            return;
        }

        if(pWord.length() < 8){
            password.setError("incorrect entry, please re-enter");
            password.requestFocus();
            return;
        }

        LoadWidget.setVisibility(View.VISIBLE);

        appAuth.signInWithEmailAndPassword(emailAddress, pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    // if task is successful - redirection to the authorised activity

                    startActivity(new Intent(LoginActivity.this, Dashboard.class));
                    LoadWidget.setVisibility(View.GONE);
                }
                else{
                    // if task is unsuccessful - throws user back to login page to correct
                    // their login attempt

                    Toast.makeText(LoginActivity.this, "incorrect password or email, please try again", Toast.LENGTH_LONG).show();
                    LoadWidget.setVisibility(View.GONE);
                }
            }
        });



    }
}