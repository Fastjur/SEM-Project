package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.objects.playerschemes.PlayerScheme;


/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends GameObject {

  private boolean shootheld = false;
  private boolean isHit = false;
  private PlayerScheme playerScheme;
  public int activeHooks = 0;
  public int maximumHooks = 1;
  public int shield = 0;
  public int hookRemoveDelay = 0;
  private  boolean frozen = false;

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
    xpos += dx;
    if (xpos < 1) {
      xpos = 1;
    }
    int boardWidth = Application.getBoard().getWidth();
    double playerMaxPosX = boardWidth - this.getWidth();
    if (xpos > playerMaxPosX) {
      xpos = playerMaxPosX;
    }
  }

  @Override
  public void doUpdate() {
    super.doUpdate();

    if (TimeSystem.getFrozen() > 0 && !frozen) {
      frozen = true;
      this.changeImage(playerScheme.getFrozenTextureName());
    } else if (frozen && TimeSystem.getFrozen() == 0) {
      frozen = false;
      if (shield > 0) {
        this.changeImage(playerScheme.getShieldTextureName());
      } else {
        this.changeImage(playerScheme.getTextureName());
      }
    }

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

    if (playerScheme.shootPressed() && !shootheld && activeHooks < maximumHooks) {
      HookAndRope newRope = new HookAndRope(getXpos(), 0, this, hookRemoveDelay);
      Application.getBoard().getCurrentScreen().objectList.add(newRope);
      activeHooks++;
      shootheld = true;
    }
    if (!playerScheme.shootPressed() && shootheld) {
      shootheld = false;
    }

    if (collisionPlayer() && !isHit && !Application.cheatMode && !frozen) {
      if (shield > 0) {
        shield -= 1;
        if (shield == 0) {
          this.changeImage(playerScheme.getTextureName());
        }
      } else {
        LifeSystem.loseLife();
      }
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
            drop.playerCollision(this);
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

  public final void setShield(int shield) {
    this.shield = shield;
    this.changeImage(playerScheme.getShieldTextureName());
  }
}
