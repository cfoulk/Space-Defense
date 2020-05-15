package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class iceProjectile extends Projectile {
    iceProjectile(Context context, Enemy target, int x, int y, int damage) {
        super(context, target, x, y, damage);

        mProjectile = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.iceball);

        mProjectile = Bitmap
                .createScaledBitmap(mProjectile, width, height, true);
    }

    @Override
    void checkEnemyCollision(){
        if(x + width > target.x && x < target.x + target.getWidth() && y + height > target.y && y < target.y + target.getHeight()){
            isAlive = false;
            target.removeHealth(damage);
            target.setSpeed(5);
            System.out.println("damage done");
        }
    }
}
