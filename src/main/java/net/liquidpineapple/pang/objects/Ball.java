package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.liquidpineapple.pang.Application;

/**
 * Class that represents the ball.
 * Created by Erik on 7-9-2016.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Ball extends GameObject {

    //Size 1 is the smallest ball.
    private int ballSize;
    private double movX;
    private double movY;


    private static final String textureLocationBlue = "/sprites/Balls/blue.png";
    private static final String textureLocationGreen = "/sprites/Balls/green.png";
    private static final String textureLocationRed = "/sprites/Balls/red.png";
    private static final String textureLocationYellow = "/sprites/Balls/yellow.png";

    /**
     * Creates a ball with a set horizontal speed and a variable vertical speed depending on size.
     *
     * @param startX - X-coordinate to make the ball
     * @param startY - Y-coordinate to make the ball
     * @param direction - Direction the ball should move, valid inputs are: "left" and "right", anything else randomizes the direction.
     * @param sizeIn - The desired size of the ball, Greater then 4 or smaller then 0 results in a size 4 ball.
     */
    public Ball(double startX, double startY, BallMovement direction, int sizeIn){
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
        movY = 0;
    }

    /**
     * Method that updates the ball.
     */
    @Override
    public void doUpdate() {
        super.doUpdate();

        move();
        if (yPos + this.getHeight() > Application.getBoard().getHeight())
            yPos = Application.getBoard().getHeight() - this.getHeight();
        if (xPos + this.getWidth() > Application.getBoard().getWidth())
            xPos = Application.getBoard().getWidth() - this.getWidth();
        if (xPos < 0)
            xPos = 0;

        if (xPos == 0 || xPos + this.getWidth() == Application.getBoard().getWidth()) {
            movX = -movX;
        }

        if (yPos + this.getHeight() == Application.getBoard().getHeight()) {
            movY = -6;
    }

    if(collisionHook()){
        destroyball();
    }
}

    /**
     * Calculates and sets the next position the ball should be drawn in.
     */
    public void move(){
        xPos += movX;
        movY += 1/25.0;
        yPos += movY;
    }

    /**
     * Method that checks whether the ball collides with the Hook.
     * @return returns true if the hook is hit, returns false otherwise.
     */
    public boolean collisionHook(){
        boolean hookInUse = false;
        HookAndRope activeRope = null;
        for(GameObject o : Application.getBoard().getCurrentScreen().objectList){
            if(o instanceof HookAndRope){
                hookInUse = true;
                activeRope = (HookAndRope) o;
            }
        }

        if(!hookInUse){
            return false;
        }

        double ropePos = activeRope.getXPos() + (activeRope.getWidth())/2;

        if(ropePos - this.getXPos() >= 0 && ropePos - this.getXPos() <= this.getWidth()) {
            if(this.getYPos()+ this.getHeight() >= activeRope.getYPos()){
                Application.getBoard().getCurrentScreen().objectList.remove(activeRope);
                return true;
            }
        }

        return false;
    }


    /**
     * Adds score (WIP), spawns two balls going left and right respectively on itself, and removes this ball.
     */
    public void destroyball(){
        ScoreSystem.addScore(100);
        if (ballSize != 1){
            Ball smallerBall = new Ball(xPos, yPos, BallMovement.LEFT_MOVEMENT, ballSize -1);
            Ball smallerBall2 = new Ball(xPos+1, yPos, BallMovement.RIGHT_MOVEMENT, ballSize -1);
            Application.getBoard().addObject(smallerBall);
            Application.getBoard().addObject(smallerBall2);
        }
        Application.getDropRandomizer().rollRandomdrop(xPos+(this.getWidth()/2), yPos+(this.getHeight()/2));
        //remove ball
        Application.getBoard().getCurrentScreen().objectList.remove(this);
    }

}
