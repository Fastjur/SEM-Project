package net.liquidpineapple.pang.screens;

import lombok.Getter;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.GameObject;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class that represents a level.
 *
 * @author Govert de Gans
 * @date 2016-09-07
 */
public class LevelScreen extends Screen {

  @Getter
  private LinkedList<GameObject> hudObjectList;


  public LevelScreen() {
    super();
    hudObjectList = new LinkedList<GameObject>();
  }

  @Override
  /**
   * Update method for the level class.
   * Checks if the level is completed and if so, it loads a new level.
   * Updates all components in level when the level is not ended.
   */
  public void doUpdate() {
    if (noBallsLeft()) {
        ScoreSystem.addScore(TimeSystem.getTime());
        nextLevel();
    } else {
      new ArrayList<>(objectList).forEach(GameObject::doUpdate);
    }
  }

  /**
   * Checks if there are any balls left in the level.
   * @return true if there is at least one ball in the level and false otherwise.
   */
  private boolean noBallsLeft() {
    for (GameObject object : objectList) {
      if (object instanceof Ball) {
        return false;
      }
    }
    return true;
  }

  /**
   * Attempst to load the new level (WIP).
   */
  private void nextLevel() {
    Board currentBoard = Application.getBoard();
    Screen newScreen;

    if (currentBoard.getLevels().hasNext()) {
      newScreen = (Screen) currentBoard.getLevels().next();
    } else {
      //set behaviour when all levels have been completed here.
      newScreen = new WinScreen();
    }
    currentBoard.changeScreen(newScreen);
  }

  @Override
  public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
    super.doDrawing(graphics2D, imageObserver);
    for (GameObject hudObject : hudObjectList) {
      hudObject.doDrawing(graphics2D, imageObserver);
    }
  }
}
