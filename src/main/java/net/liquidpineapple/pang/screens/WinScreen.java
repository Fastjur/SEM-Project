package net.liquidpineapple.pang.screens;

import lombok.Getter;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


/**
 * Class that represents the winscreen.
 *
 * @author Jaap-Jan
 * @date 21-9-2016.
 */

public class WinScreen extends Screen {
  @Getter
  private static ArrayList<NumberToken> places;

  /**
   * Constructor for the winscreen.
   */
  public WinScreen() {
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/winscreen.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    //These are from right to left.
    places = new ArrayList<>(9);
    int numberPosX = 524;
    int numberPosY = 300;
    int numberOfTokens = 9;
    for (int i = 0; i < numberOfTokens; i++) {
      NumberToken token = new NumberToken(numberPosX, numberPosY);
      places.add(token);
      objectList.add(token);
      numberPosX -= 32;
    }
    displayscore();
  }

  /**
   * Method that updates the winscreen.
   */
  @Override
  public void doUpdate() {
    super.doUpdate();
    if (InputHandler.isAnyKeyPressed()) {
      Application.lifeSystem.resetLives();
      Application.getScoreSystem().resetScore();
      Application.getBoard().changeScreen(new MainMenuScreen());
    }
  }

  /**
   * Method that makes the score display on the screen.
   */
  public void displayscore() {
    int calcscore = ScoreSystem.getScore();
    int pos = 0;

    while (calcscore > 0) {
      places.get(pos).setScoreToken(calcscore % 10);
      calcscore /= 10;
      pos++;
    }
    doUpdate();
  }

}
