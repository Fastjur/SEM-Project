package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.logger.Logger;
import org.xml.sax.SAXException;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;



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
        backgroundImage = ImageIO.read(Level.class.getResource("/sprites/controlsbg2p.png"));
      } else {
        backgroundImage = ImageIO.read(Level.class.getResource("/sprites/controlsbg.png"));
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
      try {
        Application.lifeSystem.resetLives();
        Application.getScoreKeeper().resetScore();
        Application.getBoard().changeScreen(Level.createFromXml("/levels/level1.xml"));
      } catch (IOException | ParserConfigurationException | SAXException ex) {
        Logger.error("Could not load level", ex);
      }
    }
  }
}
