package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Enemy_n {

    private int speed = 25;

    private int x, y, health;

    private int height, width;

    private Bitmap mEnemy;

    //screen size
    private Point ss;



    Enemy_n(Context context, int x, int y, int width, int height, int health, Point ss){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.ss = ss;

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.alien_octo);
        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }


    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mEnemy,x - (width/2),y - (height/2),paint);

    }

    void move(){

        if ((y == 500) && (x < 1000)){
            x = x + speed;
        }
        if ((y < 750) && (x == 1000)){
            y = y + speed;
        }

        //the enemy always stops at the end of the screen, where the base will be
        if ((y == 750) && (x < ss.x)){
            x = x + speed;
        }


    }

    public void setSpeed(int speed){
        this.speed = speed;
    }



}
