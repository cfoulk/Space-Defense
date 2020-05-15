package com.project.spacedefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

class Tower {

    int x,y;
    private int range;
    int width, height, damage;
    long timeSinceLastShot;
    private float angle;
    long firingSpeed;

    Context context;

    Bitmap mTower, mCannon;
    ArrayList<Projectile> projectiles;
    private CopyOnWriteArrayList<Enemy> enemies;
    Enemy target;
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
        this.range = 900;
        this.mEnemyWave = mEnemyWave;
        this.enemies = mEnemyWave.getEnemyList();
        this.targeted = false;
        this.projectiles = new ArrayList<>();


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


    // was suppose to calc angle for the cannon on the towers to rotate
    private float calculateAngle(){

        if (targeted) {
            double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
            return (float) Math.toDegrees(angleTemp) - 90;
        }

        return 0;
    }

    void shoot(){
        projectiles.add(new Projectile(context, target, x, y, damage));
        System.out.println("New bullet");
        timeSinceLastShot = System.currentTimeMillis();
    }

    void update(){
        if(enemies.size() > 0) {
            if (!targeted) { // || !target.getstatus()
                target = getTarget();
            }

            if(target == null || !target.isAlive){
                targeted = false;
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
            //angle = calculateAngle();
        }
    }

    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mTower, x - (width / 2), y - (height / 2), paint);
        for(Projectile p : projectiles){
            p.draw(canvas, paint);
        }
    }
}
