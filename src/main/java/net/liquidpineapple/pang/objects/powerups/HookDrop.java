package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;


public class HookDrop extends Drop {

  private int hookChange;
  private static final String TEXTURELOCATION = "/sprites/drops/chain.png";

  /**
   * Creates a drop, with additional parameter hookChange,
   * which decides how many hooks are allowed to be shot at the same time for this player.
   *
   * @param hookChange The amount of maximum allowed hooks after picking up this drop.
   *                   It will always be set to a minimum of 1.
   */
  public HookDrop(double movY, int score,
                  int hookChange) {
    super(TEXTURELOCATION, movY, score);
    this.hookChange = hookChange;
    this.collectSound = SoundEffect.COLLECT_HOOK;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    player.setMaximumHooks( Math.max(hookChange, 1));
    super.playerCollision(player);
  }
}

