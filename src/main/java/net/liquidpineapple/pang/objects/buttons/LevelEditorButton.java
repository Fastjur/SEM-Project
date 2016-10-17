package net.liquidpineapple.pang.objects.buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.screens.LevelEditor;

/**
 * Class that represents the object for the Quitbutton.
 *
 * @author Jaap-Jan
 * @date 9-9-2016.
 */

public class LevelEditorButton extends Button {
  /**
   * Constructor of the Quitbutton.
   *
   * @param startX - The X location where the button should be on the screen.
   * @param startY - The Y location where the button should be on the screen.
   */
  public LevelEditorButton(double startX, double startY) {
    super("/images/leveleditor.png", startX, startY);
  }

  /**
   * Method that exits the game when the button is clicked.
   */
  @Override
  public void onClick() {
    Logger.info("Change screen to LevelEditor");
    Application.getBoard().changeScreen(new LevelEditor());
  }
}
