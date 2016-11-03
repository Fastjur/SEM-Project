package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.Difficulty;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.GameObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LevelScreenTest {

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
    EasyLevels levels = new EasyLevels();
    Application.getBoard().setLevels(levels.createIterator());
    Application.getBoard().setDifficulty(Difficulty.EASY);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

  @Test
  public void levelScreen() throws Exception {
    Application.lifeSystem.resetLives();
    Application.getScoreSystem().resetScore();
    Screen newScreen = (Screen) Application.getBoard().getLevels().next();
    Application.getBoard().changeScreen(newScreen);
    assertFalse(newScreen.objectList.isEmpty());
  }

  @Test
  public void noBallsNextLevel() {
    Application.lifeSystem.resetLives();
    Application.getScoreSystem().resetScore();
    LevelScreen newScreen = (LevelScreen) Application.getBoard().getLevels().next();
    Application.getBoard().changeScreen(newScreen);
    assertTrue(Application.getBoard().getCurrentScreen().equals(newScreen));
    for (int i = 0; i < newScreen.objectList.size(); i++) {
      GameObject object = newScreen.objectList.get(i);
      if (object instanceof Ball) {
          ((Ball) object).destroyball();
        i=-1;
      }
    }
    assertTrue(newScreen.noBallsLeft());
    newScreen.doUpdate();
    assertFalse(Application.getBoard().getCurrentScreen().equals(newScreen));
  }

}
