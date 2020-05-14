package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class smallOctoEnemy extends Enemy{


    smallOctoEnemy(Context context, int x, int y, int width, int height, int health, Point ss, Base mBase) {
        super(context, x, y, width, height, health, ss, mBase);

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.alien_octo);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }




}
