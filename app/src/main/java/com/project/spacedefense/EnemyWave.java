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
        enemies = 10; //amount of enemies to spawn
        spawned = 0;
        spawnTime = 3000; // 3 seconds
        timeSinceLastSpawn = 0;

        enemyList = new ArrayList<>();
    }

    void update() {

        if((System.currentTimeMillis() - timeSinceLastSpawn) >= spawnTime && (spawned < enemies)){
            enemyList.add(new smallOctoEnemy(context, 100, 500, 60, 60, 50, size, mBase));
            timeSinceLastSpawn = System.currentTimeMillis();
            spawned += 1;
        }

        for(Enemy e: enemyList) {
            e.update();
        }

    }

    void draw(Canvas canvas, Paint paint){
        for(Enemy e: enemyList) {
            e.draw(canvas, paint);
        }
    }

}
