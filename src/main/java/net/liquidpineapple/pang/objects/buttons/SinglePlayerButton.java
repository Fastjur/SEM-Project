package net.liquidpineapple.pang.objects.buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.screens.DifficultyScreen;

/**
 * Class that represents the object for the SinglePlayer button.
 *
 * @author Govert de Gans
 * @date 2016-09-08
 */

public class SinglePlayerButton extends Button {
  /**
   * Constructor of the SinglePlayer button.
   *
   * @param startX - The X location where the button should be on the screen.
   * @param startY - The Y location where the button should be on the screen.
   */
  public SinglePlayerButton(double startX, double startY) {
    super("/images/singleplayer.png", startX, startY);
  }

  /**
   * Method that starts a SinglePlayer game when the button is clicked.
   */
  @Override
  public void onClick() {
    Logger.info("I change the screen now");
    Application.multiplayer = false;
    Application.getBoard().changeScreen(new DifficultyScreen());
  }
}
