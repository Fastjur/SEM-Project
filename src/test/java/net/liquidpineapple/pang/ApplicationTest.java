package net.liquidpineapple.pang;

import static junit.framework.TestCase.assertFalse;

import net.liquidpineapple.pang.screens.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class ApplicationTest {

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
  }

  @Test
  public void testScreenChangeToLevel() throws Throwable {
    Application.getBoard().changeScreen(
        Level.createFromXml("src/main/resources/levels/level9000.xml"));
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
