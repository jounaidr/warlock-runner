package com.example.javagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class help extends AppCompatActivity {
    private Button menu; //menu btn

    /**
     * This method will setup the activity view
     * and the button which will return to main menu
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        menu = (Button) findViewById(R.id.menu); //sets menu btn
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity(); //when clicked go to menu
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    /**
     * opens menu
     */
    public void openMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class); //set intent to menu activity
        finish();
        startActivity(intent); //start activity
    }
}
