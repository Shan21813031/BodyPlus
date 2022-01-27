package com.apress.gerber.bodyplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private View appView;
    private FirebaseAuth appAuth;
    private ProgressBar loadWidget;
    private EditText name, age, emailAddress, password;
    private TextView banner, registerUser;

    private static int REGISTRATION_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /*
        initialisation of decorView function to auto hide system UI navigation
        bar with on system visibility change listener
         */

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        /*
        initialisation of Firebase database use
         */

        appAuth = FirebaseAuth.getInstance();

        banner = findViewById(R.id.pageDescription);
        banner.setOnClickListener(this);

        registerUser = findViewById(R.id.registerBtn);
        registerUser.setOnClickListener(this);

        name = findViewById(R.id.fullName);
        age = findViewById((R.id.uAge));
        emailAddress = findViewById((R.id.email));
        password = findViewById(R.id.password);

        loadWidget = findViewById(R.id.progressCircle);

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

        switch(v.getId()){
            case R.id.registerBtn:
                registerUser();
                break;
        }

    }

    private void registerUser() {

        String email = emailAddress.getText().toString().trim();
        String userAge = age.getText().toString().trim();
        String fullName = name.getText().toString().trim();
        String pWord = password.getText().toString().trim();

        String weight = " ";
        String height = " ";

        String dl = " ";
        String bp = " ";
        String squat = " ";

        String squatGoal = " ";
        String weightGoal = " ";
        String bpGoal = " ";
        String dlGoal = " ";

        String fTri = " ";
        String sTri = " ";
        String fBi = " ";
        String sBi = " ";
        String fSh = " ";
        String sSh = " ";
        String tSh = " ";
        String fLegs = " ";
        String sLegs = " ";
        String tLegs = " ";
        String foLegs = " ";
        String fiLegs = " ";
        String siLegs = " ";
        String fChest = " ";
        String sChest = " ";
        String fAbs = " ";
        String sAbs = " ";
        String tAbs = " ";
        String fBack = " ";
        String sBack = " ";

        /*
        rules of registration entries
         */

        if(email.isEmpty() ) {
            emailAddress.setError("incomplete entry");
            emailAddress.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailAddress.setError("invalid email address");
            return;
        }

        if(pWord.isEmpty()){
            password.setError("incomplete entry");
            password.requestFocus();
            return;
        }

        if (pWord.length() < 8){
            password.setError("password length is below 8 characters");
            password.requestFocus();
            return;
        }

        if(userAge.isEmpty()){
            age.setError("incomplete entry");
            age.requestFocus();
            return;
        }

        if (fullName.isEmpty()){
            name.setError("incomplete entry");
            name.requestFocus();
            return;
        }

        /*
        makes visible the loading circle when "register" button is clicked
         */
        loadWidget.setVisibility(View.VISIBLE);

        /*
        Initialises data and pushes to Firebase Realtime database with checks on task succession
         */
        appAuth.createUserWithEmailAndPassword(email,pWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        /*
                        Data collected passes checks and is passed through User object and submitted to Firebase
                        through an instance of Data
                         */

                        if(task.isSuccessful()){
                            User user = new User(fullName, userAge, email, weight, height, dl, squat, bp,
                                    squatGoal, bpGoal, weightGoal, dlGoal, fTri, sTri, fBi, sBi, fSh, sSh, tSh, fLegs, sLegs, tLegs, foLegs, fiLegs,
                                    siLegs, fChest, sChest, fAbs, sAbs, tAbs, fBack, sBack);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegistrationActivity.this,"Registration complete", Toast.LENGTH_LONG).show();

                                        // allows a delay in between changing screens so user can anticipate it and understand the feedback

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run(){
                                                Intent homeIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                                startActivity(homeIntent);
                                                finish();
                                            }
                                        },REGISTRATION_TIME_OUT);

                                        loadWidget.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(RegistrationActivity.this,"REGISTRATION FAILURE", Toast.LENGTH_LONG).show();
                                        loadWidget.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegistrationActivity.this,"Registration failed", Toast.LENGTH_LONG).show();
                            loadWidget.setVisibility(View.GONE);
                        }
                    }
                });


    }
}