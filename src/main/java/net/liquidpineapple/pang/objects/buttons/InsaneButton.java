package net.liquidpineapple.pang.objects.buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.screens.ControlsScreen;
import net.liquidpineapple.pang.screens.EasyLevels;
import net.liquidpineapple.pang.screens.InsaneLevels;

/**
 * Class that represents the object for the insane button.
 *
 * @author Jaap-Jan
 * @date 17-10-2016
 */
public class InsaneButton extends Button {
  /**
   * Constructor of a button.
   *
   * @param startX          - The X position where the image should be on the screen.
   * @param startY          - The Y position where the image should be on the screen.
   */
  public InsaneButton(double startX, double startY) {
    super("/images/insane.png", startX, startY);
  }

  /**
   * Method that starts a insane game when the button is clicked.
   */
  @Override
  public void onClick() {
    Logger.info("I change the screen now");
    InsaneLevels levels = new InsaneLevels();
    Application.getBoard().setLevels(levels.createIterator());
    Application.getBoard().changeScreen(new ControlsScreen());
  }
}
