package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;


public class StickyHookDrop extends Drop {

  private int stickyDelayTime;
  private static final String TEXTURELOCATION = "/sprites/drops/chain.png";

  /**
   * Creates a drop, the parameter stickyDelayTime is the amount of seconds the hook waits,
   * before removing itself when hitting the roof.
   *
   * @param stickyDelayTime The seconds waited before removal after roof collision.
   */
  public StickyHookDrop(double movY, int score, int stickyDelayTime) {
    super(TEXTURELOCATION, movY, score);
    this.collectSound = SoundEffect.COLLECT_STICKY;
    this.stickyDelayTime = Math.max(stickyDelayTime, 0);
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

