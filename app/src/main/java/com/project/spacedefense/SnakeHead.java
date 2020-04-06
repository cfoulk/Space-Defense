package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import com.project.snakeysnake.Snake.Heading;

import java.util.ArrayList;

class SnakeHead implements SnakeComponent{

    // A bitmap for each direction the head can face
    private Bitmap mBitmapHeadRight;
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;

    private int mSegmentSize;

    private Heading heading;


    SnakeHead(Context context, int ss, int mSegmentSize, Point mr, Heading heading){

        this.mSegmentSize = mSegmentSize;

        this.heading = heading;


        mBitmapHeadRight = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        // Create 3 more versions of the head for different headings
        mBitmapHeadLeft = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        mBitmapHeadUp = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        mBitmapHeadDown = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        // Modify the bitmaps to face the snake head
        // in the correct direction
        mBitmapHeadRight = Bitmap
                .createScaledBitmap(mBitmapHeadRight,
                        ss, ss, false);

        // A matrix for scaling
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);

        mBitmapHeadLeft = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0, 0, ss, ss, matrix, true);

        // A matrix for rotating
        matrix.preRotate(-90);
        mBitmapHeadUp = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0, 0, ss, ss, matrix, true);

        // Matrix operations are cumulative
        // so rotate by 180 to face down
        matrix.preRotate(180);
        mBitmapHeadDown = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0, 0, ss, ss, matrix, true);

    }



    @Override
    public void draw(ArrayList<Point> segmentLocations, Canvas canvas, Paint paint) {

        switch (heading) {
            case RIGHT:
                canvas.drawBitmap(mBitmapHeadRight,
                        segmentLocations.get(0).x
                                * mSegmentSize,
                        segmentLocations.get(0).y
                                * mSegmentSize, paint);
                break;

            case LEFT:
                canvas.drawBitmap(mBitmapHeadLeft,
                        segmentLocations.get(0).x
                                * mSegmentSize,
                        segmentLocations.get(0).y
                                * mSegmentSize, paint);
                break;

            case UP:
                canvas.drawBitmap(mBitmapHeadUp,
                        segmentLocations.get(0).x
                                * mSegmentSize,
                        segmentLocations.get(0).y
                                * mSegmentSize, paint);
                break;

            case DOWN:
                canvas.drawBitmap(mBitmapHeadDown,
                        segmentLocations.get(0).x
                                * mSegmentSize,
                        segmentLocations.get(0).y
                                * mSegmentSize, paint);
                break;
        }
    }
}
