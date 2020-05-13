package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Enemy_n {

    private int speed = 25;

    private Boolean isAlive;

    private int x, y, health;

    private int height, width;

    private Bitmap mEnemy;

    //screen size
    private Point ss;

    private Point basePos;
    private int baseW, baseH;
    private Base mBase;



    Enemy_n(Context context, int x, int y, int width, int height, int health, Point ss, Point basePos, int baseW, int baseH, Base mBase){

        this.mBase = mBase;
        isAlive = true;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.ss = ss;
        this.basePos = basePos;
        this.baseW = baseW;
        this.baseH = baseH;

        mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.alien_octo);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);
    }

    void update(){
        if (isAlive){
            move();
        }
        checkBaseCollision();
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

    private void checkBaseCollision(){
        if(x + width > basePos.x && x < basePos.x + baseW && y + height > basePos.y && y < basePos.y + baseH){
            isAlive = false;

            mBase.setHealth();
        }
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    //collisions take away health
    public void removeHealth(int t){
        health -= t;
    }
}
