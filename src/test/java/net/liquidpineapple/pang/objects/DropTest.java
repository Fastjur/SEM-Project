package net.liquidpineapple.pang.objects;

import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;

import net.liquidpineapple.pang.objects.playerschemes.Player1;
import net.liquidpineapple.pang.objects.powerups.HeartDrop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropTest {

  private static final String PROPERTIES_LOCATION = "/config.properties";
  private Drop drop1Live = new HeartDrop("/sprites/drops/heart.png", 0, 0, 0, 1, 0, 1);
  private Drop droplose2Live = new HeartDrop("/sprites/drops/heart.png", 0, 0, 0, 1, 0, -2);
  private Player testplayer = new Player(0,0,new Player1());

  private static final double DELTA = 0.01;

  private float movement = 1;
  private Drop drop100P = new Drop("/sprites/drops/heart.png", 0, 0, 2 * movement, movement, 100);
  private Drop drop1000P = new Drop("/sprites/drops/heart.png", 0, 0, 0, 1, 1000);

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

  @Test
  public void testscore() {
    int oldscore = ScoreSystem.getScore();
    drop100P.playerCollision(testplayer);
    assertEquals(oldscore + 100, ScoreSystem.getScore());
    drop1000P.playerCollision(testplayer);
    assertEquals(oldscore + 1100, ScoreSystem.getScore());
  }

  @Test
  public void updatetest() {
    drop100P.doUpdate();
    assertEquals(2 * movement, drop100P.getXpos(), DELTA);
    assertEquals(movement, drop100P.getYpos(), DELTA);
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
