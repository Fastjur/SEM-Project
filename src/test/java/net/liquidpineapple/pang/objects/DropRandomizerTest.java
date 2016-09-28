package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropRandomizerTest {

  private static Application app;
  private static final String CONFIG_PROPERTIES = "/config.properties";

  /**
   * Setup test.
   * @throws Throwable - trowable
   */
  @Before
  public void setUp() throws Throwable {
    app = new Application(CONFIG_PROPERTIES);
    app.start();
    DropRandomizer.getInstance();
  }

  @Test
  public void testRollRandomDrop() {
    DropRandomizer.getInstance().rollRandomdrop(0, 0);
    //This is a randomized process,
    // it doest have any guaranteed result and you can't input anything to ensure it either... :/
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
