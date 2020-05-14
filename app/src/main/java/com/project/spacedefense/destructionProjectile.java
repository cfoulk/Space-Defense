package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class destructionProjectile extends Projectile {
    destructionProjectile(Context context, Enemy target, int x, int y, int damage) {
        super(context, target, x, y, damage);

        mProjectile = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.greenball);

        mProjectile = Bitmap
                .createScaledBitmap(mProjectile, width, height, true);
    }
}