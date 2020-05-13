//Coded by Charles Foulk and Nick King for CSC 133
//March 2nd 2020


package com.project.spacedefense;




import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

import android.graphics.Path;

class TowerGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // Control pausing between updates
    private long mNextFrameTime;
    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;



    //TODO migrate this to strategy pattern
    // for playing sound effects
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;




    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    private int mScore;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    private Point size;
    private Point basePos;
    private int baseW, baseH;
    private int baseHealth;

    // A snake ssss
    private Base mBase;
    private Enemy mEnemy;
    // And an apple
    private Turret mTurret;

    HUD mHUD;

    private Bitmap mBackground;


    private AssetManager assetManager;

    // This is the constructor method that gets called
    // from SnakeActivity
    public TowerGame(Context context, Point size) throws IOException {
        super(context);

        this.size = size;

        mHUD = new HUD(size);






        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;


        //TODO *Nick* migrate necessary things to sound strategy pattern classes whil allowing for
        // Initializing the SoundPool from here as seamlessly as possible

        //Instantiating the strategy pattern to handle the audio here
        assetManager = context.getAssets();



        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();



        // Call the constructors of our two game objects

        Point point = new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        mTurret = new Turret.TurretBuilder(
                context, point, blockSize)
                .mBitMapTurrets()
                .build();





        mBase = new Base(context, size);



        basePos = mBase.getPosition();
        baseW = mBase.getWidth();
        baseH = mBase.getHeight();

        mEnemy = new Enemy(context,100, 500, 60,60, 50, size, basePos, baseW, baseH, mBase);

    }


    // Called to start a new game
    public void newGame() {

        // reset the snake
//        mEnemy.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner

        //mTurret.spawn();


        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }


    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    try {
                        update();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            draw();
        }
    }


    // Check to see if it is time for an update
    public boolean updateRequired() {

        // Run at 10 frames per second
        final long TARGET_FPS = 25;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            mNextFrameTime =System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }


    // Update all the game objects
    public void update() throws IOException {



       // mEnemy.move();
        mEnemy.update();
        baseHealth = mBase.getHealth();

    }

    Context context;

    {
        context = getContext();
    }

    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {

            mCanvas = mSurfaceHolder.lockCanvas();

            mCanvas.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.star_background), NUM_BLOCKS_WIDE, mNumBlocksHigh, mPaint);

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(100);

            // Draw the score
            mCanvas.drawText("Base: " + baseHealth, 20, 120, mPaint);
            mHUD.draw(mCanvas, mPaint);


            // Draw the apple and the snake

            //mTurret.draw(mCanvas, mPaint);

            //mEnemy.draw(mCanvas, mPaint);



            Path sPath = new Path();
            sPath.moveTo(0, 500);
            sPath.lineTo(1000, 500);
            sPath.lineTo(1000, 750);
            sPath.lineTo(size.x,750);




            //Paint ballPaint = new Paint();
            //ballPaint.setColor(Color.GREEN);
            Paint pathPaint = new Paint();
            pathPaint.setColor(Color.GRAY);
            pathPaint.setStyle(Paint.Style.STROKE);
            pathPaint.setStrokeWidth(20);

            mCanvas.drawPath(sPath, pathPaint);
            mEnemy.draw(mCanvas, mPaint);
            mBase.draw(mCanvas, mPaint);


            // Draw some text while paused
            if(mPaused){

                // Set the size and color of the mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                // We will give this an international upgrade soon

                //i uncommented this code and it seems to work, might be temporary
                mCanvas.drawText("Tap to Play!", 200, 700, mPaint);

            }


            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (mPaused) {
                    mPaused = false;
                    newGame();

                    return true;
                }


                break;

            default:
                break;

        }
        return true;
    }


    // Stop the thread
    public void pause() {
        mPlaying = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }


    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}