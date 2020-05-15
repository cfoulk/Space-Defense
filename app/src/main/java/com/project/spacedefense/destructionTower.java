package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class destructionTower extends Tower {
    destructionTower(Context context, int x, int y, EnemyWave mEnemyWave) {
        super(context, x, y, mEnemyWave);

        this.damage = 20;
        this.firingSpeed = 6000;
        mTower = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.greentower);
        mTower = Bitmap
                .createScaledBitmap(mTower, width, height, true);
    }

    @Override
    void shoot(){
        //timeSinceLastShot = 0;
        super.projectiles.add(new destructionProjectile(context, target, x, y, damage));
        System.out.println("New bullet");
        timeSinceLastShot = System.currentTimeMillis();
    }
}
