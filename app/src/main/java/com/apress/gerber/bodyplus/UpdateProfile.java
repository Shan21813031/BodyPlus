package com.apress.gerber.bodyplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    private View appView;

    private FirebaseUser user;
    private DatabaseReference reference;

    private EditText weight, height, age, benchP, squatP, deadL;
    private TextView saveUser;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        saveUser = findViewById(R.id.updateBtn);
        saveUser.setOnClickListener(this);

        weight = findViewById(R.id.uWeight);
        height = findViewById(R.id.uHeight);
        age = findViewById(R.id.uAge);
        squatP = findViewById(R.id.uSM);
        benchP = findViewById(R.id.uBP);
        deadL = findViewById(R.id.uDL);



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
            case R.id.updateBtn:
                saveUser();
                break;
        }
    }


    private void saveUser(){

        String weightStr = weight.getText().toString().trim();
        String heightStr = height.getText().toString().trim();
        String ageStr = age.getText().toString().trim();
        String benchpStr = benchP.getText().toString().trim();
        String deadlStr = deadL.getText().toString().trim();
        String squatStr = squatP.getText().toString().trim();

        reference.child(userID).child("weight").setValue(weightStr);
        reference.child(userID).child("height").setValue(heightStr);
        reference.child(userID).child("dl").setValue(deadlStr);
        reference.child(userID).child("bp").setValue(benchpStr);
        reference.child(userID).child("age").setValue(ageStr);
        reference.child(userID).child("squat").setValue(squatStr);

        Toast.makeText(this,"User Profile updated successfully", Toast.LENGTH_LONG).show();

        startActivity(new Intent(UpdateProfile.this, ProfileActivity.class));


    }

}

