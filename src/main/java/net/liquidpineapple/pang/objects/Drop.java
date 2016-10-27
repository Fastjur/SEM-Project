package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.AudioSystem;
import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.gui.ScoreSystem;

import java.awt.Point;

/**
 * Created by Erik on 22-9-2016.
 */

public class Drop extends GameObject {

  private int score;
  private double movY;
  protected SoundEffect collectSound;

  /**
   * Method that creates a new drop.
   *
   * @param textureLocation as image
   * @param score           as score change when this pickup collides with player
   * @param movY            as vertical movement every update
   */
  public Drop(String textureLocation, double movY, int score) {
    super(textureLocation, 0, 0);
    this.score = score;
    this.collectSound = SoundEffect.COLLECT_COIN;
    this.movY = movY;
  }

  /**
   * Calculates and sets the next position the ball should be drawn in.
   */
  public void move() {
    ypos += movY;
  }

  /**
   * Method that updates the drop.
   */
  @Override
  public void doUpdate() {
    super.doUpdate();

    move();
    ypos = Math.min(ypos + this.getHeight(), Application.getBoard().getHeight()) - this.getHeight();
    xpos = Math.min(xpos + this.getWidth(), Application.getBoard().getWidth()) - this.getWidth();
    xpos = Math.max(xpos,0);
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
