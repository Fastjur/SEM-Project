package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;


public class HeartDrop extends Drop {

  private static final String TEXTURELOCATION = "/sprites/drops/heart.png";

  /**
   * Creates a drop that gives one life when picked up.
   */
  public HeartDrop( double movY,
                   int score) {
    super(TEXTURELOCATION, movY, score);
    this.collectSound = SoundEffect.COLLECT_HEART;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    LifeSystem.gainLife();
    super.playerCollision(player);
  }
}

