package net.liquidpineapple.pang.objects;

import java.util.Random;

import static java.lang.Math.random;
import static javafx.scene.input.KeyCode.X;
import static javafx.scene.input.KeyCode.Y;

/**
 * Created by Erik on 7-9-2016.
 */
public class Ball extends GameObject {

    public enum Directions{
        LEFT, RIGHT, RANDOM
    }

    //Size 1 is the smallest ball.
    int size;
    int movX;
    int movY;
    private static final String textureLocationBlue = "/sprites/Balls/Blue/frame 1.png";
    private static final String textureLocationGreen = "/sprites/Balls/Green/frame 1.png";
    private static final String textureLocationRed = "/sprites/Balls/Red/frame 1.png";
    private static final String textureLocationYellow = "/sprites/Balls/Yellow/frame 1.png";
    /**
     * Creates a ball with a set horizontal speed and a variable vertical speed depending on size.
     *
     * @param startX    X-coordinate to make the ball
     * @param startY   Y-coordinate to make the ball
     * @param direction Direction the ball should move, valid inputs are: "left" and "right", anything else randomizes the direction.
     * @param sizeIn    The desired size of the ball, Greater then 4 or smaller then 0 results in a size 4 ball.
     */
    public Ball(int startX, int startY, Directions direction, int sizeIn){
        super(textureLocationBlue, startX, startY);
        if(size == 1){
            ChangeImage(textureLocationGreen);
        }
        else if(size == 2){
            ChangeImage(textureLocationRed);
        }
        else if (size == 3){
            ChangeImage(textureLocationYellow);
        }

        if(sizeIn>0 && sizeIn<5){
        size = sizeIn;}
        else {
            size = 4;}

        //Makes the ball go either left or right depending on input, if the input is incorrect it randomly chooses a direction.
        if(direction.equals(Directions.LEFT)){
            movX=-15;}
        else if(direction.equals(Directions.RIGHT)){
            movX=15;}
        else {
            //Should be moved to somewhere we are sure it's running and called from there, Not important because for now the ball can just move to the same side.
            Random coinflip = new Random();
            if(coinflip.nextInt(1)==1){
                movX=-15;}
            else {
                movX=15;}
        }
        movY = 3*size+5;
        //Create actual ball here.
    }

    /**
     * Calculates and sets the next position the ball should be drawn in.
     */
    public void move(){
        xPos = xPos + movX;
        movY += 2;
        yPos = yPos + movY;
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
            new Ball(xPos, yPos, Directions.LEFT, size-1);
            new Ball(xPos, yPos, Directions.RIGHT, size-1);
        }
        //remove ball
    }

    public int getBallSize(){
        return size;
    }

    public int getMovX(){
        return movX;
    }

    public int getMovY(){
        return movY;
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

}
