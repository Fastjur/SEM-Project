package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;

public class HookDrop extends Drop {

  private int hookChange;

  /**
   * Creates a drop, with additional parameter hookChange,
   * which decides how many hooks are allowed to be shot at the same time for this player.
   *
   * @param hookChange The amount of maximum allowed hooks after picking up this drop.
   *                   It will always be set to a minimum of 1.
   */
  public HookDrop(String textureLocation, double startX, double startY, double movX, double movY,
                  int score, int hookChange) {
    super(textureLocation, startX, startY, movX, movY, score);
    this.hookChange = hookChange;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    player.setMaximumHooks(hookChange);
    if ( player.getMaximumHooks() <= 0) {
      player.setMaximumHooks(1);
    }
    super.playerCollision(player);
  }
}

