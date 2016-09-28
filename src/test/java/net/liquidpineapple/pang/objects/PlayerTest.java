package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.screens.Screen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/12.
 */
public class PlayerTest {

  private Application app;

  private static final int startX = 15;
  private static final int startY = 20;

  private static final String defaultTexture = "/sprites/no-texture.png";
  private final double DELTA = 0.01;
  private static String PROPERTIES_LOCATION = "/config.properties";

  private Player player;

  @Before
  public void setUp() throws Throwable {
    app = new Application(PROPERTIES_LOCATION);
    app.start();
    player = new Player(startX, startY);
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
    assertEquals(5, player.getXPos(), DELTA);
    assertEquals(6, player.getYPos(), DELTA);
  }

  @Test
  public void testSetImage() throws Exception {
    ImageIcon ii = new ImageIcon(defaultTexture);
    Image newImg = ii.getImage();
    player.setImage(newImg);
    assertEquals(newImg, player.getImage());
  }

  @Test
  public void testConstructor() throws Exception {
    assertEquals(player.getXPos(), startX, 0.0000001);
    assertEquals(player.getYPos(), startY, 0.0000001);

  }

  @Test
  public void testMoveLEFT() throws Exception {
    player.setDx(-DELTA);
    player.move();
    System.out.println(startX - DELTA);
    assertEquals(player.getXPos(), startX - DELTA, 0.001);
  }

  @Test
  public void testMoveRIGHT() throws Exception {
    player.setDx(DELTA);
    player.move();
    assertEquals(player.getXPos(), startX + DELTA, 0.001);
  }

  @Test
  public void testMoveNODIRECTION() throws Exception {
    player.setDx(0);
    player.move();
    assertEquals(player.getXPos(), startX, 0.0000001);
  }

  @Test
  public void testCollision() throws Exception {
    player.setXPos(100);
    player.setYPos(100);
    Ball ball = new Ball(100, 100, BallMovement.LEFT_MOVEMENT, 1);
    Application.getBoard().addObject(ball);
    assertTrue(player.collisionPlayer());
  }

  @Test
  public void testDoUpdate() throws Exception {
    player.setXPos(100);
    player.setYPos(100);

  }
}
