package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower {

    private int x,y, width, height, damage;
    private float firingSpeed, timeSinceLastShot, angle;

    private Context context;

    private Bitmap mTower;
    private ArrayList<Projectile> projectiles;
    private CopyOnWriteArrayList<Enemy> enemies;
    private Enemy target;
    private EnemyWave mEnemyWave;

    Tower(Context context, int x, int y, EnemyWave mEnemyWave){

        this.enemies = new CopyOnWriteArrayList<>();
        this.context = context;
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 60;
        this.damage = 50;
        this.mEnemyWave = mEnemyWave;
        this.enemies = mEnemyWave.getEnemyList();
        //this.target = getTarget();
        //this.angle = calculateAngle();

        this.firingSpeed = 25;

        this.projectiles = new ArrayList<>();


        mTower = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.guns);

        mTower = Bitmap
                .createScaledBitmap(mTower, width, height, true);
    }

    private Enemy getTarget(){
        if(enemies.size() > 0) {
            return enemies.get(0);
        }

        return null;
    }

    private float calculateAngle(){
        if(target != null) {
            double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
            return (float) Math.toDegrees(angleTemp) - 90;
        }

        return 0;
    }

    private void shoot(){
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(context, target, x, y, damage, firingSpeed));
    }

    void update(){

        target = getTarget();
        if((System.currentTimeMillis() - timeSinceLastShot) >= firingSpeed){
            shoot();
        }

        for(Projectile p : projectiles){
            p.update();
        }

        angle = calculateAngle();
    }

    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mTower, x - (width / 2), y - (height / 2), paint);

        for(Projectile p : projectiles){
            p.draw(canvas, paint);
        }
    }
}
