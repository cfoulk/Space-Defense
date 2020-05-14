package com.project.spacedefense;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import java.util.ArrayList;

public interface InputObserver {

    // This allows InputObservers to be called by GameEngine to handle input
    void handleInput(MotionEvent event, Context context, ArrayList<Rect> buttons, EnemyWave mEnemyWave);
}