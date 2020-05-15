package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Base {

    private int health;

    private int height, width;

    private Point position;

    private boolean isAlive;

    private Bitmap mBase;


    Base(Context context, Point ss){


        isAlive = true;

        height = 100;
        width = 100;
        health = 750;

        position = new Point(ss.x - width, 750);


        mBase = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.base);
        mBase = Bitmap
                .createScaledBitmap(mBase, width, height, true);

    }


    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBase, position.x,position.y - (height/2),paint);
    }

    //each time a enemy collides with the base, it takes 50 health from the base
    public void setHealth(int m){
        health -= m;
    }

    public Point getPosition(){
        return position;
    }

    public void reset(){
        health = 750;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getHealth(){
        return health;
    }
}
