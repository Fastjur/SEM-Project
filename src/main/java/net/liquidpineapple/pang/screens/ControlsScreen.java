package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;

import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Class that represents the Controls screen.
 *
 * @author Jaap-Jan
 * @date 13-9-2016.
 */
public class ControlsScreen extends Screen {
  /**
   * Constructor for the ControlsScreen.
   */
  public ControlsScreen() {
    try {
      if (Application.multiplayer) {
        backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/controlsbg2p.png"));
      } else {
        backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/controlsbg.png"));
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Method that updates the controlsScreen.
   */
  @Override
  public void doUpdate() {
    super.doUpdate();
    if (InputHandler.isAnyKeyPressed()) {
      Application.lifeSystem.resetLives();
      Application.getScoreSystem().resetScore();
      Application.getBoard().changeScreen((Screen) Application.getBoard().getLevels().next());
    }
  }
}
