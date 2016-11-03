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

  private Drop drop1Live = new HeartDrop(1, 0);
  private Player testplayer = new Player(0,0,new Player1());

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
  }

  @Test
  public void testlifegain() {
    int oldlives = LifeSystem.getLives();
    LifeSystem.loseLife();
    drop1Live.playerCollision(testplayer);
    assertEquals(oldlives, LifeSystem.getLives());
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
