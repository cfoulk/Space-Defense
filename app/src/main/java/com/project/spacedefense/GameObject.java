package com.project.spacedefense;

public class GameObject {




}




/* import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;


public class GameObject extends SnakeGame{

    Point point;
    Context context;
    int blockSize;
    Apple apple;
    Snake snake;
    int status;
    Point size;
    private int NUM_BLOCKS_WIDE;




    public GameObject(Context context, Point size) throws IOException {
        super(context, size);

    }


    Apple createBadApple(){
        Apple newBadApple = new Apple.AppleBuilder(
                context, point, blockSize)
                .mBitmapApple() //TODO change to bad apple bitmap
                .build();

        return newBadApple;
    }

    Apple createApple() {
        Apple newApple = new Apple.AppleBuilder(
                context, point, blockSize)
                .mBitmapApple()
                .build();

        return newApple;
    }

    public int statusSetter(){
        //generate a random status number
        return 1;
    }






    Snake createSnake() {
        Snake newSnake = new Snake(context, point, blockSize);
        //move call from SnakeGame class to here


        return newSnake;

    }

}
*/