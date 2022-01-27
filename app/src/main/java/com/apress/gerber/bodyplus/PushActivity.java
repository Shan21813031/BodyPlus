package com.apress.gerber.bodyplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PushActivity extends AppCompatActivity {

    private View appView;

    TextView exerciseA, exerciseB, exerciseC, exerciseD, exerciseE, exerciseF, exerciseG, exerciseH, exerciseI;
    Button finish1, finish2, finish3, finish4, finish5, finish6, finish7, finish8, finish9;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        appView = getWindow().getDecorView();
        appView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility ==  0){
                    appView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });

        finish1 = findViewById(R.id.complete1);
        finish2 = findViewById(R.id.complete2);
        finish3 = findViewById(R.id.complete3);
        finish4 = findViewById(R.id.complete4);
        finish5 = findViewById(R.id.complete5);
        finish6 = findViewById(R.id.complete6);
        finish7 = findViewById(R.id.complete7);
        finish8 = findViewById(R.id.complete8);
        finish9 = findViewById(R.id.complete9);

        fetchExercises();

        finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish1.setText("FINISHED");
                finish1.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish1.setText("FINISH");
                finish1.setBackgroundColor(Color.parseColor("#601C00"));
                checkCompletion();
                return true;
            }
        });

        finish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish2.setText("FINISHED");
                finish2.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish2.setText("FINISH");
                finish2.setBackgroundColor(Color.parseColor("#984514"));
                checkCompletion();

                return false;
            }
        });

        finish3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish3.setText("FINISHED");
                finish3.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();

            }
        });
        finish3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish3.setText("FINISH");
                finish3.setBackgroundColor(Color.parseColor("#984514"));
                checkCompletion();

                return false;
            }
        });

        finish4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish4.setText("FINISHED");
                finish4.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish4.setText("FINISH");
                finish4.setBackgroundColor(Color.parseColor("#5E593C"));
                checkCompletion();
                return false;
            }
        });

        finish5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish5.setText("FINISHED");
                finish5.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish5.setText("FINISH");
                finish5.setBackgroundColor(Color.parseColor("#394030"));
                checkCompletion();
                return false;
            }
        });


        finish6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish6.setText("FINISHED");
                finish6.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish6.setText("FINISH");
                finish6.setBackgroundColor(Color.parseColor("#16200F"));
                checkCompletion();
                return false;
            }
        });


        finish7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish7.setText("FINISHED");
                finish7.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish7.setText("FINISH");
                finish7.setBackgroundColor(Color.parseColor("#122222"));
                checkCompletion();
                return false;
            }
        });

        finish8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish8.setText("FINISHED");
                finish8.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish8.setText("FINISH");
                finish8.setBackgroundColor(Color.parseColor("#4B6969"));
                checkCompletion();
                return false;
            }
        });

        finish9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish9.setText("FINISHED");
                finish9.setBackgroundColor(Color.parseColor("#00FF00"));
                checkCompletion();


            }
        });
        finish8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish9.setText("FINISH");
                finish9.setBackgroundColor(Color.parseColor("#2C4447"));
                checkCompletion();
                return false;
            }
        });

    }

    private void checkCompletion() {

        if (finish1.getText().equals("FINISHED") && finish2.getText().equals("FINISHED")
                && finish2.getText().equals("FINISHED") && finish3.getText().equals("FINISHED") &&
                finish4.getText().equals("FINISHED") && finish5.getText().equals("FINISHED") &&
                finish6.getText().equals("FINISHED") && finish7.getText().equals("FINISHED") && finish8.
                getText().equals("FINISHED") && finish9.getText().equals("FINISHED")){

            startActivity(new Intent(PushActivity.this, Dashboard.class));
            Toast.makeText(PushActivity.this, "SESSION COMPLETED", Toast.LENGTH_LONG).show();

        }

    }

    private void fetchExercises() {


        exerciseA = findViewById(R.id.ex1);
        exerciseB = findViewById(R.id.ex2);
        exerciseC = findViewById(R.id.ex3);
        exerciseD = findViewById(R.id.ex4);
        exerciseE = findViewById(R.id.ex5);
        exerciseF = findViewById(R.id.ex6);
        exerciseG = findViewById(R.id.ex7);
        exerciseH = findViewById(R.id.ex8);
        exerciseI = findViewById(R.id.ex9);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){

                    String sSh = userProfile.sSh;
                    String tSh = userProfile.tSh;
                    String fChest = userProfile.fChest;
                    String sChest = userProfile.sChest;
                    String fTri = userProfile.fTri;
                    String sTri = userProfile.sTri;
                    String tAbs = userProfile.tAbs;
                    String fAbs = userProfile.fAbs;
                    String sAbs = userProfile.sAbs;

                    exerciseA.setText(fChest);
                    exerciseB.setText(sChest);
                    exerciseC.setText(sSh);
                    exerciseD.setText(tSh);
                    exerciseE.setText(fTri);
                    exerciseF.setText(sTri);
                    exerciseG.setText(fAbs);
                    exerciseH.setText(sAbs);
                    exerciseI.setText(tAbs);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PushActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
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