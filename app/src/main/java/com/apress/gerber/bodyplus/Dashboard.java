package com.apress.gerber.bodyplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private View appView;
    ImageButton profile, routine, goals, session;
    private Button signOut;
    boolean firstImage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        profile = findViewById(R.id.profile);
        routine = findViewById(R.id.routine);
        goals = findViewById(R.id.goals);
        session = findViewById(R.id.session);
        signOut = findViewById(R.id.logoutBtn);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, LoginActivity.class));
                finishAffinity();
            }
        });

        profile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(firstImage)
                {
                    profile.setImageResource(R.drawable.pfb);
                    firstImage = false;
                    startActivity(new Intent(Dashboard.this, ProfileActivity.class));

                }
                else{
                    profile.setImageResource(R.drawable.profile);
                    firstImage = true;

                }
                return false;
            }
        });

        routine.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(firstImage)
                {
                    routine.setImageResource(R.drawable.rfb);
                    startActivity(new Intent(Dashboard.this, RoutineActivity.class));
                    firstImage = false;
                }
                else{
                    routine.setImageResource(R.drawable.routine);
                    firstImage = true;

                }
                return false;
            }
        });

        session.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(firstImage)
                {
                    session.setImageResource(R.drawable.sfb);
                    startActivity(new Intent(Dashboard.this, SessionActivity.class));
                    firstImage = false;
                }
                else{
                    session.setImageResource(R.drawable.session);
                    firstImage = true;

                }
                return false;
            }
        });

        goals.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(firstImage)
                {
                    goals.setImageResource(R.drawable.gfb);
                    startActivity(new Intent(Dashboard.this, GoalsActivity.class));
                    firstImage = false;
                }
                else{
                    goals.setImageResource(R.drawable.goals);
                    firstImage = true;

                }
                return false;
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