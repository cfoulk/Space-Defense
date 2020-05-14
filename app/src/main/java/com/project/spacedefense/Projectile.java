package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Projectile {

    private int x, y, damage, width, height;
    float firingSpeed, timeSinceLastShot, xVelocity, yVelocity;
    private Bitmap mProjectile;
    private Enemy target;

    Projectile(Context context, Enemy target, int x, int y, int damage, float firingSpeed){

        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 60;
        this.damage = damage;
        this.firingSpeed = firingSpeed;
        this.target = target;
        this.xVelocity = 0f;
        this.yVelocity = 0f;

        mProjectile = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.cannonball);

        mProjectile = Bitmap
                .createScaledBitmap(mProjectile, width, height, true);

        calculateDirection();

    }

    private void calculateDirection(){

        if(target != null) {
            float totalAllowedMovement = 1.0f;
            float xDistanceFromTarget = Math.abs(target.getX() - x);
            float yDistanceFromTarget = Math.abs(target.getY() - y);
            float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
            float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
            xVelocity = xPercentOfMovement;
            yVelocity = totalAllowedMovement - xPercentOfMovement;

            if (target.getX() < x) {
                xVelocity *= -1;
            }
            if (target.getY() < y) {
                yVelocity *= -1;
            }
        }
    }

    void update(){

        calculateDirection();
        x += xVelocity * firingSpeed;
        y += yVelocity * firingSpeed;

    }

    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mProjectile, x - (width / 2), y - (height / 2), paint);
    }
}
