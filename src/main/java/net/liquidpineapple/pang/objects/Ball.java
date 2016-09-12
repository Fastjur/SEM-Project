package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Random;

/**
 * Created by Erik on 7-9-2016.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Ball extends GameObject {

    //Size 1 is the smallest ball.
    private int ballSize;
    private int movX;
    private int movY;

    private static final String textureLocationBlue = "/sprites/Balls/Blue/frame 1.png";
    private static final String textureLocationGreen = "/sprites/Balls/Green/frame 1.png";
    private static final String textureLocationRed = "/sprites/Balls/Red/frame 1.png";
    private static final String textureLocationYellow = "/sprites/Balls/Yellow/frame 1.png";

    private Random randomDirection = new Random();

    /**
     * Creates a ball with a set horizontal speed and a variable vertical speed depending on size.
     *
     * @param startX    X-coordinate to make the ball
     * @param startY   Y-coordinate to make the ball
     * @param direction Direction the ball should move, valid inputs are: "left" and "right", anything else randomizes the direction.
     * @param sizeIn    The desired size of the ball, Greater then 4 or smaller then 0 results in a size 4 ball.
     */
    public Ball(int startX, int startY, BallMovement direction, int sizeIn){
        super(textureLocationBlue, startX, startY);

        if (sizeIn > 0 && sizeIn < 5) {
            ballSize = sizeIn;
        } else {
            ballSize = 4;
        }

        switch (ballSize) {
            case 1:
                changeImage(textureLocationGreen); break;
            case 2:
                changeImage(textureLocationRed); break;
            case 3:
                changeImage(textureLocationYellow); break;
            default:
                changeImage(textureLocationBlue); break;
        }

        if (direction.equals(BallMovement.LEFT_MOVEMENT)) {
            movX = BallMovement.LEFT_MOVEMENT.getDx();
        } else if (direction.equals(BallMovement.RIGHT_MOVEMENT)) {
            movX = BallMovement.RIGHT_MOVEMENT.getDx();
        }
        movY = 3* ballSize +5;
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
        movY = -(3* ballSize +5);
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
        if (ballSize != 1){
            new Ball(xPos, yPos, BallMovement.LEFT_MOVEMENT, ballSize -1);
            new Ball(xPos, yPos, BallMovement.RIGHT_MOVEMENT, ballSize -1);
        }
        //remove ball
    }

}
