package com.apress.gerber.bodyplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Body extends AppCompatActivity {

    private View appView;
    Button optA, optB, optC, optD, quitBtn;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

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

        optA = (Button) findViewById(R.id.optionA);
        optB = (Button) findViewById(R.id.optionB);
        optC = (Button) findViewById(R.id.optionC);
        optD = (Button) findViewById(R.id.optionD);

        quitBtn = (Button) findViewById(R.id.Quit);

        //Database update of user input

        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fChest").setValue("Bench Press");
                reference.child(userID).child("sChest").setValue("Fly Machine");
                reference.child(userID).child("fBack").setValue("Pull Ups");
                reference.child(userID).child("sBack").setValue("Lat Row");
                reference.child(userID).child("fAbs").setValue("Crunches");
                reference.child(userID).child("sAbs").setValue("Leg Raises");
                reference.child(userID).child("tAbs").setValue("Planks");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Body.this, RoutineActivity.class));

            }
        });
        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fChest").setValue("DB Press");
                reference.child(userID).child("sChest").setValue("Cable Fly");
                reference.child(userID).child("fBack").setValue("Lat Pull Down");
                reference.child(userID).child("sBack").setValue("Bent Over Row");
                reference.child(userID).child("fAbs").setValue("Crunches");
                reference.child(userID).child("sAbs").setValue("Leg Raises");
                reference.child(userID).child("tAbs").setValue("Planks");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Body.this, RoutineActivity.class));

            }
        });
        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fChest").setValue("Vertical Press Machine");
                reference.child(userID).child("sChest").setValue("Fly Machine");
                reference.child(userID).child("fBack").setValue("Pull Ups");
                reference.child(userID).child("sBack").setValue("Row Machine");
                reference.child(userID).child("fAbs").setValue("Crunches");
                reference.child(userID).child("sAbs").setValue("Leg Raises");
                reference.child(userID).child("tAbs").setValue("Planks");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Body.this, RoutineActivity.class));

            }
        });
        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fChest").setValue("Bench Press");
                reference.child(userID).child("sChest").setValue("Cable Fly");
                reference.child(userID).child("fBack").setValue("Lat Pull Down");
                reference.child(userID).child("sBack").setValue("Row Machine");
                reference.child(userID).child("fAbs").setValue("Crunches");
                reference.child(userID).child("sAbs").setValue("Leg Raises");
                reference.child(userID).child("tAbs").setValue("Planks");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Body.this, RoutineActivity.class));

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
}