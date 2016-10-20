package net.liquidpineapple.pang.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import net.liquidpineapple.pang.objects.playerschemes.Player2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/12.
 */
public class PlayerTest {

  private Application app;

  private static final int startX = 15;
  private static final int startY = 20;

  private static final String defaultTexture = "/sprites/no-texture.png";
  private static final double DELTA = 0.01;

  private Player player;
  private Player player2;

  /**
   * Setup test.
   * @throws Throwable - throwable.
   */
  @Before
  public void setUp() throws Throwable {
    app = new Application();
    app.start();
    player = new Player(startX, startY, new Player1());
    player2 = new Player(startX, startY, new Player2());
  }

  @After
  public void tearDown() throws Exception {
    app.close();
  }

  @Test
  public void testGetBounds() throws Exception {
    Rectangle rectangle = new Rectangle(15, 20, 66, 92);
    assertEquals(rectangle, player.getBounds());
  }

  @Test
  public void testSetPos() throws Exception {
    player.setPos(5, 6);
    player2.setPos(5, 6);
    assertEquals(5, player.getXpos(), DELTA);
    assertEquals(6, player.getYpos(), DELTA);
    assertEquals(5, player2.getXpos(), DELTA);
    assertEquals(6, player2.getYpos(), DELTA);
    player.doUpdate();
  }

  @Test
  public void testSetImage() throws Exception {
    ImageIcon ii = new ImageIcon(defaultTexture);
    Image newImg = ii.getImage();
    player.setImage(newImg);
    player2.setImage(newImg);
    assertEquals(newImg, player.getImage());
    assertEquals(newImg, player2.getImage());
  }

  @Test
  public void testConstructor() throws Exception {
    assertEquals(player.getXpos(), startX, 0.0000001);
    assertEquals(player.getYpos(), startY, 0.0000001);
    assertEquals(player2.getXpos(), startX, 0.0000001);
    assertEquals(player2.getYpos(), startY, 0.0000001);

  }

  @Test
  public void testMoveLeft() throws Exception {
    player.setDx(-DELTA);
    player.move();
    System.out.println(startX - DELTA);
    assertEquals(player.getXpos(), startX - DELTA, 0.001);
    player2.setDx(-DELTA);
    player2.move();
    assertEquals(player2.getXpos(), startX - DELTA, 0.001);
  }

  @Test
  public void testMoveRight() throws Exception {
    player.setDx(DELTA);
    player.move();
    assertEquals(player.getXpos(), startX + DELTA, 0.001);
    player2.setDx(DELTA);
    player2.move();
    assertEquals(player2.getXpos(), startX + DELTA, 0.001);
  }

  @Test
  public void testMoveNoDirection() throws Exception {
    player.setDx(0);
    player.move();
    assertEquals(player.getXpos(), startX, 0.0000001);
    player2.setDx(0);
    player2.move();
    assertEquals(player2.getXpos(), startX, 0.0000001);
  }

  @Test
  public void testCollision() throws Exception {
    player.setXpos(100);
    player.setYpos(100);
    player2.setXpos(100);
    player2.setYpos(100);
    Ball ball = new Ball(100, 100, BallMovement.LEFT_MOVEMENT, 1);
    Application.getBoard().getCurrentScreen().objectList.add(ball);
    assertTrue(player.collisionPlayer());
    assertTrue(player2.collisionPlayer());
  }

  @Test
  public void testDoUpdate() throws Exception {
    player.setXpos(100);
    player.setYpos(100);

  }
}
