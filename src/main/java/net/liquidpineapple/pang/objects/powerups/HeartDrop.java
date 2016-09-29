package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;

public class HeartDrop extends Drop {

  private int livesChange;

  /**
   * Creates a drop, with additional parameter livesChange,
   * which decides how much the live changes when this object is picked up.
   *
   * @param livesChange The change in the amount of lives when drop is picked up.
   */
  public HeartDrop(String textureLocation, double startX, double startY, double movX, double movY,
                   int score, int livesChange) {
    super(textureLocation, startX, startY, movX, movY, score);
    this.livesChange = livesChange;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    while (livesChange > 0) {
      LifeSystem.gainLife();
      livesChange -= 1;
    }
    while (livesChange < 0) {
      LifeSystem.loseLife();
      livesChange += 1;
    }
    super.playerCollision(player);
  }
}

