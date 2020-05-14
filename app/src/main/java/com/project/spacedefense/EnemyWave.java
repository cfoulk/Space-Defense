package com.project.spacedefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

class EnemyWave {

    private int enemies, spawned;
    private long timeSinceLastSpawn, spawnTime;

    private ArrayList<Enemy> enemyList;
    private Point size;
    private Base mBase;
    private Context context;

    EnemyWave(Context context, Point size, Base mBase){

        this.context = context;
        this.size = size;
        this.mBase = mBase;
        enemies = 10;
        spawned = 0;
        spawnTime = 3000; // 3 seconds
        timeSinceLastSpawn = 0;

        enemyList = new ArrayList<>();
    }

    void update(Canvas canvas, Paint paint) {


        if((System.currentTimeMillis() - timeSinceLastSpawn) >= spawnTime && (spawned < 10)){
            enemyList.add(new Enemy(context, 100, 500, 60, 60, 50, size, mBase));
            System.out.println(System.currentTimeMillis() + " New enemy " + spawned);
            timeSinceLastSpawn = System.currentTimeMillis();
            System.out.println("new spawn time: " + timeSinceLastSpawn);
            spawned += 1;
        }

        /*while (spawned < enemies) {

            if ((System.currentTimeMillis() - timeSinceLastSpawn) >= spawnTime) {
                enemyList.add(new Enemy(context, 100, 500, 60, 60, 50, size, mBase));
                System.out.println("New enemy");
                timeSinceLastSpawn = System.currentTimeMillis();
                spawned += 1;
            }


            for(Enemy e: enemyList){
                System.out.println("update?");
                e.update();
                e.draw(canvas, paint);
            }
        }*/

        for(Enemy e: enemyList) {
            //System.out.println("draw?");
            e.update();
            //e.draw(canvas, paint);
        }
        //move();
    }

    void move(){
        for(Enemy e: enemyList) {
            //System.out.println("update?");
            e.update();
        }
    }

    void draw(Canvas canvas, Paint paint){
        for(Enemy e: enemyList) {
            //System.out.println("draw?");
            e.draw(canvas, paint);
        }
    }

}
