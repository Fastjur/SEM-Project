package net.liquidpineapple.pang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.BallMovement;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.screens.ControlsScreen;
import net.liquidpineapple.pang.screens.LevelEditor;
import net.liquidpineapple.pang.screens.Screen;
import net.liquidpineapple.pang.screens.UserCreatedLevels;
import net.liquidpineapple.pang.screens.WinScreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.awt.Component;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


@RunWith(PowerMockRunner.class)
@PrepareForTest({InputHandler.class, Application.class, Board.class})
public class LevelEditorTest {
  private LevelEditor levelEditor;
  private PointerInfo pointerInfo;
  private InputHandler inputHandler;
  private Board board;
  private Robot robot;

  /**
   * Sets up all dependencies for the tests.
   * @throws Exception when something goes wrong in mocking
   */
  @Before
  public void setUp() throws Exception {
    levelEditor = PowerMockito.spy(new LevelEditor());
    inputHandler = new InputHandler();
    board = Mockito.mock(Board.class);
    pointerInfo = Mockito.mock(PointerInfo.class);
    Mockito.when(pointerInfo.getLocation()).thenReturn(new Point(100, 100));
    Mockito.when(board.getLocationOnScreen()).thenReturn(new Point(0, 0));
    PowerMockito.mockStatic(Application.class);
    Mockito.when(Application.getBoard()).thenReturn(board);
    robot = new Robot();
  }

  @Test
  public void testPlaceBallSize1() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_1)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 1);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 1);
  }

  @Test
  public void testPlaceBallSize2() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_2)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 2);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 2);
  }

  @Test
  public void testPlaceBallSize3() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_3)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 3);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 3);
  }

  @Test
  public void testPlaceBallSize4() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_4)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 4);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 4);
  }

  @Test
  public void testAddBall() throws Exception {
    Whitebox.invokeMethod(levelEditor, "addBall", 1);
    assertEquals(levelEditor.addedObjects.size(), 1);
    assertEquals(((Ball) levelEditor.addedObjects.get(0)).getBallSize(), 1);
  }

  @Test
  public void testPlacePlayer() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_5)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addPlayer");
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addPlayer");
  }

  @Test
  public void testAddPlayer() throws Exception {
    Whitebox.invokeMethod(levelEditor, "addPlayer");
    assertEquals(1, levelEditor.addedObjects.size());
    assertTrue(levelEditor.addedObjects.get(0) instanceof Player);
  }


  @Test
  public void testHandleKeyPresses1() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_1));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addBall", 1);
  }

  @Test
  public void testHandleKeyPresses2() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_2));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addBall", 2);
  }

  @Test
  public void testHandleKeyPresses3() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_3));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addBall", 3);
  }

  @Test
  public void testHandleKeyPresses4() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_4));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addBall", 4);
  }

  @Test
  public void testHandleKeyPresses5() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_5));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addPlayer");
  }

  @Test
  public void testHandleKeyPressesS() throws Exception {
    levelEditor = new LevelEditor();
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_S));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("saveGame");
  }

  @Test
  public void testHandleKeyPressesP() throws Exception {
    ArrayList<Screen> levels = new ArrayList<>();
    levels.add(new ControlsScreen());
    levels.add(new WinScreen());

    Mockito.when(board.getLevels()).thenReturn(levels.iterator());

    levelEditor = new LevelEditor();
    Application.lifeSystem = LifeSystem.getInstance();
    UserCreatedLevels level = Mockito.mock(UserCreatedLevels.class);

    Mockito.when(level.createIterator()).thenReturn(levels.iterator());
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_P));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("playGame");
    assertNotNull(board.getLevels());
  }

  @Test
  public void testSetCurrentMousePos() throws Exception {
    robot.mouseMove(100, 100);
    Whitebox.invokeMethod(levelEditor, "setCurrentMousePos");
    assertEquals(levelEditor.getCurrentMouseX(), 100);
    assertEquals(levelEditor.getCurrentMouseY(), 100);
    robot.mouseMove(200,600);
    Whitebox.invokeMethod(levelEditor, "setCurrentMousePos");
    assertEquals(levelEditor.getCurrentMouseX(), 200);
    assertEquals(levelEditor.getCurrentMouseY(), 600);
  }

  @Test
  public void testSelectObject() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_4));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");

    robot.mouseMove(-50, -50);
    Whitebox.invokeMethod(levelEditor, "setCurrentMousePos");

    inputHandler.mousePressed(new MouseEvent(new Component() {
    }, 0, 0, 0, 1, 2, 3, false, 1));
    GameObject result = Whitebox.invokeMethod(levelEditor, "selectObject");
    Ball ball = new Ball(-79, -79, BallMovement.LEFT_MOVEMENT, 4);
    assertEquals(result, ball);

  }

  @Test
  public void testDelete() throws Exception {
    inputHandler.keyPressed(new KeyEvent(new Component() {
    }, 0, 0, 0, KeyEvent.VK_4));
    Whitebox.invokeMethod(levelEditor, "handleKeyPresses");
    assertEquals(levelEditor.addedObjects.size(), 1);
    Ball ball = new Ball(-79, -79, BallMovement.LEFT_MOVEMENT, 4);
    Whitebox.invokeMethod(levelEditor, "deleteObject", ball);
    assertEquals(levelEditor.addedObjects.size(), 0);
  }

  @After
  public void tearDown() {
    inputHandler.clearKeys();
    levelEditor.addedObjects.clear();
  }
}
