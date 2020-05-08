package com.example.javagame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button start; //start btn
    private Button exit; //exit btn
    private Button help; //help btn

    private MediaPlayer music; //initialise music player

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        playMusic(); //play menu music

        start = (Button) findViewById(R.id.start); //setup start btn
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity(); //when clicked go to game
            }
        });

        exit = (Button) findViewById(R.id.exit); //setup exit btn
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish(); //stop processes
                System.exit(0); //exit app
            }
        });

        help = (Button) findViewById(R.id.help); //setup exit btn
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity(); //open help activity when clicked
            }
        });
    }

    /**
     * Method used to set, play and loop music track
     */
    public void playMusic(){
        music = MediaPlayer.create(this,R.raw.jjba_pillar_men); //sets media player music to menu music
        music.setLooping(true); //loop music
        music.start(); //start music
    }

    /**
     * Stops music and opens game
     */
    public void openMenuActivity(){
        music.stop(); //stop menu music
        Intent intent = new Intent(this, Game.class); //go to game activity
        startActivity(intent); //start activity
    }

    /**
     * opens help page
     */
    public void openHelpActivity(){
        music.stop(); //stop menu music
        Intent intent = new Intent(this, help.class); //go to help activity
        startActivity(intent); //start activity
    }
}
