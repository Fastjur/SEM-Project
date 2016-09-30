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
public class ShieldDropTest {

  private static final String PROPERTIES_LOCATION = "/config.properties";

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
  }

  @Test
  public void testShieldSetNormal3() {
    Drop shield = new ShieldDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 3);
    Player testplayer = new Player(0,0,new Player1());
    shield.playerCollision(testplayer);
    assertEquals(testplayer.getShield(), 3);
  }

  @Test
  public void testShieldSetNormal2() {
    Drop shield = new ShieldDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 2);
    Player testplayer = new Player(0,0,new Player1());
    shield.playerCollision(testplayer);
    assertEquals(testplayer.getShield(), 2);
  }

  @Test
  public void testShieldSetNormal2and3() {
    Drop shield = new ShieldDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 2);
    Drop shield2 = new ShieldDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 3);

    Player testplayer = new Player(0,0,new Player1());
    shield2.playerCollision(testplayer);
    shield.playerCollision(testplayer);
    assertEquals(testplayer.getShield(), 5);
  }

  @Test
  public void testHookSetNegative() {
    Drop shield = new ShieldDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, -3);
    Player testplayer = new Player(0,0,new Player1());
    shield.playerCollision(testplayer);
    assertEquals(testplayer.getShield(), -3);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
