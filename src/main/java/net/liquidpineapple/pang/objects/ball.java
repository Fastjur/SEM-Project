package net.liquidpineapple.pang.objects;

import java.lang.reflect.Array;
import java.util.Random;

import static java.lang.Math.random;
import static javafx.scene.input.KeyCode.X;
import static javafx.scene.input.KeyCode.Y;

/**
 * Created by Erik on 7-9-2016.
 */
public class Ball {

    //Size 1 is the smallest ball.
    int size;
    int movX;
    int movY;

    /**
     * Creates a ball with a set horizontal speed and a variable vertical speed depending on size.
     *
     * @param spawnX    X-coordinate to make the ball
     * @param spawnY    Y-coordinate to make the ball
     * @param direction Direction the ball should move, valid inputs are: "left" and "right", anything else randomizes the direction.
     * @param sizeIn    The desired size of the ball, Greater then 4 or smaller then 0 results in a size 4 ball.
     */
    public Ball(int spawnX, int spawnY, String direction, int sizeIn){
        if(sizeIn>0 && sizeIn<5){
        size = sizeIn;}
        else{size = 4;}
        //Makes the ball go either left or right depending on input, if the input is incorrect it randomly chooses a direction.
        if(direction=="left"){movX=-15;}
        else if(direction=="right"){movX=15;}
        else {Random coinflip = new Random();
            if(coinflip.nextInt(1)==1){movX=-15;}
            else {movX=15;}}
        movY = 3*size+5;
        //Create actual ball here.
    }

    /**
     * Calculates and returns the next position the ball should be drawn in.
     * !!currently not returing
     */
    public void nextMovement(){
        //int new X = selfX + movX;
        movY += 2;
        //int new Y = selfY + movY;
        //return (0,0);
    }

    /**
     * Handles floor colission, speed is set such that it always goes to the same height from the floor bounced on. hitting a higher platform means ending up higher too.
     */
    void collidefloor(){
        //This way it always goes to the same height after hitting a surface.
        movY = -(3*size+5);
    }

    /**
     * Handles wall colission by negating the horizontal direction.
     */
    void collidewall(){
        //We always move the same speed so we can just change direction.
        movX = -movX;
    }

    /**
     * Adds score (WIP), spawns two balls going left and right respectively on itself, and removes this ball.
     */
    void destroyball(){
        //setscore+100
        if(size!=1){
            //ball(selfX, selfY, "left", size-1);
            //ball(selfX, selfY, "right", size-1);
        }
        //remove ball
    }

    public int getSize(){
        return size;
    }

    public int getMovX(){
        return movX;
    }

    public int getMovY(){
        return movY;
    }

}
