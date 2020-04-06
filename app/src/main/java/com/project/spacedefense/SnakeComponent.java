package com.project.spacedefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public interface SnakeComponent {
    void draw(ArrayList<Point> segmentLocations, Canvas canvas, Paint paint);
}

