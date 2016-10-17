package net.liquidpineapple.pang.objects.buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.screens.ControlsScreen;
import net.liquidpineapple.pang.screens.EasyLevels;

/**
 * Class that represents the object for the SinglePlayer button.
 *
 * @author Jaap-Jan
 * @date 17-10-2016
 */
public class EasyButton extends Button {
  /**
   * Constructor of a button.
   *
   * @param startX          - The X position where the image should be on the screen.
   * @param startY          - The Y position where the image should be on the screen.
   */
  public EasyButton(double startX, double startY) {
    super("/images/singleplayer.png", startX, startY);
  }

  /**
   * Method that starts a SinglePlayer game when the button is clicked.
   */
  @Override
  public void onClick() {
    Logger.info("I change the screen now");
    EasyLevels levels = new EasyLevels();
    Application.getBoard().setLevels(levels.createIterator());
    Application.getBoard().changeScreen(new ControlsScreen());
  }
}
