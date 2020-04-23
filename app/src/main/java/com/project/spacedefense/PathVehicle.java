package com.project.spacedefense;

import java.util.Vector;





// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Path Following

// Vehicle class

class PathVehicle {

  // All the usual stuff
  Vector position;
  Vector velocity;
  Vector acceleration;
  double r;
  double maxforce;    // Maximum steering force
  double maxspeed;    // Maximum speed

    // Constructor initialize all values
  PathVehicle( Vector l, float ms, float mf) {
    position = l.get();
    r = 4.0;
    maxspeed = ms;
    maxforce = mf;
    acceleration = new Vector(0, 0);
    velocity = new Vector((int) maxspeed, 0); //TODO uncast first parameter from int and back to float or double
  }

  // Main "run" function
  public void run() {
    update();
 //   display();  //TODO change this to draw
  }


  /*
  // This function implements Craig Reynolds' path following algorithm
  // http://www.red3d.com/cwr/steer/PathFollow.html
  void follow(Path p) {

    // Predict position 50 (arbitrary choice) frames ahead
    // This could be based on speed
    Vector predict = velocity.get();
    predict.normalize();
    predict.mult(50);
    Vector predictpos = Vector.add(position, predict);

    // Now we must find the normal to the path from the predicted position
    // We look at the normal for each line segment and pick out the closest one

    Vector normal = null;
    Vector target = null;
    float worldRecord = 1000000;  // Start with a very high record distance that can easily be beaten

    // Loop through all points of the path
    for (int i = 0; i < p.points.size()-1; i++) {

      // Look at a line segment
      Vector a = p.points.get(i);
      Vector b = p.points.get(i+1);

      // Get the normal point to that line
      Vector normalPoint = getNormalPoint(predictpos, a, b);
      // This only works because we know our path goes from left to right
      // We could have a more sophisticated test to tell if the point is in the line segment or not
      if (normalPoint.x < a.x || normalPoint.x > b.x) {
        // This is something of a hacky solution, but if it's not within the line segment
        // consider the normal to just be the end of the line segment (point b)
        normalPoint = b.get();
      }

      // How far away are we from the path?
      float distance = PVector.dist(predictpos, normalPoint);
      // Did we beat the record and find the closest line segment?
      if (distance < worldRecord) {
        worldRecord = distance;
        // If so the target we want to steer towards is the normal
        normal = normalPoint;

        // Look at the direction of the line segment so we can seek a little bit ahead of the normal
        PVector dir = PVector.sub(b, a);
        dir.normalize();
        // This is an oversimplification
        // Should be based on distance to path & velocity
        dir.mult(10);
        target = normalPoint.get();
        target.add(dir);
      }
    }

    // Only if the distance is greater than the path's radius do we bother to steer
    if (worldRecord > p.radius) {
      seek(target);
    }


    // Draw the debugging stuff
    if (debug) {
      // Draw predicted future position
      stroke(0);
      fill(0);
      line(position.x, position.y, predictpos.x, predictpos.y);
      ellipse(predictpos.x, predictpos.y, 4, 4);

      // Draw normal position
      stroke(0);
      fill(0);
      ellipse(normal.x, normal.y, 4, 4);
      // Draw actual target (red if steering towards it)
      line(predictpos.x, predictpos.y, normal.x, normal.y);
      if (worldRecord > p.radius) fill(255, 0, 0);
      noStroke();
      ellipse(target.x, target.y, 8, 8);
    }
  }


   */
//TODO Convert this math funtion into a working java version
  // A function to get the normal point from a point (p) to a line segment (a-b)
  // This function could be optimized to make fewer new Vector objects
  Vector getNormalPoint(Vector p, Vector a, Vector b) {
    // Vector from a to p
    Vector ap = Vector.sub(p, a);
    // Vector from a to b
    Vector ab = Vector.sub(b, a);
    ab.normalize(); // Normalize the line
    // Project vector "diff" onto line by using the dot product
    ab.mult(ap.dot(ab));
    Vector normalPoint = Vector.add(a, ab);
    return normalPoint;
  }


  // Method to update position
  void update() {
    // Update velocity
    velocity.add(acceleration);
    // Limit speed
    velocity.limit(maxspeed);
    position.add(velocity);
    // Reset accelertion to 0 each cycle
    acceleration.mult(0);
  }

  void applyForce(Vector force) {
    // We could add mass here if we want A = F / M
    acceleration.add(force);
  }


  // A method that calculates and applies a steering force towards a target
  // STEER = DESIRED MINUS VELOCITY
  void seek(Vector target) {
    Vector desired = Vector.sub(target, position);  // A vector pointing from the position to the target

    // If the magnitude of desired equals 0, skip out of here
    // (We could optimize this to check if x and y are 0 to avoid mag() square root
    if (desired.mag() == 0) return;

    // Normalize desired and scale to maximum speed
    desired.normalize();
    desired.mult(maxspeed);
    // Steering = Desired minus Velocity
    Vector steer = Vector.sub(desired, velocity);
    steer.limit(maxforce);  // Limit to maximum steering force

      applyForce(steer);
  }


  /*
  void display() {
    // Draw a triangle rotated in the direction of velocity
    float theta = velocity.heading2D() + radians(90);
    fill(175);
    stroke(0);
    pushMatrix();
    translate(position.x, position.y);
    rotate(theta);
    beginShape(PConstants.TRIANGLES);
    vertex(0, -r*2);
    vertex(-r, r*2);
    vertex(r, r*2);
    endShape();
    popMatrix();
  }
*/

  /*
  // Wraparound
  void borders(Path p) {
    if (position.x > p.getEnd().x + r) {
      position.x = p.getStart().x - r;
      position.y = p.getStart().y + (position.y-p.getEnd().y);
    }
  }

   */



}






}
