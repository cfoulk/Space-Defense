package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class fireTower extends Tower {
    fireTower(Context context, int x, int y, EnemyWave mEnemyWave) {
        super(context, x, y, mEnemyWave);

        this.damage = 15;
        this.firingSpeed = 2000;
        mTower = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.redtower);
        mTower = Bitmap
                .createScaledBitmap(mTower, width, height, true);
    }


    @Override
    void shoot(){
        //timeSinceLastShot = 0;
        super.projectiles.add(new fireProjectile(context, target, x, y, damage));
        System.out.println("New bullet");
        timeSinceLastShot = System.currentTimeMillis();
    }
}
