package net.liquidpineapple.pang;

import net.liquidpineapple.pang.screens.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertFalse;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class ApplicationTest {

  private Application app;
  private static final String PROPERTIES_LOCATION = "/config.properties";

  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
  }

  @Test(expected = FileNotFoundException.class)
  public void testWrongProperties() throws Throwable {
    Application app = new Application("test");
    app.start();
  }

  @Test
  public void testScreenChangeToLevel() throws Throwable {
    Application.getBoard().changeScreen(Level.createFromXML("src/main/resources/levels/level9000.xml"));
  }

  @Test
  public void testNotInCheatMode() throws Throwable {
    assertFalse("Hey there, I know our game is hard. But let's not push any code with cheat " +
        "mode on! Set the boolean 'cheatMode' in application to false!", Application.cheatMode);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }
}
