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
public class StickyHookDropTest {

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
  }

  @Test
  public void testHookSetNormal3() {
    Drop Hook = new StickyHookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 3);
    Player testplayer = new Player(0,0,new Player1());
    Hook.playerCollision(testplayer);
    assertEquals(testplayer.getHookRemoveDelay(), 3);
  }

  @Test
  public void testHookSetNormal2() {
    Drop Hook = new StickyHookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 2);
    Player testplayer = new Player(0,0,new Player1());
    Hook.playerCollision(testplayer);
    assertEquals(testplayer.getHookRemoveDelay(), 2);
  }

  @Test
  public void testHookSetNegative() {
    Drop Hook = new StickyHookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, -3);
    Player testplayer = new Player(0,0,new Player1());
    Hook.playerCollision(testplayer);
    assertEquals(testplayer.getHookRemoveDelay(), 0);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
