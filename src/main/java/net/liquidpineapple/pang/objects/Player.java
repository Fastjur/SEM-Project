package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.logger.Logger;

import java.awt.event.KeyEvent;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends GameObject {

  private static final String textureLocation = "/sprites/player/p1_front.png";
  public static boolean isHit = false;
  private double oldX;

  private enum PlayerMovement {
    LEFT_DIRECTION(-4 / 5.0),
    RIGHT_DIRECTION(4 / 5.0),
    NO_MOVEMENT(0);

    private final double dx;

    PlayerMovement(double dx) {
      this.dx = dx;
    }
  }

  private double dx;

  public Player(double startX, double startY) {
    super(textureLocation, startX, startY);
  }

  /**
   * Method that moves the player.
   */
  public void move() {
    oldX = xPos;
    xPos += dx;
    if (xPos < 1) {
      xPos = 1;
    }
    int boardWidth = Application.getBoard().getWidth();
    double playerMaxPosX = boardWidth - this.getWidth();
    if (xPos > playerMaxPosX) {
      xPos = playerMaxPosX;
    }
    Logger.info("Moved " + this + " from x: " + oldX + " to x: " + xPos);
  }

  @Override
  public void doUpdate() {
    super.doUpdate();

    if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
      if (!InputHandler.isKeyPressed(KeyEvent.VK_D)) {
        dx = PlayerMovement.LEFT_DIRECTION.dx;
        move();
      }

    } else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
      dx = PlayerMovement.RIGHT_DIRECTION.dx;
      move();
    } else {
      dx = PlayerMovement.NO_MOVEMENT.dx;
    }

    if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
      boolean hookInUse = false;
      for (GameObject o : Application.getBoard().getCurrentScreen().objectList) {
        if (o instanceof HookAndRope) {
          hookInUse = true;

        }
      }
      if (!hookInUse) {
        HookAndRope newRope = new HookAndRope(getXPos(), 0);
        Application.getBoard().addObject(newRope);
      }
    }

    if (collisionPlayer() && !isHit && !Application.cheatMode) {
      LifeSystem.loseLife();
      isHit = true;
    }
  }

  /**
   * Method that handles the collision of a player with a ball.
   * @return a true if player collides with the ball, false otherwise.
   */
  public boolean collisionPlayer() {
    for (GameObject object : Application.getBoard().getCurrentScreen().objectList) {
      if (object instanceof Ball || object instanceof Drop) {
        double playerPos = this.getXPos() + (this.getImage().getWidth(null)) / 2;

        if (playerPos - object.getXPos() >= 0 && playerPos - object.getXPos() <= object.getWidth()
            && object.getYPos() + object.getHeight() >= this.getYPos()) {

          Logger.info("Player Collided with object:" + object);
          if (object instanceof Drop) {
            Drop drop = (Drop) object;
            drop.playerCollision();
            return false;
          } else {
            return true;
          }

        }
      }
    }
    isHit = false;
    return false;
  }
}
