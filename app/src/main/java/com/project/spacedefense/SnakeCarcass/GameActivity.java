package com.project.spacedefense.SnakeCarcass;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.project.spacedefense.TowerGame;

import java.io.IOException;


/*
This is completely copied over from previous projects only changed some names
*/


public class GameActivity extends Activity {

    TowerGame tGame;

    // Set the game up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);

        // Create a new instance of the TowerGame class
        try {
            tGame = new TowerGame(this, size);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Make Engine the view of the Activity
        setContentView(tGame);
    }

    // Start the thread in snakeEngine
    @Override
    protected void onResume() {
        super.onResume();
        tGame.resume();
    }

    // Stop the thread in snakeEngine
    @Override
    protected void onPause() {
        super.onPause();
        tGame.pause();
    }
}