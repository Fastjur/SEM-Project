package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;


public class FreezeTimeDrop extends Drop {

  private int timeFrozen;

  /**
   * Creates a drop, it freezes time for timeFrozen seconds,
   * during this time the player is invincible
   *
   * @param timeFrozen the amount of seconds the time is frozen.
   */
  public FreezeTimeDrop(String textureLocation, double startX, double startY, double movX,
                        double movY, int score, int timeFrozen) {
    super(textureLocation, startX, startY, movX, movY, score);
    this.timeFrozen = timeFrozen;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    TimeSystem.setFrozen(timeFrozen);
    super.playerCollision(player);
  }
}

