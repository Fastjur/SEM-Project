package net.liquidpineapple.pang.screens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.ScoreSystem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tim on 23-9-2016.
 */
public class GameOverScreenTest {

  private Application app;
  private static final String PROPERTIES_LOCATION = "/config.properties";

  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

  @Test
  public void gameOverScreen() throws Exception {
    GameOverScreen gameOverScreen = new GameOverScreen();
    Application.getBoard().changeScreen(gameOverScreen);
    assertFalse(gameOverScreen.objectList.isEmpty());
  }

  @Test
  public void testDisplayScore() throws Exception {
    GameOverScreen gameOverScreen = new GameOverScreen();
    Application.getBoard().changeScreen(gameOverScreen);
    ScoreSystem.setScore(10);
    gameOverScreen.displayscore();

    assertEquals(0, GameOverScreen.getPlaces().get(0).getCurrentnumber());
    assertEquals(1, GameOverScreen.getPlaces().get(1).getCurrentnumber());
  }


}
