package net.liquidpineapple.pang.objects.powerups;

import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import net.liquidpineapple.pang.objects.playerschemes.Player2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by Erik on 22-9-2016.
 */
public class FreezeTimeDropTest {

  private static final String PROPERTIES_LOCATION = "/config.properties";

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
  }

  @Test
  public void testHookSetNormal3() {
    Drop hook = new FreezeTimeDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 3);
    Player testplayer = new Player(0,0,new Player1());
    Player testplayer2 = new Player(0,0,new Player2());
    hook.playerCollision(testplayer);
    hook.playerCollision(testplayer2);
    assertEquals(TimeSystem.getFrozen(), 3);
  }

  @Test
  public void testHookSetNormal2() {
    Drop hook = new FreezeTimeDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 2);
    Player testplayer = new Player(0,0,new Player1());
    hook.playerCollision(testplayer);
    assertEquals(TimeSystem.getFrozen(), 2);
  }

  @Test
  public void testHookSetNegative() {
    Drop hook = new FreezeTimeDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, -3);
    Player testplayer = new Player(0,0,new Player1());
    hook.playerCollision(testplayer);
    assertEquals(TimeSystem.getFrozen(), 0);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
