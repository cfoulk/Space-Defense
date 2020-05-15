package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Projectile {

    int x, y, damage;
    int height, width;
    float firingSpeed, timeSinceLastShot, xVelocity, yVelocity;
    Bitmap mProjectile;
    Enemy target;
    boolean isAlive;

    Projectile(Context context, Enemy target, int x, int y, int damage){

        isAlive = true;
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 60;
        this.damage = damage;
        this.firingSpeed = 75;
        this.target = target;
        this.xVelocity = 0f;
        this.yVelocity = 0f;

        //calculateDirection();

    }

    private void calculateDirection(){
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

    void update(){

        if(target != null) {
            calculateDirection();
            x += xVelocity * firingSpeed;
            y += yVelocity * firingSpeed;
            checkEnemyCollision();

        }
    }

    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mProjectile, x - (width / 2), y - (height / 2), paint);
    }

    void checkEnemyCollision(){
        if(x + width > target.x && x < target.x + target.getWidth() && y + height > target.y && y < target.y + target.getHeight()){
            isAlive = false;

            target.removeHealth(damage);

            System.out.println("damage done");
        }
    }

    public boolean getStatus(){
        if(target.getStatus() && target != null) {
            return isAlive;
        }
        return false;
    }
}
