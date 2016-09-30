package net.liquidpineapple.pang.objects.powerups;

import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 22-9-2016.
 */
public class HeartDropTest {

  private static final String PROPERTIES_LOCATION = "/config.properties";
  private Drop drop1Live = new HeartDrop("/sprites/drops/heart.png", 0, 0, 0, 1, 0, 1);
  private Drop droplose2Live = new HeartDrop("/sprites/drops/heart.png", 0, 0, 0, 1, 0, -2);
  private Player testplayer = new Player(0,0,new Player1());

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
  }

  @Test
  public void testlifegain() {
    int oldlives = LifeSystem.getLives();
    droplose2Live.playerCollision(testplayer);
    assertEquals(oldlives - 2, LifeSystem.getLives());
    drop1Live.playerCollision(testplayer);
    assertEquals(oldlives - 1, LifeSystem.getLives());
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
