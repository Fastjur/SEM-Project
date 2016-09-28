package net.liquidpineapple.pang.objects.buttons;

import net.liquidpineapple.pang.logger.Logger;

/**
 * Class that represents the object for the Quitbutton.
 *
 * @author Jaap-Jan
 * @date 9-9-2016.
 */
public class QuitButton extends Button {
  /**
   * Constructor of the Quitbutton.
   *
   * @param startX - The X location where the button should be on the screen.
   * @param startY - The Y location where the button should be on the screen.
   */
  public QuitButton(double startX, double startY) {
    super("/images/quit.png", startX, startY);
  }

  /**
   * Method that exits the game when the button is clicked.
   */
  @Override
  public void onClick() {
    Logger.info("Close");
    System.exit(0);
  }
}
