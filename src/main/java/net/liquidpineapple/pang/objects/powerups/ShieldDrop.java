package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;


public class ShieldDrop extends Drop {

  private int shield;
  private static final String TEXTURELOCATION = "/sprites/drops/shieldBronze.png";

  /**
   * Creates a drop, with additional parameter Shield,
   * which causes the player to have a shield that breaks after Shield amount of hits.
   * Shields do stack, a new shield will be added on top of existing shields.
   *
   * @param shield The amount of hits needed before the shield breaks.
   */
  public ShieldDrop(double movY, int score,
                    int shield) {
    super(TEXTURELOCATION, movY, score);
    this.shield = shield;
    this.collectSound = SoundEffect.COLLECT_SHIELD;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    player.setShield(player.getShield() + shield);
    super.playerCollision(player);
  }
}

