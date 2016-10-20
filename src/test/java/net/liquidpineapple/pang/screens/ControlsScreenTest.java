package net.liquidpineapple.pang.screens;

import static org.junit.Assert.assertTrue;

import net.liquidpineapple.pang.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControlsScreenTest {

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

  @Test
  public void controlsScreen() throws Exception {
    ControlsScreen cs = new ControlsScreen();
    Application.getBoard().changeScreen(cs);
    assertTrue(cs.objectList.isEmpty());
  }
}
