package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.AudioSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;

/**
 * Created by Erik on 22-9-2016.
 */

public class Drop extends GameObject {

  private int score;
  private double movY;
  private double movX;
  protected String collectSound;

  /**
   * Method that creates a new drop.
   *
   * @param textureLocation as image
   * @param startX          as start position X
   * @param startY          as start position Y
   * @param score           as score change when this pickup collides with player
   * @param movX            as vertical movement every update
   * @param movY            as horizontal movement every update
   */
  public Drop(String textureLocation, double startX, double startY, double movX, double movY,
              int score) {
    super(textureLocation, startX, startY);
    this.score = score;
    this.collectSound = "coin";
    this.movY = movY;
    this.movX = movX;
  }

  /**
   * Calculates and sets the next position the ball should be drawn in.
   */
  public void move() {
    xpos += movX;
    ypos += movY;
  }

  /**
   * Method that updates the drop.
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

    if (ypos + this.getHeight() == Application.getBoard().getHeight()) {
      movX = 0;
    } else if (xpos == 0 || xpos + this.getWidth() == Application.getBoard().getWidth()) {
      movX = -movX;
    }
  }

  /**
   * Method that handles the collision with a player.
   */
  public void playerCollision(Player player) {
    ScoreSystem.addScore(score);
    Application.getBoard().getCurrentScreen().objectList.remove(this);
    AudioSystem.playEffect(collectSound);
  }
}
