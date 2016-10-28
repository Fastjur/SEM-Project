package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.SoundEffect;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;

import java.util.ArrayList;

public class BombDrop extends Drop {

  /**
   * Creates a drop, when picked up this drop breaks all the balls 1 time.
   */
  public BombDrop(String textureLocation, double startX, double startY, double movX, double movY,
                  int score) {
    super(textureLocation, startX, startY, movX, movY, score);
    this.collectSound = SoundEffect.COLLECT_BOMB;
  }


  /**
   * Method that handles the collision with a player.
   */
  @Override
  public void playerCollision(Player player) {
    for (GameObject object :
        new ArrayList<>(Application.getBoard().getCurrentScreen().objectList)) {
      if (object instanceof Ball) {
        ((Ball) object).destroyball();
      }
    }

    super.playerCollision(player);
  }
}

