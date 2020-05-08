package com.example.javagame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


public class Game extends Activity {

    private MediaPlayer music; //initialise music player

    /**
     * When activity is created, play music, go fullscreen, hide title
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playMusic(); //begin game music

        requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title...
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //...and go fullscreen

        setContentView(new GamePanel(this));
    }

    /**
     * stop game music when leaving activity
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        music.stop(); //stop game music when leaving activity
    }

    /**
     * inflates option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu); //inflates option menu (if present options)
        return true;
    }

    /**
     * Option selection menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method used to set, play and loop music track
     */
    public void playMusic(){
        music = MediaPlayer.create(this,R.raw.unreal_superhero); //sets media player music to game music
        music.setLooping(true); //loop music
        music.start(); //start music
    }
}
