package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ControlsScreenTest {

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
  public void ControlsScreen() throws Exception {
    ControlsScreen cs = new ControlsScreen();
    Application.getBoard().changeScreen(cs);
    assertTrue(cs.objectList.isEmpty());
  }
}
