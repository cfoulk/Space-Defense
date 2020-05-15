package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class bossEnemy extends Enemy {
    bossEnemy(Context context, int x, int y, int health, Point ss, Base mBase) {
        super(context, x, y, health, ss, mBase);

        this.health = 150;
        this.height = 150;
        this.width = 100;

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.bossalien);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }


    void checkBaseCollision(){
        if(x + width > basePos.x && x < basePos.x + baseW && y + height > basePos.y && y < basePos.y + baseH){
            isAlive = false;

            mBase.setHealth(200);
        }
    }
}
