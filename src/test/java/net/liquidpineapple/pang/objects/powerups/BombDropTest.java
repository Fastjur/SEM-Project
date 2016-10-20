package net.liquidpineapple.pang.objects.powerups;

import static org.junit.Assert.assertFalse;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.BallMovement;
import net.liquidpineapple.pang.objects.Drop;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 22-9-2016.
 */
public class BombDropTest {

  private Application app;

  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
  }

  @Test
  public void testBombDrop() {
    Drop hook = new BombDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1);
    Ball smallerBall = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 1);
    Application.getBoard().getCurrentScreen().objectList.add(smallerBall);
    Player testplayer = new Player(0,0,new Player1());
    hook.playerCollision(testplayer);
    assertFalse(Application.getBoard().getCurrentScreen().objectList.contains(smallerBall));
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

}
