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
import android.graphics.Rect;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Path;

class TowerGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // Control pausing between updates
    private long mNextFrameTime;
    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    public volatile boolean mPaused = true;


    //TODO migrate this to strategy pattern
    // for playing sound effects
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;


    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    public int mScore;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    private Point size;
    private int baseHealth;
    private Base mBase;

    private EnemyWave mEnemyWave;
    private Towers mTowers;
    private Tower mTower;
    private Tower mTower2;
    private Tower mTower3;
    //private Towers mTowers;
    private CopyOnWriteArrayList enemyList;

    HUD mHUD;

    private AssetManager assetManager;

    // This is the constructor method that gets called
    // from GameActivity
    public TowerGame(Context context, Point size) throws IOException {
        super(context);
        this.size = size;
        mHUD = new HUD(size);





        int blockSize = size.x / NUM_BLOCKS_WIDE;
        mNumBlocksHigh = size.y / blockSize;
        assetManager = context.getAssets();

        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mBase = new Base(context, size);
        mEnemyWave = new EnemyWave(context, size, mBase, mScore);
        mTowers = new Towers(context, mEnemyWave);

        //mTowers.add(new fireTower(context, 1000, 300, mEnemyWave));

        //mTower = new fireTower(context, 1000, 300, mEnemyWave);
        //mTower2 = new iceTower(context, 800, 600, mEnemyWave);
        // = new destructionTower(context, 1000, 450, mEnemyWave);
        baseHealth = 500;


    }


    // Called to start a new game
    public void newGame() {
        mScore = 0;
        mEnemyWave.reset();
        mBase.reset();
        mTowers.reset();
        baseHealth = 750;
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

        mEnemyWave.update();
        mScore = mEnemyWave.getScore();
        baseHealth = mBase.getHealth();
        if(baseHealth <= 0 || mEnemyWave.getRemaining() == 0){
            mPaused = true;
        }
        mTowers.update();



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
            mPaint.setTextSize(70);
            mCanvas.drawText("Enemies left: " + mEnemyWave.getRemaining(), 20, 200, mPaint);
            mPaint.setTextSize(50);
            mCanvas.drawText("Score: " + mScore, 20, 250, mPaint);
            mHUD.draw(mCanvas, mPaint);

            Path sPath = new Path();
            sPath.moveTo(0, 500);
            sPath.lineTo(1000, 500);
            sPath.lineTo(1000, 750);
            sPath.lineTo(size.x,750);

            Paint pathPaint = new Paint();
            pathPaint.setColor(Color.GRAY);
            pathPaint.setStyle(Paint.Style.STROKE);
            pathPaint.setStrokeWidth(20);
            mCanvas.drawPath(sPath, pathPaint);
            mBase.draw(mCanvas, mPaint);
            mEnemyWave.draw(mCanvas, mPaint);
            mTowers.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(mPaused){

                if(baseHealth <= 0){
                    mPaint.setColor(Color.argb(255, 255, 0, 0));
                    mPaint.setTextSize(200);
                    mCanvas.drawText("You have lost.", 200, 700, mPaint);
                }

                if(mEnemyWave.getRemaining() == 0){
                    mPaint.setColor(Color.argb(255, 0, 255, 0));
                    mPaint.setTextSize(200);
                    mCanvas.drawText("You have won!", 200, 700, mPaint);
                }

                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(100);

                mCanvas.drawText("Tap Anywhere to Play!", 200, 900, mPaint);
            }
            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        int i = motionEvent.getActionIndex();
        int x = (int) motionEvent.getX(i);
        int y = (int) motionEvent.getY(i);
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (mPaused) {
                    mPaused = false;
                    newGame();

                    return true;
                }

                if(!mPaused && mScore >= 200){
                    mTowers.AddDestrctionTower(x, y);
                    mEnemyWave.minusScore();
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