package net.liquidpineapple.pang.Buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.objects.buttons.Button;
import net.liquidpineapple.pang.objects.buttons.MultiPlayerButton;
import net.liquidpineapple.pang.screens.DifficultyScreen;
import net.liquidpineapple.pang.screens.WinScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Erik on 3-11-2016.
 */
public class MultiplayerButtonTest {

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
  public void testMultiPlayerButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new MultiPlayerButton(0,0);
    app.multiplayer = false;
    TestingButton.onClick();
    assertEquals(DifficultyScreen.class, board.getCurrentScreen().getClass());
    assertTrue(app.multiplayer);
  }

}
