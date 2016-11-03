package net.liquidpineapple.pang.screens;

import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Class that represents the winscreen.
 *
 * @author Jaap-Jan
 * @date 21-9-2016.
 */

public class WinScreen extends EndGameScreen {

  /**
   * Constructor for the winscreen.
   */
  public WinScreen() {
    super();
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/winscreen.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
