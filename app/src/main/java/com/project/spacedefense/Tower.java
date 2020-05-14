package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower {

    private int x,y, width, height, damage, range;
    private long firingSpeed, timeSinceLastShot;
    private float angle;

    private Context context;

    private Bitmap mTower, mCannon;
    private ArrayList<Projectile> projectiles;
    private CopyOnWriteArrayList<Enemy> enemies;
    private Enemy target;
    private EnemyWave mEnemyWave;
    private boolean targeted;
    //private Matrix m;

    Tower(Context context, int x, int y, EnemyWave mEnemyWave){

        this.enemies = new CopyOnWriteArrayList<>();
        this.context = context;
        this.x = x;
        this.y = y;
        this.width = 75;
        this.height = 75;
        this.damage = 15;
        this.range = 900;
        this.mEnemyWave = mEnemyWave;
        this.enemies = mEnemyWave.getEnemyList();
        this.targeted = false;
        //this.target = getTarget();
        //this.angle = calculateAngle();

        this.firingSpeed = 2000;

        this.projectiles = new ArrayList<>();


        mTower = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.redtower);

        mTower = Bitmap
                .createScaledBitmap(mTower, width, height, true);
        


    }

    private Enemy getTarget(){
        //return enemies.get(0);
        Enemy closest = null;
        float closestDistance = 10000;
        for(Enemy e : enemies){
            if(isInRange(e) && findDistance(e) < closestDistance){
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        if (closest != null){
            targeted = true;
        }
        return closest;
    }

    private boolean isInRange(Enemy e){
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);

        return xDistance < range && yDistance < range;
    }

    private float findDistance(Enemy e){
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - x);
        return xDistance + yDistance;
    }

    private float calculateAngle(){

        if (targeted) {
            double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
            return (float) Math.toDegrees(angleTemp) - 90;
        }

        return 0;
    }

    private void shoot(){
        //timeSinceLastShot = 0;
        projectiles.add(new Projectile(context, target, x, y, damage));
        System.out.println("New bullet");
        timeSinceLastShot = System.currentTimeMillis();
    }

    void update(){
        if(enemies.size() > 0) {
            if (!targeted || !target.getStatus()) {
                target = getTarget();
            }
            if ((System.currentTimeMillis() - timeSinceLastShot) >= firingSpeed) {
                shoot();
            }
            for (Projectile p : projectiles) {
                if (p.getStatus()) {
                    p.update();
                } else {
                    projectiles.remove(p);
                }
            }
            angle = calculateAngle();
        }
    }

    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mTower, x - (width / 2), y - (height / 2), paint);



        for(Projectile p : projectiles){
            p.draw(canvas, paint);
        }
    }
}
