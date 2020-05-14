package com.project.spacedefense;


import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;


class UIController implements InputObserver {


    private boolean initialPress = false;

    UIController(GameEngineBroadcaster b) {
        // Add as an observer
        addObserver(b);


    }

    // Need to add observer each time a new game is started and the game objects are rebuilt
    // and the observer list is cleared. This method allows us to re-add an observer for this
    // Rather than just add it in the constructor.
    // The Player controller doesn't need it because a new object is created each level
    void addObserver(GameEngineBroadcaster b) {
        b.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event,
                            Context context,
                            ArrayList<Rect> buttons, EnemyWave mEnemyWave) {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        int eventType = event.getAction()
                & MotionEvent.ACTION_MASK;

        if (eventType == MotionEvent.ACTION_UP ||
                eventType == MotionEvent.ACTION_POINTER_UP) {

            if (buttons.get(HUD.TOWER0).contains(x, y)){


                Tower t1 = new Tower(context, 300, 300, mEnemyWave);
                // Player pressed the pause button
                // Respond differently depending upon the game's state

                // If the game is not paused
                /*if (!mPaused) {
                    // Pause the game
                    gameState.pause();
                }

                // If game is over start a new game
                else if (gameState.getGameOver()) {

                    gameState.startNewGame();
                }

                // Paused and not game over
                else if (gameState.getPaused()
                        && !gameState.getGameOver()) {

                    gameState.resume();
                }*/
            }
        }

    }

}