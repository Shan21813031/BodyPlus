package com.apress.gerber.bodyplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class GoalsActivity extends AppCompatActivity {

    private View appView;

    private FirebaseUser user;
    private DatabaseReference reference;

    private Button update;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

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

        final TextView fullNameTextView = findViewById(R.id.fullName);

        final TextView weightGoalTextView = findViewById(R.id.wGoal);
        final TextView benchPGoalTextView = findViewById(R.id.bpTarget);
        final TextView deadLGoalTextView = findViewById(R.id.dlTarget);
        final TextView squatGoalTextView = findViewById(R.id.sTarget);

        TextView weightDTextView = findViewById(R.id.wpDiff);
        TextView benchDTextView = findViewById(R.id.bpDiff);
        TextView deadDTextView = findViewById(R.id.dlDiff);
        TextView squatDTextView = findViewById(R.id.sDiff);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){

                    String fullName = userProfile.fullName;
                    String weightGoal = userProfile.weightGoal;
                    String bpGoal = userProfile.bpGoal;
                    String dlGoal = userProfile.dlGoal;
                    String squatGoal = userProfile.squatGoal;

                    fullNameTextView.setText(fullName);
                    weightGoalTextView.setText(weightGoal);
                    benchPGoalTextView.setText(bpGoal);
                    deadLGoalTextView.setText(dlGoal);
                    squatGoalTextView.setText(squatGoal);


                    if(!weightGoal.equals(" ")){

                        String weight = userProfile.weight;
                        String bp = userProfile.bp;
                        String dl = userProfile.dl;
                        String squat = userProfile.squat;

                        Float weightDiff =  Float.valueOf(weight) / Float.valueOf(weightGoal) * 100;
                        Float bpDiff = Float.valueOf(bp) / Float.valueOf(bpGoal) * 100;
                        Float dlDiff = Float.valueOf(dl) / Float.valueOf(dlGoal) * 100;
                        Float squatDiff = Float.valueOf(squat) / Float.valueOf(squatGoal) * 100;

                        String WDF = Float.toString(weightDiff);
                        String BDF = Float.toString(bpDiff);
                        String DLF = Float.toString(dlDiff);
                        String SDF = Float.toString(squatDiff);

                        weightDTextView.setText(WDF);
                        benchDTextView.setText(BDF);
                        deadDTextView.setText(DLF);
                        squatDTextView.setText(SDF);

                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(GoalsActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        update = findViewById(R.id.setGoals);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalsActivity.this, setGoal.class));
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