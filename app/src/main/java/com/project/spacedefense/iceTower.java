package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class iceTower extends Tower {
    iceTower(Context context, int x, int y, EnemyWave mEnemyWave) {
        super(context, x, y, mEnemyWave);

        this.damage = 5;
        this.firingSpeed = 1500;
        mTower = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.bluetower);
        mTower = Bitmap
                .createScaledBitmap(mTower, width, height, true);
    }

    @Override
    void shoot(){
        //timeSinceLastShot = 0;
        super.projectiles.add(new iceProjectile(context, target, x, y, damage));
        System.out.println("New bullet");
        timeSinceLastShot = System.currentTimeMillis();
    }

}
