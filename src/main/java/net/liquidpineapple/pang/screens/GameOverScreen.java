package net.liquidpineapple.pang.screens;

import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * Class that represents the game over screen.
 *
 * @author Jaap-Jan
 * @date 21-9-2016.
 */
public class GameOverScreen extends EndGameScreen {

  /**
   * Constructor for the game over screen.
   */
  public GameOverScreen() {
    super();
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/gameover.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
