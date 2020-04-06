package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class SnakeBody implements SnakeComponent{

    private Bitmap mBitmapBody;
    private int mSegmentSize;



    //Create and scale the body
    SnakeBody(Context context, int ss, int mSegmentSize) {

        this.mSegmentSize = mSegmentSize;

        mBitmapBody = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.body);

        mBitmapBody = Bitmap
                .createScaledBitmap(mBitmapBody,
                        ss, ss, false);
    }


    public void draw(ArrayList<Point> segmentLocations, Canvas canvas, Paint paint){
        for (int i = 1; i < segmentLocations.size(); i++) {
            canvas.drawBitmap(mBitmapBody,
                    segmentLocations.get(i).x
                            * mSegmentSize,
                    segmentLocations.get(i).y
                            * mSegmentSize, paint);
        }
    }


    public void remove(ArrayList<Point> segmentLocations){
        if (segmentLocations.size() > 0){
            for (int i = 1; i < 2; i++){
                segmentLocations.remove(segmentLocations.size()-1);
            }
        }
    }
}