package com.project.spacedefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Towers {

    private ArrayList<Tower> towerList;

    private Context context;
    private EnemyWave mEnemyWave;

    Towers(Context context, EnemyWave mEnemyWave){

        this.context = context;
        this.mEnemyWave = mEnemyWave;

        towerList = new ArrayList<>();


    }

    void update(){
        for(Tower t: towerList) {
            t.update();
        }
    }

    void draw(Canvas c, Paint p){
        for(Tower t: towerList) {
            t.draw(c, p);
        }
    }

    void AddFireTower(int x, int y){
        towerList.add(new fireTower(context, x, y, mEnemyWave));
    }
    void AddIceTower(int x, int y){
        towerList.add(new iceTower(context, x, y, mEnemyWave));
    }
    void AddDestrctionTower(int x, int y){
        towerList.add(new destructionTower(context, x, y, mEnemyWave));
    }

    void reset(){
        towerList.clear();
        towerList.add(new fireTower(context, 1000, 300, mEnemyWave));
        towerList.add(new iceTower(context, 800, 600, mEnemyWave));
    }
}
