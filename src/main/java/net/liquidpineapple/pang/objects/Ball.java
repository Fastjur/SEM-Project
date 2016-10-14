package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.AudioSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.logger.Logger;

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
  private double oldX;
  private double oldY;


  private static final String textureLocationBlue = "/sprites/Balls/blue.png";
  private static final String textureLocationGreen = "/sprites/Balls/green.png";
  private static final String textureLocationRed = "/sprites/Balls/red.png";
  private static final String textureLocationYellow = "/sprites/Balls/yellow.png";

  /**
   * Creates a ball with a set horizontal speed and a variable vertical speed depending on size.
   *
   * @param startX    - X-coordinate to make the ball
   * @param startY    - Y-coordinate to make the ball
   * @param direction - Direction the ball should move, valid inputs are: "left" and "right",
   *                  anything else randomizes the direction.
   * @param sizeIn    - The desired size of the ball, Greater then 4 or smaller then 0 results in
   *                  a size 4 ball.
   */
  public Ball(double startX, double startY, BallMovement direction, int sizeIn) {
    super(textureLocationBlue, startX, startY);

    if (sizeIn > 0 && sizeIn < 5) {
      ballSize = sizeIn;
    } else {
      ballSize = 4;
    }

    switch (ballSize) {
      case 1:
        changeImage(textureLocationGreen);
        break;
      case 2:
        changeImage(textureLocationRed);
        break;
      case 3:
        changeImage(textureLocationYellow);
        break;
      default:
        changeImage(textureLocationBlue);
        break;
    }

    if (direction.equals(BallMovement.LEFT_MOVEMENT)) {
      movX = BallMovement.LEFT_MOVEMENT.getDx();
    } else if (direction.equals(BallMovement.RIGHT_MOVEMENT)) {
      movX = BallMovement.RIGHT_MOVEMENT.getDx();
    }
    movY = -2;
  }

  /**
   * Method that updates the ball.
   */
  @Override
  public void doUpdate() {
    super.doUpdate();

    move();
    if (ypos + this.getHeight() > Application.getBoard().getHeight()) {
      ypos = Application.getBoard().getHeight() - this.getHeight();
    }
    if (xpos + this.getWidth() > Application.getBoard().getWidth()) {
      xpos = Application.getBoard().getWidth() - this.getWidth();
    }
    if (xpos < 0) {
      xpos = 0;
    }
    if (xpos == 0 || xpos + this.getWidth() == Application.getBoard().getWidth()) {
      movX = -movX;
    }

    if (ypos + this.getHeight() == Application.getBoard().getHeight()) {
      movY = -6;
    }

    if (collisionHook()) {
      destroyball();
    }
  }

  /**
   * Calculates and sets the next position the ball should be drawn in.
   */
  public void move() {
    oldX = xpos;
    oldY = ypos;
    xpos += movX;
    movY += 1 / 25.0;
    ypos += movY;
    Logger.info("Moved " + this.toString() + " from (" + oldX + ", " + oldY + ") to (" + xpos
        + ", " + ypos + ")");
  }

  /**
   * Method that checks whether the ball collides with the Hook.
   *
   * @return returns true if the hook is hit, returns false otherwise.
   */
  public boolean collisionHook() {
    HookAndRope activeRope = null;
    for (GameObject o : Application.getBoard().getCurrentScreen().objectList) {
      if (o instanceof HookAndRope) {
        activeRope = (HookAndRope) o;

        double ropePos = activeRope.getXpos() + (activeRope.getWidth()) / 2;

        if (ropePos - this.getXpos() >= 0 && ropePos - this.getXpos() <= this.getWidth()
            && this.getYpos() + this.getHeight() >= activeRope.getYpos()) {

          activeRope.getPlayer().activeHooks--;
          Application.getBoard().getCurrentScreen().objectList.remove(activeRope);
          Logger.info("Collision between " + this + " and " + activeRope);
          return true;
        }
      }
    }
    return false;
  }


  /**
   * Adds score (WIP), spawns two balls going left and right respectively on itself, and removes
   * this ball.
   */
  public void destroyball() {
    ScoreSystem.addScore(100);
    if (ballSize != 1) {
      Ball smallerBall = new Ball(xpos, ypos, BallMovement.LEFT_MOVEMENT, ballSize - 1);
      Ball smallerBall2 = new Ball(xpos + 1, ypos, BallMovement.RIGHT_MOVEMENT, ballSize - 1);
      Application.getBoard().getCurrentScreen().objectList.add(smallerBall);
      Application.getBoard().getCurrentScreen().objectList.add(smallerBall2);
    }
    DropRandomizer.getInstance().rollRandomdrop(xpos + (this.getWidth() / 2), ypos
        + (this.getHeight() / 2));
    //remove ball
    Logger.info("Removing " + this);
    AudioSystem.playEffect("pop");
    Application.getBoard().getCurrentScreen().objectList.remove(this);
  }

}
