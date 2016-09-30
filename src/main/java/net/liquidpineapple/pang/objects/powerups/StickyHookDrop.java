package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;

public class StickyHookDrop extends Drop {

  private int stickyDelayTime;

  /**
   * Creates a drop, the parameter stickyDelayTime is the amount of seconds the hook waits,
   * before removing itself when hitting the roof.
   *
   * @param stickyDelayTime The seconds waited before removal after roof collision.
   */
  public StickyHookDrop(String textureLocation, double startX, double startY,
                        double movX, double movY, int score, int stickyDelayTime) {
    super(textureLocation, startX, startY, movX, movY, score);
    this.stickyDelayTime = stickyDelayTime;
    if ( stickyDelayTime < 0) {
      this.stickyDelayTime = 0;
    }
  }

  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    player.setHookRemoveDelay(stickyDelayTime);
    super.playerCollision(player);
  }
}

