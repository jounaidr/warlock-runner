package com.example.javagame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class GameOver extends AppCompatActivity {
    private Button menu; //menu btn
    private MediaPlayer music; //initialise music player

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        super.onCreate(savedInstanceState);

        playMusic(); //play death sound

        setContentView(R.layout.activity_game_over);

        menu = (Button) findViewById(R.id.menu); //sets menu buton
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity(); //go to menu activity when clicked
            }
        });
    }


    /**
     * opens menu
     */
    public void openMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class); //set intent to menu activity
        startActivity(intent); //start activity
    }

    /**
     * Method used to set, play and loop music track
     */
    public void playMusic(){
        music = MediaPlayer.create(this,R.raw.death21); //sets media player music to death music
        music.start(); //play death sound
    }
}
