package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.objects.playerschemes.PlayerScheme;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends GameObject {

  private boolean isHit = false;
  private double oldX;
  private PlayerScheme playerScheme;
  public int activeHooks = 0;

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

  public Player(double startX, double startY, PlayerScheme playerScheme) {
    super(playerScheme.getTextureName(), startX, startY);
    this.playerScheme = playerScheme;
  }

  /**
   * Method that moves the player.
   */
  public void move() {
    oldX = xpos;
    xpos += dx;
    if (xpos < 1) {
      xpos = 1;
    }
    int boardWidth = Application.getBoard().getWidth();
    double playerMaxPosX = boardWidth - this.getWidth();
    if (xpos > playerMaxPosX) {
      xpos = playerMaxPosX;
    }
    Logger.info("Moved " + this + " from x: " + oldX + " to x: " + xpos);
  }

  @Override
  public void doUpdate() {
    super.doUpdate();

    if (playerScheme.leftPressed()) {
      if (!playerScheme.rightPressed()) {
        dx = PlayerMovement.LEFT_DIRECTION.dx;
        move();
      }

    } else if (playerScheme.rightPressed()) {
      dx = PlayerMovement.RIGHT_DIRECTION.dx;
      move();
    } else {
      dx = PlayerMovement.NO_MOVEMENT.dx;
    }

    if (playerScheme.shootPressed()) {
      if (activeHooks < 1) {
        HookAndRope newRope = new HookAndRope(getXpos(), 0, this);
        Application.getBoard().addObject(newRope);
        activeHooks++;
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
        double playerPos = this.getXpos() + (this.getImage().getWidth(null)) / 2;

        if (playerPos - object.getXpos() >= 0 && playerPos - object.getXpos() <= object.getWidth()
            && object.getYpos() + object.getHeight() >= this.getYpos()) {

          Logger.info(playerScheme.getName() + " collided with object:" + object);
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
