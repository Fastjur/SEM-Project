package net.liquidpineapple.pang.Buttons;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.Difficulty;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.objects.buttons.Button;
import net.liquidpineapple.pang.objects.buttons.MediumButton;
import net.liquidpineapple.pang.screens.ControlsScreen;
import net.liquidpineapple.pang.screens.WinScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 3-11-2016.
 */
public class MediumButtonTest {

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
  public void testMediumButton() throws Exception {
    board.changeScreen(new WinScreen());
    Button TestingButton = new MediumButton(0,0);
    board.setDifficulty(Difficulty.UNDEFINED);
    TestingButton.onClick();
    assertEquals(ControlsScreen.class, board.getCurrentScreen().getClass());
    assertEquals(Difficulty.MEDIUM, board.getDifficulty());
  }

}
