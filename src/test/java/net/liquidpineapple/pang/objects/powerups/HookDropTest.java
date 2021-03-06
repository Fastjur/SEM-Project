package net.liquidpineapple.pang.objects.powerups;

import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 22-9-2016.
 */
public class HookDropTest {

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
  }

  @Test
  public void testHookSetNormal3() {
    Drop hook = new HookDrop(1, 1, 3);
    Player testplayer = new Player(0,0,new Player1());
    hook.playerCollision(testplayer);
    assertEquals(testplayer.getMaximumHooks(), 3);
  }

  @Test
  public void testHookSetNormal2() {
    Drop hook = new HookDrop(1, 1, 2);
    Player testplayer = new Player(0,0,new Player1());
    hook.playerCollision(testplayer);
    assertEquals(testplayer.getMaximumHooks(), 2);
  }

  @Test
  public void testHookSetNegative() {
    Drop hook = new HookDrop(1, 1, -3);
    Player testplayer = new Player(0,0,new Player1());
    hook.playerCollision(testplayer);
    assertEquals(testplayer.getMaximumHooks(), 1);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
