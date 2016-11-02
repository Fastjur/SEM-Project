package net.liquidpineapple.pang.Buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.Difficulty;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.objects.buttons.*;
import net.liquidpineapple.pang.screens.ControlsScreen;
import net.liquidpineapple.pang.screens.DifficultyScreen;
import net.liquidpineapple.pang.screens.LevelEditor;
import net.liquidpineapple.pang.screens.WinScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ConcreteButtonsTests {


  private Application app;
  private Board board;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
    board = Application.getBoard();
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

  @Test
  public void testEasyButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new EasyButton(0,0);
    board.setDifficulty(Difficulty.UNDEFINED);
    TestingButton.onClick();
    assertEquals(ControlsScreen.class, board.getCurrentScreen().getClass());
    assertEquals(Difficulty.EASY, board.getDifficulty());
  }

  @Test
  public void testMediumButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new MediumButton(0,0);
    board.setDifficulty(Difficulty.UNDEFINED);
    TestingButton.onClick();
    assertEquals(ControlsScreen.class, board.getCurrentScreen().getClass());
    assertEquals(Difficulty.MEDIUM, board.getDifficulty());
  }

  @Test
  public void testHardButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new HardButton(0,0);
    board.setDifficulty(Difficulty.UNDEFINED);
    TestingButton.onClick();
    assertEquals(ControlsScreen.class, board.getCurrentScreen().getClass());
    assertEquals(Difficulty.HARD, board.getDifficulty());
  }

  @Test
  public void testInsaneButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new InsaneButton(0,0);
    board.setDifficulty(Difficulty.UNDEFINED);
    TestingButton.onClick();
    assertEquals(ControlsScreen.class, board.getCurrentScreen().getClass());
    assertEquals(Difficulty.INSANE, board.getDifficulty());
  }

  @Test
  public void testLevelEditorButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new LevelEditorButton(0,0);
    TestingButton.onClick();
    assertEquals(LevelEditor.class, board.getCurrentScreen().getClass());
  }

  @Test
  public void testMultiPlayerButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new MultiPlayerButton(0,0);
    app.multiplayer = false;
    TestingButton.onClick();
    assertEquals(DifficultyScreen.class, board.getCurrentScreen().getClass());
    assertTrue(app.multiplayer);
  }

  @Test
  public void testSinglePlayerButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new SinglePlayerButton(0,0);
    app.multiplayer = true;
    TestingButton.onClick();
    assertEquals(DifficultyScreen.class, board.getCurrentScreen().getClass());
    assertFalse(app.multiplayer);
  }
}
