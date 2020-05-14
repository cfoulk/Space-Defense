package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class smallOctoEnemy extends Enemy{


    smallOctoEnemy(Context context, int x, int y, int width, int height, int health, Point ss, Base mBase) {
        super(context, x, y, width, height, health, ss, mBase);

        this.health = 25;

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.alien_octo);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }




}
