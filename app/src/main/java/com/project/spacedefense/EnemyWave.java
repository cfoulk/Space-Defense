package com.project.spacedefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.concurrent.CopyOnWriteArrayList;

class EnemyWave {

    private int enemies, spawned, enemiesDead;
    private long timeSinceLastSpawn, spawnTime;

    private boolean waveComplete, allDead;

    private CopyOnWriteArrayList<Enemy> enemyList;
    private Point size;
    private Base mBase;
    private Context context;
    int mScore;

    EnemyWave(Context context, Point size, Base mBase, int mScore){

        this.context = context;
        this.size = size;
        this.mBase = mBase;
        this.mScore = mScore;
        enemies = 30; //amount of enemies to spawn
        spawned = 0;
        spawnTime = 3000; // 3 seconds
        timeSinceLastSpawn = 0;

        enemyList = new CopyOnWriteArrayList<>();
    }

    void update() {

        if((System.currentTimeMillis() - timeSinceLastSpawn) >= spawnTime && (spawned < enemies)){

            if(spawned < 20) {
                spawnTime = 3000;
                enemyList.add(new smallOctoEnemy(context, 100, 500, 50, size, mBase));
                spawned += 1;
            }
            if(spawned >= 20 && spawned < (enemies - 1)){
                enemyList.add(new bigOctoEnemy(context, 100, 500,50, size, mBase));
                spawnTime = 4000;
                spawned += 1;
            }
            if (spawned == enemies - 1){
                spawnTime = 10000;
                enemyList.add(new bossEnemy(context, 100, 500, 50, size, mBase));
                spawned += 1;
            }
            timeSinceLastSpawn = System.currentTimeMillis();

        }

        for(Enemy e: enemyList) {
            if(e.getStatus()) {
                e.update();
            } else {
                enemyList.remove(e);
                mScore += 50;
                enemiesDead += 1;
            }
        }

    }

    void draw(Canvas canvas, Paint paint){
        for(Enemy e: enemyList) {
            e.draw(canvas, paint);
        }
    }

    public CopyOnWriteArrayList<Enemy> getEnemyList(){
        return enemyList;
    }

    int getRemaining(){
        return enemies - enemiesDead;
    }

    public void reset(){
        enemyList.clear();
        enemies = 30;
        spawned = 0;
        enemiesDead = 0;
    }

    public int getScore(){
        return mScore;
    }


    //continually update the score when purchasing
    public void minusScore(){
        mScore -= 200;
    }

}
