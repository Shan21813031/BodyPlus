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

public class ArmsShoulders extends AppCompatActivity {

    private View appView;
    Button optA, optB, optC, optD, quitBtn;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_shoulders);

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

                reference.child(userID).child("fTri").setValue("Dips");
                reference.child(userID).child("sTri").setValue("Tricep Extension");
                reference.child(userID).child("fBi").setValue("Seated DB Curl");
                reference.child(userID).child("sBi").setValue("Hammer Curl");
                reference.child(userID).child("fSh").setValue("DB Overhead Press");
                reference.child(userID).child("sSh").setValue("DB Lateral Raise");
                reference.child(userID).child("tSh").setValue("Rear Delt Fly");


                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ArmsShoulders.this, RoutineActivity.class));

            }
        });
        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fTri").setValue("Seated Dips");
                reference.child(userID).child("sTri").setValue("Katana Extension");
                reference.child(userID).child("fBi").setValue("Curl Machine");
                reference.child(userID).child("sBi").setValue("Seated DB Curl");
                reference.child(userID).child("fSh").setValue("Barbell Overhead Press");
                reference.child(userID).child("sSh").setValue("Rear Delt Cables");
                reference.child(userID).child("tSh").setValue("Cable Lateral Raise");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ArmsShoulders.this, RoutineActivity.class));

            }
        });
        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fTri").setValue("Skull Crush");
                reference.child(userID).child("sTri").setValue("Overhead Rope Extension");
                reference.child(userID).child("fBi").setValue("Curl Machine");
                reference.child(userID).child("sBi").setValue("Preacher Curls");
                reference.child(userID).child("fSh").setValue("Land Mine Press");
                reference.child(userID).child("sSh").setValue("Rear Delt Fly");
                reference.child(userID).child("tSh").setValue("Machine Side Lateral Raise");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ArmsShoulders.this, RoutineActivity.class));

            }
        });
        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userID).child("fTri").setValue("Dips");
                reference.child(userID).child("sTri").setValue("Overhead Rope Extension");
                reference.child(userID).child("fBi").setValue("Preacher Curls");
                reference.child(userID).child("sBi").setValue("Hammer Curls");
                reference.child(userID).child("fSh").setValue("Overhead DB Press");
                reference.child(userID).child("sSh").setValue("Cable Side Laterals");
                reference.child(userID).child("tSh").setValue("Face Pulls");

                Toast.makeText(getApplicationContext(),"Routine has successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ArmsShoulders.this, RoutineActivity.class));

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