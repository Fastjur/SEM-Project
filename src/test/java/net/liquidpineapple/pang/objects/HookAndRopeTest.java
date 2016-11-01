package net.liquidpineapple.pang.objects;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.objects.playerschemes.Player1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tim on 13-9-2016.
 * Class to test HookAndRope class.
 */
public class HookAndRopeTest {

  private HookAndRope hookAndRope;
  private Player player;
  private static final int XCOORD = 400;
  private static final int MAXY = 300;
  private static final double DELTA = 0.0;

  private Application app;

  /**
   * Setup new HookAndRope.
   */
  @Before
  public void setUp() throws Exception {
    app = new Application();
    app.start();
    player = new Player(0, 0, new Player1());
    hookAndRope = new HookAndRope(XCOORD, MAXY, player, 0);
  }

  /**
   * Tests the constructor of the HookAndRope class.
   */
  @Test
  public void constructorTest() throws Exception {
    assertEquals(hookAndRope.getXpos(), XCOORD, DELTA);
    assertEquals(hookAndRope.getYpos(), 600, DELTA);
  }

  /**
   * Tests the normal behaviour of move method in the HookAndRope class.
   */
  @Test
  public void moveTest() throws Exception {
    assertEquals(600, hookAndRope.getYpos(), DELTA);
    hookAndRope.move();
    assertEquals(597, hookAndRope.getYpos(), DELTA);
  }

  /**
   * Tests the behaviour of the move method when the rope collides with the maximum height.
   */
  @Test
  public void hitTopMoveTest() throws Exception {
    HookAndRope rope = new HookAndRope(500, 584, player, 0);
    assertEquals(600, rope.getYpos(), DELTA);
    rope.move();
    assertEquals(597, rope.getYpos(), DELTA);
    rope.move();
    assertFalse(Application.getBoard().getCurrentScreen().objectList.contains(rope));
  }

  /**
   * Tests the behaviour of the move method when {@code yPos <= MAXY}.
   */
  @Test
  public void hitTopTest() throws Exception {
    HookAndRope rope = new HookAndRope(500, 597, player, 0);
    assertEquals(600, rope.getYpos(), DELTA);
    rope.move();
    assertEquals(597, rope.getYpos(), DELTA);
    rope.doUpdate();
    assertFalse(Application.getBoard().getCurrentScreen().objectList.contains(rope));
  }

  @Test
  public void testPlayerGetter() {
    assertEquals(player, hookAndRope.getPlayer());
  }

  @After
  public void tearDown() {
    hookAndRope = null;
    app.close();
  }
}
