package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Enemy {

    private int speed = 10;

    Boolean isAlive;

    int x, y, health, height, width;

    Bitmap mEnemy;

    //screen size
    private Point ss;

    //base info
    Point basePos;
    int baseW, baseH;
    Base mBase;


    Enemy(Context context, int x, int y, int health, Point ss, Base mBase){

        this.mBase = mBase;
        isAlive = true;
        this.x = x;
        this.y = y;
        //this.width = width;
        //this.height = height;
        this.health = health;
        this.ss = ss;
        this.basePos = mBase.getPosition();
        this.baseW = mBase.getWidth();
        this.baseH = mBase.getHeight();

        /*mEnemy = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.alien_octo);

        mEnemy = Bitmap
                .createScaledBitmap(mEnemy, width, height, true);*/
    }

    void update(){

        System.out.println(health + "   " + isAlive);
        if (isAlive){
            move();
            checkBaseCollision();
        }
    }


    void draw(Canvas canvas, Paint paint) {
        if(isAlive) {
            canvas.drawBitmap(mEnemy, x - (width / 2), y - (height / 2), paint);
        }
    }

    private void move(){

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

    private void checkHealth(){
        isAlive = health > 0;
    }

    void checkBaseCollision(){
        if(x + width > basePos.x && x < basePos.x + baseW && y + height > basePos.y && y < basePos.y + baseH){
            isAlive = false;

            mBase.setHealth(50);
        }
    }

    void setSpeed(int speed){
        this.speed = speed;
    }

    //collisions take away health
    void removeHealth(int t){
        health -= t;
        checkHealth();
    }

    boolean getStatus(){
        return isAlive;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
