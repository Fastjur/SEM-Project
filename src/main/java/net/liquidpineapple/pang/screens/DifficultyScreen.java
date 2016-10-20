package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.buttons.EasyButton;
import net.liquidpineapple.pang.objects.buttons.HardButton;
import net.liquidpineapple.pang.objects.buttons.InsaneButton;
import net.liquidpineapple.pang.objects.buttons.MediumButton;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class that represents the screen for the difficulty buttons.
 *
 * @author Jaap-Jan
 * @date 17-10-2016
 */
public class DifficultyScreen extends Screen {
  /**
   * Screen with the difficulty buttons.
   */
  public DifficultyScreen() {
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/titlebg.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    objectList.add(new EasyButton(200, 230));
    objectList.add(new MediumButton(200, 310));
    objectList.add(new HardButton(200, 390));
    objectList.add(new InsaneButton(200, 470));
  }
}
