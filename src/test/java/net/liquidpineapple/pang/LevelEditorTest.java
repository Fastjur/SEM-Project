package net.liquidpineapple.pang;

import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.BallMovement;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import net.liquidpineapple.pang.screens.LevelEditor;
import net.liquidpineapple.pang.screens.LevelScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LevelEditorTest {

  private Application app;
  private static final String PROPERTIES_LOCATION = "/config.properties";
  private Robot robot;
  private int windowX;
  private int windowY;
  private Point oldMousePos;
  //Used to return the mouse to the old location, to not irrate developers with a mouse thats gone.


  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    ScoreSystem.setScore(0);
    app.start();
    try {
      robot = new Robot();
    } catch(AWTException e) {
      e.printStackTrace();
    };
    windowX = app.getX();
    windowY = app.getY();
    oldMousePos = MouseInfo.getPointerInfo().getLocation();

  }

  @Test
  public void loadLevelEditScreen() throws Exception {
    LevelEditor levelEditScreen = new LevelEditor();
    Application.getBoard().changeScreen(levelEditScreen);
    assertTrue(levelEditScreen.addedObjects.isEmpty());
  }

  @Test
  public void placeSize1Ball() throws Exception {
    LevelEditor levelEditScreen = new LevelEditor();
    Application.getBoard().changeScreen(levelEditScreen);
    robot.mouseMove(windowX + 100, windowY + 100);
    robot.keyPress(KeyEvent.VK_1);
    TimeUnit.MILLISECONDS.sleep(200);

    assertEquals(1, ((Ball)levelEditScreen.addedObjects.get(0)).getBallSize());
  }

  @Test
  public void placeSize2Ball() throws Exception {
    LevelEditor levelEditScreen = new LevelEditor();
    Application.getBoard().changeScreen(levelEditScreen);
    robot.mouseMove(windowX + 100, windowY + 100);
    robot.keyPress(KeyEvent.VK_2);
    TimeUnit.MILLISECONDS.sleep(200);

    assertEquals(2, ((Ball)levelEditScreen.addedObjects.get(0)).getBallSize());
  }

  @Test
  public void placeSize3Ball() throws Exception {
    LevelEditor levelEditScreen = new LevelEditor();
    Application.getBoard().changeScreen(levelEditScreen);
    robot.mouseMove(windowX + 100, windowY + 100);
    robot.keyPress(KeyEvent.VK_3);
    TimeUnit.MILLISECONDS.sleep(200);

    assertEquals(3, ((Ball)levelEditScreen.addedObjects.get(0)).getBallSize());
  }

  @Test
  public void placeSize4Ball() throws Exception {
    LevelEditor levelEditScreen = new LevelEditor();
    Application.getBoard().changeScreen(levelEditScreen);
    robot.mouseMove(windowX + 100, windowY + 100);
    robot.keyPress(KeyEvent.VK_4);
    TimeUnit.MILLISECONDS.sleep(200);

    assertEquals(4, ((Ball)levelEditScreen.addedObjects.get(0)).getBallSize());
  }

  @Test
  public void placePlayer() throws Exception {
    LevelEditor levelEditScreen = new LevelEditor();
    Application.getBoard().changeScreen(levelEditScreen);
    robot.mouseMove(windowX + 100, windowY + 100);
    robot.keyPress(KeyEvent.VK_5);
    TimeUnit.MILLISECONDS.sleep(200);

    assertTrue(levelEditScreen.addedObjects.get(0).getClass().equals(
        new Player(0,0,new Player1()).getClass()
    ));
  }

  @After
  public void tearDown() throws Exception {
    robot.mouseMove(oldMousePos.x,oldMousePos.y);
    app.close();
  }
}
