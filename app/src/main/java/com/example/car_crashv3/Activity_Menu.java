package com.example.car_crashv3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class Activity_Menu extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonOptions;
    private Button buttonLeaderBoards;
    private Bundle optionsBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        if(getIntent().hasExtra(Activity_Options.BUNDLE)) {
            optionsBundle = getIntent().getExtras().getBundle(Activity_Options.BUNDLE);
        }
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        buttonLeaderBoards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLeaderboards();

            }
        });

        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startOptions();

            }
        });
    }
    private void startOptions() {
        Intent myIntent = new Intent(this, Activity_Options.class);
        startActivity(myIntent);
        finish();
    }
    private void startGame() {
        Intent myIntent = new Intent(this, com.example.car_crashv3.Activity_Game.class);
        if(optionsBundle!=null)
            myIntent.putExtra(Activity_Options.BUNDLE, optionsBundle);
        startActivity(myIntent);
    }
    private void startLeaderboards(){
        Intent myIntent = new Intent(this, Activity_LeaderBoards.class);
        startActivity(myIntent);
    }
    private void findViews(){
        buttonPlay = findViewById(R.id.btn_play);
        buttonOptions = findViewById(R.id.btn_options);
        buttonLeaderBoards = findViewById(R.id.btn_leaderBoards);
    }
}