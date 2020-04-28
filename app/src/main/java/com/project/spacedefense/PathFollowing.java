package com.project.spacedefense;

import android.content.res.Resources;

import java.util.Vector;

// This is an altered version of the original code found at the following:
// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Path Following
// Via Reynolds: // http://www.red3d.com/cwr/steer/PathFollow.html

public class PathFollowing {




    // A path object (series of connected points)
    Path path;


    // Two vehicles
    PathVehicle car1;
    PathVehicle car2;

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    void setup() {
        int height = getScreenHeight() / 2;
        int width = getScreenWidth() / 2;
        //size(640, 360); //TODO convert this to screen size decision
        // Call a function to generate new Path object
        //newPath();

        // Each vehicle has different maxspeed and maxforce for demo purposes

        //car1 = new PathVehicle(new Vector(0, height / 2), 2, 0.04);
        //car2 = new PathVehicle(new Vector(0, width / 2), 3, 0.1);
    }
/*
    void draw() {
        background(255);
        // Display the path
        path.display();
        // The boids follow the path
        car1.follow(path);
        car2.follow(path);
        // Call the generic run method (update, borders, display, etc.)
        car1.run();
        car2.run();

        car1.borders(path);
        car2.borders(path);

        // Instructions
        fill(0);
        text("Hit space bar to toggle debugging lines.\nClick the mouse to generate a new path.", 10, height - 30);
    }
*/
/*
    void newPath() {
        // A path is a series of connected points
        // A more sophisticated path might be a curve
        path = new Path();
        path.addPoint(-20, height / 2);
        path.addPoint(random(0, width / 2), random(0, height));
        path.addPoint(random(width / 2, width), random(0, height));
        path.addPoint(width + 20, height / 2);
    }*/
/*
    public void keyPressed() {
        if (key == ' ') {
            debug = !debug;
        }
    }

    public void mousePressed() {
        newPath();
    }
*/
}