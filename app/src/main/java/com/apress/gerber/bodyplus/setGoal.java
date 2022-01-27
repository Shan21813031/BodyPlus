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

public class setGoal extends AppCompatActivity implements View.OnClickListener {

    private View appView;

    private FirebaseUser user;
    private DatabaseReference reference;

    private TextView saveGoals;
    private EditText weightG, bpG, dlG, sqG;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

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

        saveGoals = findViewById(R.id.updateBtn);
        saveGoals.setOnClickListener(this);

        weightG = findViewById(R.id.WeightGoal);
        bpG = findViewById(R.id.benchpressGoal);
        dlG = findViewById(R.id.deadliftGoal);
        sqG = findViewById(R.id.squatGoals);

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
                saveGoals();
                break;
        }

    }

    private void saveGoals() {

        String wGoal = weightG.getText().toString().trim();
        String bGoal = bpG.getText().toString().trim();
        String dGoal = dlG.getText().toString().trim();
        String sGoal = sqG.getText().toString().trim();

        reference.child(userID).child("weightGoal").setValue(wGoal);
        reference.child(userID).child("bpGoal").setValue(bGoal);
        reference.child(userID).child("dlGoal").setValue(dGoal);
        reference.child(userID).child("squatGoal").setValue(sGoal);

        Toast.makeText(this,"Goal successfully set", Toast.LENGTH_LONG).show();
        startActivity(new Intent(setGoal.this, GoalsActivity.class));



    }
}