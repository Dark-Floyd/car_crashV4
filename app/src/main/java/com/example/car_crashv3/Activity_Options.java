package com.example.car_crashv3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Options extends AppCompatActivity {

    public static final String BUNDLE = "Bundle";
    private boolean gameModeTilt=false;
    private boolean gameModeFast=false;
    private int playerSkinIndex = 0;
    private int volume = 50;

    private Bundle bundle;
    private Switch tiltMode;
    private Switch fastMode;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        bundle = new Bundle();
        findViews();
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMenu();
                finish();
            }
        });

        tiltMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    gameModeTilt=true;
                }
                else{
                    gameModeTilt=false;
                }
            }
        });

        fastMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gameModeFast=true;
                }
                else{
                    gameModeFast=false;
                }
            }
        });
    }
    private void startMenu() {
        Intent myIntent = new Intent(this, com.example.car_crashv3.Activity_Menu.class);
        setBundle();
        myIntent.putExtra(BUNDLE, bundle);
        startActivity(myIntent);
    }
    private void setBundle() {
        bundle.putInt(Game.PLAYER_SKIN,playerSkinIndex);
        bundle.putBoolean(Game.MODE,gameModeTilt);
        bundle.putBoolean(Game.SPEED,gameModeFast);
        bundle.putInt(Game.VOLUME, volume);
    }
    private void findViews() {
        buttonExit = findViewById(R.id.options_BTN_Exit);
        tiltMode = findViewById(R.id.options_SWITCH_TiltMode);
        fastMode = findViewById(R.id.options_SWITCH_FastMode);
    }

}