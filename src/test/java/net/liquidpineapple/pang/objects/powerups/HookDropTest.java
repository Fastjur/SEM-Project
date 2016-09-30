package net.liquidpineapple.pang.objects.powerups;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 22-9-2016.
 */
public class HookDropTest {

  private static final String PROPERTIES_LOCATION = "/config.properties";

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
  }

  @Test
  public void testHookSetNormal3() {
    Drop Hook = new HookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 3);
    Player testplayer = new Player(0,0,new Player1());
    Hook.playerCollision(testplayer);
    assertEquals(testplayer.getMaximumHooks(), 3);
  }

  @Test
  public void testHookSetNormal2() {
    Drop Hook = new HookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 2);
    Player testplayer = new Player(0,0,new Player1());
    Hook.playerCollision(testplayer);
    assertEquals(testplayer.getMaximumHooks(), 2);
  }

  @Test
  public void testHookSetNegative() {
    Drop Hook = new HookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, -3);
    Player testplayer = new Player(0,0,new Player1());
    Hook.playerCollision(testplayer);
    assertEquals(testplayer.getMaximumHooks(), 1);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}