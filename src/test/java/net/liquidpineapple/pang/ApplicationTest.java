package net.liquidpineapple.pang;

import static junit.framework.TestCase.assertFalse;

import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.screens.ControlsScreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class ApplicationTest {

  private Application app;

  /**
   * Sets up the environment for the tests.
   */
  @Before
  public void setUp() throws Exception {
    app = new Application();
    ScoreSystem.setScore(0);
    TimeSystem.resetTime(1);
    app.start();

  }

  @Test
  public void testScreenChangeToLevel() throws Throwable {
    Application.getBoard().changeScreen(new ControlsScreen());
  }

  @Test
  public void testNotInCheatMode() throws Throwable {
    assertFalse("Hey there, I know our game is hard. But let's not push any code with cheat "
        + "mode on! Set the boolean 'cheatMode' in application to false!", Application.cheatMode);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }
}
