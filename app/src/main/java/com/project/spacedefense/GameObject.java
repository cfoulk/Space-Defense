package com.project.spacedefense;

import android.content.Context;
import android.graphics.Point;

import java.io.IOException;


public class GameObject extends TowerGame {

    Point point;
    Context context;
    int blockSize;
    //Turret turret;

    int status;
    Point size;
    private int NUM_BLOCKS_WIDE;




    public GameObject(Context context, Point size) throws IOException {
        super(context, size);


    }


    public int statusSetter(){
        //generate a random status number
        return 1;
    }






}
