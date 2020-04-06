//Coded by Charles Foulk and Nick King for CSC 133
//March 2nd 2020


package com.project.spacedefense;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

class SnakeGame extends SurfaceView implements Runnable{

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

    // A snake ssss
    private Snake mSnake;
    // And an apple
    private Apple mApple;
    private Apple mApple2;

    public AudioPlayer audioPlayer;
    private AssetManager assetManager;

    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) throws IOException {
        super(context);




        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;


        //TODO *Nick* migrate necessary things to sound strategy pattern classes whil allowing for
        // Initializing the SoundPool from here as seamlessly as possible

        //Instantiating the strategy pattern to handle the audio here
        assetManager = context.getAssets();
        //the following commented out section is replaced by the call
        // mSP = audioPlayer.mSP; above
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSP = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Prepare the sounds in memory
            descriptor = assetManager.openFd("get_apple.ogg");
            mEat_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("snake_death.ogg");
            mCrashID = mSP.load(descriptor, 0);

        } catch (IOException e) {
            // Error
        }*/
        //TODO *NICK* Sound handling ends here


        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        // Call the constructors of our two game objects

        Point point = new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh);
       // GameObject gameObject = new GameObject(context, point, blockSize);
       // Apple mApple = new GameObject(context, point, blockSize).createApple();

        mApple = new Apple.AppleBuilder(
                context, point, blockSize)
                .mBitmapApple()
                .build();

        mApple2 = new Apple.AppleBuilder(
                context, new Point(NUM_BLOCKS_WIDE, mNumBlocksHigh), blockSize)
                .mBitmapApple()
                .build();


        //Here lies the original call for apple object construction... R.I.P
        /*mApple = new Apple(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize); */



        mSnake = new Snake(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

    }


    // Called to start a new game
    public void newGame() {

        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner

        mApple.spawn();
        mApple2.spawn();

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
        final long TARGET_FPS = 10;
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


        // Move the snake
        mSnake.move();

        // Did the head of the snake eat the apple?
        if(mSnake.checkDinner(mApple.getLocation(), mApple)){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            mApple.spawn();

            // Add to  mScore
            if(mApple.getStatus() == 1){
                mScore += 1;
            } else if(mApple.getStatus() == 2){
                mScore += 2;
            } else if(mApple.getStatus() == 3){
                mScore += 3;
            } else {
                mScore -= 2;
            }

            //TODO sound handled here, migrate necessary logic as needed *NICK*
            // Play a sound
            try{
                AudioFile eatenApple = new AudioFile(assetManager, 0);
                audioPlayer = new AudioPlayer(eatenApple);
                audioPlayer.playAppleEatingSound(mSP);

                //audioInterface.playAppleEatingSound(mSP);
                //mSP.play(mEat_ID, 1, 1, 0, 0, 1);
            }
            catch (Exception e){
             ///   System.out.println("couldn't play apple sound");
            }

            //following line moved down to inherited objects
           //mSP.play(mEat_ID, 1, 1, 0, 0, 1);
        }

        // Did the snake die?
        if (mSnake.detectDeath()) {
            try{

                AudioFile audio = new AudioFile(assetManager, 0);
                audio.playSnakeDeathSound();

               // AudioPlayer audioInterface = new AudioPlayer(mSP);
               // audioInterface.playSnakeDeathSound(mSP);
                //mSP.play(mEat_ID, 1, 1, 0, 0, 1);
            }
            catch (Exception e){
             //   System.out.println("couldn't play snake ded sound");
                //System.out.println("played sound anyway");
               // mSP.play(mCrashID, 1, 1, 0, 0, 1);
            }
            // Pause the game ready to start again


            mPaused =true;
        }

        if(mSnake.checkDinner(mApple2.getLocation(), mApple2)){
            mApple2.spawn();

//TODO GET THIS CODE INTEGRATED WHEN LOGIC IS FIGURED OUT BELOW
            // remove 2 for bad apple
            if(mApple2.getStatus() == 1){
                mScore += 1;
            } else if(mApple2.getStatus() == 2){
                mScore += 2;
            } else if(mApple2.getStatus() == 3){
                mScore += 3;
            } else {
                mScore -= 2;
            }
            
            //TODO sound handled here, migrate necessary logic as needed *NICK*
            // Play a sound
            try{
                audioPlayer.playAppleEatingSound(mSP);
                mSP.play(mEat_ID, 1, 1, 0, 0, 1);
            }
            catch (Exception e){
              //  System.out.println("couldnt play apple sound");
            }

            //following line moved down to inherited objects
            //mSP.play(mEat_ID, 1, 1, 0, 0, 1);
        }



    }


    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the score
            mCanvas.drawText("" + mScore, 20, 120, mPaint);

            // Draw the apple and the snake
            mApple.draw(mCanvas, mPaint);
            mApple2.draw(mCanvas,mPaint);
            mSnake.draw(mCanvas, mPaint);

            // Draw some text while paused
            if(mPaused){

                // Set the size and color of the mPaint for the text
                mPaint.setColor(Color.argb(255, 255, 255, 255));
                mPaint.setTextSize(250);

                // Draw the message
                // We will give this an international upgrade soon

                //i uncommented this code and it seems to work, might be temporary
                mCanvas.drawText("Tap To Play!", 200, 700, mPaint);
                /*mCanvas.drawText(getResources().
                                getString(R.string.tap_to_play),
                        200, 700, mPaint);*/
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

                    // Don't want to process snake direction for this tap
                    return true;
                }

                // Let the Snake class handle the input
                mSnake.switchHeading(motionEvent);
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