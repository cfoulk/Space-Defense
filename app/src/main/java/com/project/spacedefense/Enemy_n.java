package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy_n {

    private int speed = 25;

    private int x, y, health;

    private int height, width;

    private Bitmap mEnemy;



    public Enemy_n(Context context, int x, int y, int width, int height, int health){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.alien_octo);
        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }


    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mEnemy,x - (width/2),y - (height/2),paint);

    }

    public void move(){

        if ((y == 500) && (x < 1000)){
            x = x + speed;
        }
        if ((y < 750) && (x == 1000)){
            y = y + speed;
        }
        if ((y == 750) && (x < 2000)){
            x = x + speed;
        }

    }

    public void setSpeed(int speed){
        this.speed = speed;
    }



}
