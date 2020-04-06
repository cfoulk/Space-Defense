package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class Apple {

    // The location of the apple on the grid
    // Not in pixels
    private final Point location;

    // The range of values we can choose from
    // to spawn an apple
    private final Point mSpawnRange;
    private final int mSize;
    private final Context context;
    private final int status;





    // An image to represent the apple
    //private final Bitmap mBitmapApple;
    private final Bitmap mBitmapApple;



    /// Set up the apple in the constructor

    public Apple(AppleBuilder appleBuilder){
        super();

        this.mSpawnRange = appleBuilder.mSpawnRange;
        this.mSize = appleBuilder.mSize;
        this.context = appleBuilder.context;
        this.mBitmapApple = appleBuilder.mBitmapApple;
        this.location = appleBuilder.location;
        this.status = appleBuilder.status;

    }



    //get methods only provide immutability



    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    public Point getLocation(){
        return location;
    }

    // This is called every time an apple is eaten
    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapApple,
                this.location.x * this.mSize, this.location.y * this.mSize, paint);

    }








    public static class AppleBuilder{

        private Point location;
        // The range of values we can choose from to spawn an apple

        private final Context context;
        private final Point mSpawnRange;
        private final int mSize;

        private Bitmap mBitmapApple;
        // An interchangeable image to represent the apple

        //The status of the apple. 1 or true = good, 0 or false = bad
        private int status;


        /// Set up the apple in the constructor
        public AppleBuilder(Context context, Point mSpawnRange, int mSize){
            this.context = context;
            // Make a note of the passed in spawn range
            this.mSpawnRange = mSpawnRange;
            // Make a note of the size of an apple
            this.mSize = mSize;


            // Hide the apple off-screen until the game starts
            this.location = new Point();
            this.location.x = -10;

            status = setStatus();

        }

        public int setStatus(){
            Random rand = new Random();
            int random = rand.nextInt(4);

            return random;
        }




        public AppleBuilder mBitmapApple(){
            // Load the image to the bitmap

            Bitmap mBitmapApple1;



            if(status == 0){
                mBitmapApple1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.badapple);
            } else if(status == 1){
                mBitmapApple1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
            } else if (status == 2){
                mBitmapApple1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple2points);
            } else{
                mBitmapApple1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple3points);
            }


            // Resize the bitmap
            mBitmapApple1 = Bitmap.createScaledBitmap(mBitmapApple1, this.mSize, this.mSize, false);
            mBitmapApple = mBitmapApple1;


            return this;
        }


        // Draw the apple
        public Apple build(){
            Apple apple = new Apple(this);

            //setStatus();

            return apple;

        }


    }

    public int getStatus(){
        return status;
    }

}




