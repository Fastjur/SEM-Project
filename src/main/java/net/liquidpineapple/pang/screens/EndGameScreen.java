package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import lombok.Getter;

/**
 * Class that represents the end of game screen.
 *
 * @author Jaap-Jan
 * @date 3-11-2016.
 */
public class EndGameScreen extends Screen {
  @Getter
  private static ArrayList<NumberToken> places;

  /**
   * Constructor for the EndGameScreen.
   */
  public EndGameScreen() {
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
   * Method that updates the end of game screen.
   */
  @Override
  public void doUpdate() {
    super.doUpdate();
    if (InputHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
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
