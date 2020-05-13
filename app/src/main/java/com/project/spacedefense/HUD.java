package com.project.spacedefense;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.content.res.AssetManager;


import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;


    static int UP = 0;
    static int DOWN = 1;
    static int FLIP = 2;
    static int SHOOT = 3;
    static int PAUSE = 0;
    static int TOWER1 = 1;
    static int TOWER2 = 2;

    HUD(Point size){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;

        prepareControls();
    }

    private void prepareControls(){
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        /*Rect up = new Rect(
                buttonPadding,
                mScreenHeight - (buttonHeight * 2) - (buttonPadding * 2),
                buttonWidth + buttonPadding,
                mScreenHeight - buttonHeight - (buttonPadding *2) );

        Rect down = new Rect(
                buttonPadding,
                mScreenHeight - buttonHeight - buttonPadding,
                buttonWidth + buttonPadding,
                mScreenHeight - buttonPadding);

        Rect flip = new Rect(mScreenWidth - buttonPadding - buttonWidth,
                mScreenHeight - buttonHeight - buttonPadding,
                mScreenWidth - buttonPadding,
                mScreenHeight - buttonPadding

        );

        Rect shoot = new Rect(mScreenWidth - buttonPadding - buttonWidth,
                mScreenHeight - (buttonHeight * 2) - (buttonPadding * 2),
                mScreenWidth - buttonPadding,
                mScreenHeight - buttonHeight - (buttonPadding *2)
        ); */

        Rect pause = new Rect(
                mScreenWidth - buttonPadding - buttonWidth,
                buttonPadding,
                mScreenWidth - buttonPadding,
                buttonPadding + buttonHeight);

        Rect tower1 = new Rect(
                mScreenWidth - (buttonPadding * 2) - (buttonWidth * 2) ,
                buttonPadding,
                mScreenWidth - buttonPadding,
                buttonPadding + buttonHeight);

        Rect tower2 = new Rect(
                mScreenWidth - (buttonPadding * 3) - (buttonWidth * 3),
                buttonPadding,
                mScreenWidth - buttonPadding,
                buttonPadding + buttonHeight);






        controls = new ArrayList<>();
        /*controls.add(UP,up);
        controls.add(DOWN,down);
        controls.add(FLIP, flip);
        controls.add(SHOOT, shoot);*/
        controls.add(PAUSE, pause);
        controls.add(TOWER1, tower1);
        controls.add(TOWER2, tower2);
    }

    //GameState gs
    void draw(Canvas c, Paint p){

        // Draw the HUD
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);




        //c.drawText("Hi: " + gs.getHighScore(), mTextFormatting,mTextFormatting,p);
        //c.drawText("Scrore: " + gs.getScore(), mTextFormatting,mTextFormatting * 2,p);
        //c.drawText("Lives: " + gs.getNumShips(), mTextFormatting,mTextFormatting * 3,p);

        /*if(gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PRESS PLAY", mScreenWidth /4, mScreenHeight /2 ,p);
        }

        if(gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PAUSED", mScreenWidth /3, mScreenHeight /2 ,p);
        } */


    }

    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,50,255,255));

        //Drawable d = context.getResources().getDrawable(R.drawable.alien_octo);
        //d.setBounds(pause);


        for(Rect r : controls){
            c.drawRect(r.left, r.top, r.right, r.bottom, p);


        }



        //d.setBounds(controls.get(1));
        //d.draw(c);


        // Set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }
    ArrayList<Rect> getControls(){
        return controls;
    }
}