package com.example.car_crashv3;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Math.pow;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Game {

    private MediaPlayer crashSound;
    private ImageButton buttonLeft, buttonRight;
    private MaterialButton buttonMenu;
    private MaterialTextView timer;
    private ImageView lives[];
    private com.example.car_crashv3.Tile[][] tiles;


    private final int MAX_LIVES = 3;
    private final int NUMBER_OF_LANES = 5;
    private final int NUMBER_OF_LAYERS = 7;
    public final float SENSITIVITY = 2;
    private final int MAX_DELAY = 700;
    private final int MIN_DELAY = 200;
    public static final String VOLUME = "VOLUME";
    public static final String MODE = "MODE";
    public static final String PLAYER_SKIN = "PLAYER";
    public static final String SPEED = "SPEED";

    private boolean tiltMode = false;
    private boolean speedMode = false;
    private boolean tiltModeInitialized = false;
    private float initializedX;
    private float initializedY;
    private float lastViewedX;



    private int delay = 500;
    // this value sets the speed
    private Vibrator v;
    private Random rand = new Random();
    private boolean newStone = true;
    private int odometer=0;
    private com.example.car_crashv3.Activity_Game context;
    private final Handler handler = new Handler();
    private int currentLives=3;

    // private MyDB myDB =  MyDB.getDB();
    // private ArrayList<Record> records = myDB.getRecords();
    private int highScoreIndex;

    private Runnable r = new Runnable() {
        public void run() {
            moveObjects();

            if(newStone){
                int newAsteroidLocation= rand.nextInt(NUMBER_OF_LANES);
                tiles[0][newAsteroidLocation].setStone();
            }
            else{
                int newCrateLocation= rand.nextInt(NUMBER_OF_LANES);
                tiles[0][newCrateLocation].setCoin();
            }
            newStone =!newStone;

            timer.setText("Odometer: " + toStingWithPad(odometer,3));
            odometer++;

            handler.postDelayed(r, delay);
        }
    };


    public Game(){}
    public Game(Vibrator v, com.example.car_crashv3.Activity_Game context) {
        this.v=v;
        this.context=context;
    }

    public float getLastViewedX() {
        return lastViewedX;

    }
    public int getDelay() {
        return delay;

    }
    public boolean isTiltModeInitialized() {
        return tiltModeInitialized;

    }
    public float getInitializedX() {
        return initializedX;

    }
    public float getInitializedY() {
        return initializedY;

    }
    public ImageButton getButtonRight() {
        return buttonRight;

    }
    public ImageButton getButtonLeft() {
        return buttonLeft;

    }
    public MaterialButton getButtonMenu() {
        return buttonMenu;

    }
    public  Handler getHandler() {
        return handler;

    }
    public  Runnable getRunnable() {
        return r;

    }
    public boolean getTiltMode() {
        return tiltMode;

    }
    public boolean getSpeedMode() {
        return speedMode;

    }

    public void setLastViewedX(float lastViewedX) {
        this.lastViewedX = lastViewedX;

    }
    public void setDelay(int delay) {
        if(delay>MAX_DELAY)
            this.delay = MAX_DELAY;
        else if (speedMode)
            this.delay = MIN_DELAY;
        else
            this.delay = delay;
    }
    public void setTiltModeInitialized(boolean tiltModeInitialized) {
        this.tiltModeInitialized = tiltModeInitialized;
    }
    public void setInitializedX(float initializedX) {
        this.initializedX = initializedX;

    }
    public void setInitializedY(float initializedY) {
        this.initializedY = initializedY;

    }
    public void setTimer(MaterialTextView timer) {
        this.timer = timer;

    }

    public void setTiltMode(boolean tiltMode) {
        this.tiltMode = tiltMode;

    }
    public void setSpeedMode(boolean speedMode) {
        this.speedMode = speedMode;

    }

    public void setButtonRight(ImageButton buttonRight) {
        this.buttonRight = buttonRight;

    }
    public void setLives(ImageView[] lives) {
        this.lives = lives;

    }
    public void setTiles(com.example.car_crashv3.Tile[][] tiles) {
        this.tiles = tiles;

    }
    public void setButtonLeft(ImageButton buttonLeft) {
        this.buttonLeft = buttonLeft;

    }
    public void setButtonMenu(MaterialButton buttonMenu) {
        this.buttonMenu = buttonMenu;

    }

    private String toStingWithPad(int num, int numOfPadding){
        String str=Integer.toString(num);

        for(int i=0;i<numOfPadding;i++){
            if(num<pow(10,numOfPadding))
                str="0"+str;
            num*=10;
        }
        return str;
    }

    public void newGame() {
        cleanBoard();
        if(tiltMode){
            buttonLeft.setVisibility(View.INVISIBLE);
            buttonRight.setVisibility(View.INVISIBLE);
        }
        tiles[NUMBER_OF_LAYERS-1][NUMBER_OF_LANES/2].setPlayer();
        currentLives=MAX_LIVES;
        for(int i=0;i<currentLives;i++)
            lives[i].setVisibility(View.VISIBLE);
        odometer=0;
        newStone = true;

    }

    private void cleanBoard() {
        for(int i=0;i<NUMBER_OF_LAYERS;i++){
            for(int j=0;j<NUMBER_OF_LANES;j++){
                tiles[i][j].setEmpty();
            }
        }

    }

    public void startTicker() {
        getHandler().postDelayed(getRunnable(), delay);

    }
    public void stopTicker() {
        getHandler().removeCallbacks(getRunnable());

    }

    private void vibrate(int x) {
        // 500ms
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            v.vibrate(VibrationEffect.createOneShot(x, VibrationEffect.DEFAULT_AMPLITUDE));

    }
    public void playCrashSound(View v){
        if (crashSound == null){
            crashSound = MediaPlayer.create(context,R.raw.sound_crash);
        }
        if (crashSound.isPlaying()){
            crashSound.release();
            crashSound = MediaPlayer.create(context,R.raw.sound_crash);
        }
        crashSound.start();
    }
    public void movePlayer(boolean movingLeft) {
        int playerLocation = checkPlayerLocation();
        int moveTo;
        if(movingLeft) {
            if (tiles[NUMBER_OF_LAYERS - 1][0].getKind() == com.example.car_crashv3.Tile.CAR)
                return;
            else
                moveTo = playerLocation - 1;
        }
        else {
            if (tiles[NUMBER_OF_LAYERS - 1][NUMBER_OF_LANES - 1].getKind() == com.example.car_crashv3.Tile.CAR)
                return;
            else
                moveTo = playerLocation + 1;
        }
        if(!checkHit(tiles[NUMBER_OF_LAYERS-1][playerLocation],tiles[NUMBER_OF_LAYERS-1][moveTo])) {
            tiles[NUMBER_OF_LAYERS - 1][playerLocation].setEmpty();
            tiles[NUMBER_OF_LAYERS - 1][moveTo].setPlayer();
        }
    }
    private void moveObjects() {
        for(int i=0;i<NUMBER_OF_LANES;i++){
            if(tiles[NUMBER_OF_LAYERS-1][i].getKind()!= Tile.CAR){
                tiles[NUMBER_OF_LAYERS-1][i].setEmpty();
            }
        }
        // move objects
        for(int i=NUMBER_OF_LAYERS-2;i>=0;i--){
            for(int j=0;j<NUMBER_OF_LANES;j++){

                if(tiles[i+1][j].getKind()== Tile.CAR){
                    // death
                    if(checkHit(tiles[i][j], tiles[i + 1][j])){
                        return;
                    }
                }
                else{
                    switch(tiles[i][j].getKind()){
                        case com.example.car_crashv3.Tile.STONE:
                            tiles[i + 1][j].setStone();
                            break;

                        case com.example.car_crashv3.Tile.COIN:
                            tiles[i + 1][j].setCoin();
                            break;

                        default:
                            break;
                    }
                }
                tiles[i][j].setEmpty();
            }
        }
    }


    private boolean checkHit(Tile hitter, Tile hit){
        if((hitter.getKind() == com.example.car_crashv3.Tile.STONE && hit.getKind() == com.example.car_crashv3.Tile.CAR) ||
                (hitter.getKind() == com.example.car_crashv3.Tile.CAR && hit.getKind() == com.example.car_crashv3.Tile.STONE)
               ){
            return loseLife();
        }
        else if((hitter.getKind() == com.example.car_crashv3.Tile.COIN && hit.getKind() == com.example.car_crashv3.Tile.CAR) ||
                (hitter.getKind() == com.example.car_crashv3.Tile.CAR && hit.getKind() == com.example.car_crashv3.Tile.COIN)){

            odometer += 10;

        }
        return false;

    }
    private int checkPlayerLocation(){
        for(int i=0 ; i<NUMBER_OF_LANES;i++){
            if(tiles[NUMBER_OF_LAYERS-1][i].getKind()== com.example.car_crashv3.Tile.CAR)
                return i;
        }
        return -1;
    }

    private boolean loseLife(){
        playCrashSound(null);
        vibrate(500);
        currentLives--;
        lives[currentLives].setVisibility(View.INVISIBLE);
        if(currentLives==0){
            Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show();
            newGame();
            return true;
        }
        else{
            Toast.makeText(context, "YOU GOT HIT!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }



    public void modifyGameByBundle(Bundle b) {
        if(b!=null) {
            setTiltMode(b.getBoolean(Game.MODE, false));
            setSpeedMode(b.getBoolean(Game.SPEED,false));
        }
        else
            setDefaultBundle();
    }
    private void setDefaultBundle() {
        setTiltMode(false);
        setSpeedMode(false);
    }

}
