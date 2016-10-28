package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;


public class HeartDrop extends Drop {

  private int livesChange;
  private static final String TEXTURELOCATION = "/sprites/drops/heart.png";

  /**
   * Creates a drop, with additional parameter livesChange,
   * which decides how much the live changes when this object is picked up.
   *
   * @param livesChange The change in the amount of lives when drop is picked up.
   */
  public HeartDrop( double movY,
                   int score, int livesChange) {
    super(TEXTURELOCATION, movY, score);
    this.collectSound = SoundEffect.COLLECT_HEART;
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
      System.out.println("GAIN LIFE HEARTDROP");
    }
    while (livesChange < 0) {
      LifeSystem.loseLife();
      livesChange += 1;
      System.out.println("LOSE LIFE HEARTDROP");
    }
    super.playerCollision(player);
  }
}

