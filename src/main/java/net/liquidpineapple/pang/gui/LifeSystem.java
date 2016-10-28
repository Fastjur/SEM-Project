package net.liquidpineapple.pang.gui;


import lombok.Getter;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.screens.GameOverScreen;


/**
 * Class that represents the lifeSystem.
 * Created by Erik on 13-9-2016.
 */
public class LifeSystem extends GameObject {

  private static final String textureH0 = "/sprites/hearts0.png";
  private static final String textureH1 = "/sprites/hearts1.png";
  private static final String textureH2 = "/sprites/hearts2.png";
  private static final String textureH3 = "/sprites/hearts3.png";

  @Getter
  private static int lives;
  @Getter
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  private static LifeSystem instance = new LifeSystem();
  private int lastLives;

  /**
   * Singleton constructor for the life system.
   */
  private LifeSystem() {
    super(textureH3, 636, 5);
    Logger.info("LifeSystem starting.");
    lives = 3;
    lastLives = 4;
  }

  /**
   * Method that decreases the lives of the player when the player gets hit.
   */
  public static void loseLife() {
    if (lives > 0) {
      lives -= 1;
    }
  }

  /**
   * Method that increases the life with 1 if needed.
   */
  public static void gainLife() {
    if (lives < 3) {
      lives += 1;
    }
  }

  /**
   * Method that updates the shown lives on the screen.
   */
  public void updateLifes() {
    if (lastLives != lives) {
      switch (lives) {
        case 3:
          changeImage(textureH3);
          break;
        case 2:
          changeImage(textureH2);
          break;
        case 1:
          changeImage(textureH1);
          break;
        case 0:
          changeImage(textureH0);
          Application.getBoard().changeScreen(new GameOverScreen());
          TimeSystem.stop();

          break;
        default:
          break;
      }
      lastLives = lives;
    }
  }

  /**
   * Method that resets the lives if the player gets hit.
   * Resets lastLives to properly redraw with 3 lives.
   */
  public void resetLives() {
    lives = 3;
    lastLives = 4;
  }
}
