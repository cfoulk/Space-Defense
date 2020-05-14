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

    EnemyWave(Context context, Point size, Base mBase){

        this.context = context;
        this.size = size;
        this.mBase = mBase;
        enemies = 10; //amount of enemies to spawn
        spawned = 0;
        spawnTime = 3000; // 3 seconds
        timeSinceLastSpawn = 0;

        enemyList = new CopyOnWriteArrayList<>();
    }

    void update() {

        if((System.currentTimeMillis() - timeSinceLastSpawn) >= spawnTime && (spawned < enemies)){

            if(spawned < 5) {
                enemyList.add(new smallOctoEnemy(context, 100, 500, 60, 60, 50, size, mBase));
            }
            if(spawned >= 5 && spawned < 9){
                enemyList.add(new bigOctoEnemy(context, 100, 500, 60, 60, 50, size, mBase));
            }
            if (spawned == 9){
                enemyList.add(new bossEnemy(context, 100, 500, 60, 60, 50, size, mBase));
            }
            timeSinceLastSpawn = System.currentTimeMillis();
            spawned += 1;
        }

        for(Enemy e: enemyList) {
            if(e.getStatus()) {
                e.update();
            } else {
                enemyList.remove(e);
                enemiesDead += 1;
            }
        }

    }

    void draw(Canvas canvas, Paint paint){
        for(Enemy e: enemyList) {
            e.draw(canvas, paint);
        }
    }

    public CopyOnWriteArrayList getEnemyList(){
        return enemyList;
    }

    int getRemaining(){
        return enemies - enemiesDead;
    }

}
