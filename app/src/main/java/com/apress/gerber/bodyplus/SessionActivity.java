package com.apress.gerber.bodyplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SessionActivity extends AppCompatActivity {

    private View appView;
    Button pushSession, legsSession, pullSession, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        back = (Button) findViewById(R.id.Quit);
        pushSession = (Button) findViewById(R.id.push);
        legsSession = (Button) findViewById(R.id.legs);
        pullSession = (Button) findViewById(R.id.pull);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SessionActivity.this, Dashboard.class));
            }
        });

        pushSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, PushActivity.class);
                startActivity(intent);
            }
        });
        pullSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, PullActivity.class);
                startActivity(intent);
            }
        });
        legsSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, LegsSession.class);
                startActivity(intent);
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