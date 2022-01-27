package com.apress.gerber.bodyplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoutineActivity extends AppCompatActivity {

    private View appView;
    ImageButton bodyMenu, larmsShouldersMenu, rarmsShouldersMenu, rlegsMenu, llegsMenu;
    Button back;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

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

        //check if user has setup Routine

        routineCheck();

        bodyMenu = (ImageButton) findViewById(R.id.chestBackAbs);
        larmsShouldersMenu = (ImageButton) findViewById(R.id.leftShouldersArms);
        rarmsShouldersMenu = (ImageButton) findViewById(R.id.rightShouldersArms);
        rlegsMenu = (ImageButton) findViewById(R.id.rightLeg);
        llegsMenu = (ImageButton) findViewById(R.id.leftLeg);

        back = (Button) findViewById(R.id.quit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent (RoutineActivity.this, Dashboard.class);
                startActivity(mainMenu);
            }
        });

        bodyMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBodyMenu = new Intent(RoutineActivity.this, Body.class);
                startActivity(intentBodyMenu);
            }
        });

        larmsShouldersMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentArmsMenu = new Intent(RoutineActivity.this, ArmsShoulders.class);
                startActivity(intentArmsMenu);
            }
        });
        rarmsShouldersMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentArmsMenu = new Intent(RoutineActivity.this, ArmsShoulders.class);
                startActivity(intentArmsMenu);
            }
        });

        rlegsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLegsMenu = new Intent(RoutineActivity.this, LegsActivity.class);
                startActivity(intentLegsMenu);
            }
        });
        llegsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLegsMenu = new Intent(RoutineActivity.this, LegsActivity.class);
                startActivity(intentLegsMenu);
            }
        });

    }

    private void routineCheck() {

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile.fTri != " " || userProfile.fiLegs != " " || userProfile.fChest != " "){

                    Toast.makeText(RoutineActivity.this, "Complete setup of your Routine!", Toast.LENGTH_LONG
                    ).show();

                }
                else{
                    Toast.makeText(RoutineActivity.this, "Your Routine has been setup successfully!", Toast.LENGTH_LONG
                    ).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(RoutineActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
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