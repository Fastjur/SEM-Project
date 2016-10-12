package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.buttons.MultiPlayerButton;
import net.liquidpineapple.pang.objects.buttons.QuitButton;
import net.liquidpineapple.pang.objects.buttons.SinglePlayerButton;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class that represents the Main Menu.
 *
 * @author Govert de Gans
 * @date 2016-09-08
 */
public class MainMenuScreen extends Screen {
  /**
   * Constructor of the Main menu.
   */
  public MainMenuScreen() {
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/titlebg.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    objectList.add(new SinglePlayerButton(200, 220));
    objectList.add(new MultiPlayerButton(200, 325));
    objectList.add(new QuitButton(200, 455));
  }
}
