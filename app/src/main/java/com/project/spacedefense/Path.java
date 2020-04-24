package com.project.spacedefense;

// code has been altered from its original form:
// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Path Following

import java.util.ArrayList;
import java.util.Vector;


//A path is a starting point and an endpoint,
//It is only two points.
//The radius is how wide the path is

class Path {

    // A Path is an arraylist of points (PVector objects)
    ArrayList<Vector> points;
    // A path has a radius, i.e how far is it ok for the boid to wander off
    double radius;

    Path() {
        // Arbitrary radius of 20
        radius = 20;
        points = new ArrayList<Vector>();

        //designate start and end point

        Vector start = new Vector<>();
        Vector end = new Vector<>();


    }

    // Add a point to the path
    void addPoint(float x, float y) {
        //TODO for now this is casted to an int but must be changed to float to work properly
        Vector point = new Vector((int)x, (int)y);
        points.add(point);
    }

    Vector getStart() {
        return points.get(0);
    }

    Vector getEnd() {
        return points.get(points.size()-1);
    }




/*
    // Draw the path This is pde code? Can be translated to android later,
    //For now I will rely on playthrough testing unless it proves too buggy
    void display() {
        // Draw thick line for radius
        stroke(175);
        strokeWeight(radius*2);
        noFill();
        beginShape();
        for (PVector v : points) {
            vertex(v.x, v.y);
        }
        endShape();
        // Draw thin line for center of path
        stroke(0);
        strokeWeight(1);
        noFill();
        beginShape();
        for (PVector v : points) {
            vertex(v.x, v.y);
        }
        endShape();
    }

 */
}



