package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class bigOctoEnemy extends Enemy {

    bigOctoEnemy(Context context, int x, int y, int width, int height, int health, Point ss, Base mBase) {
        super(context, x, y, width, height, health, ss, mBase);

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.cannonball);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }
}
