package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.buttons.EasyButton;
import net.liquidpineapple.pang.objects.buttons.HardButton;
import net.liquidpineapple.pang.objects.buttons.MediumButton;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by Jaap-Jan on 17-10-2016.
 */
public class DifficultyScreen extends Screen {
  public DifficultyScreen() {
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/titlebg.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    objectList.add(new EasyButton(200, 220));
    objectList.add(new MediumButton(200, 300));
    objectList.add(new HardButton(200, 380));
  }
}
