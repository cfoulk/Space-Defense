package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class bigOctoEnemy extends Enemy {

    bigOctoEnemy(Context context, int x, int y, int health, Point ss, Base mBase) {
        super(context, x, y, health, ss, mBase);

        this.health = 75;
        this.height = 100;
        this.width = 60;

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.bigocto);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }
}
