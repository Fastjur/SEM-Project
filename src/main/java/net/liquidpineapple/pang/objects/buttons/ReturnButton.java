package net.liquidpineapple.pang.objects.buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.screens.MainMenuScreen;

import java.awt.event.KeyEvent;

/**
 * Class that represents the object for the return button.
 *
 * @author Jaap-Jan
 * @date 24-10-2016
 */
public class ReturnButton extends Button {

  /**
   * Constructor of a button.
   *
   * @param startX - The X position where the image should be on the screen.
   * @param startY - The Y position where the image should be on the screen.
   */
  public ReturnButton(double startX, double startY) {
    super("/images/return.png", startX, startY);
  }

  @Override
  public void doUpdate() {
    super.doUpdate();

    if (InputHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
      Application.getBoard().changeScreen(new MainMenuScreen());
    }
  }

  /**
   * Method that returns to main menu when the button is clicked.
   */
  @Override
  public void onClick() {
    Logger.info("I change the screen now");
    Application.getBoard().changeScreen(new MainMenuScreen());
  }
}
