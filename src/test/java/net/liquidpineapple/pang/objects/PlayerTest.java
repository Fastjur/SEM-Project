package net.liquidpineapple.pang.objects;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import net.liquidpineapple.pang.objects.playerschemes.Player2;
import net.liquidpineapple.pang.objects.playerschemes.PlayerScheme;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/12.
 */
public class PlayerTest {

  private Application app;

  private static final int startX = 15;
  private static final int startY = 20;

  private static final String SOME_TEXTURE = "/sprites/beam1.png";
  private static final double DELTA = 0.01;

  private Player player;
  private Player player2;
  private PlayerScheme playerScheme;

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
    playerScheme = player.getPlayerScheme();
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
    InputStream newImgStream = getClass().getResourceAsStream(SOME_TEXTURE);
    BufferedImage newImg = ImageIO.read(newImgStream);
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

  @Test
  public void testFrozenTexture() throws Throwable {
    player.doUpdate();
    InputStream frozenImgStream = getClass().getResourceAsStream(playerScheme.getFrozenTextureName());
    BufferedImage frozenImg = ImageIO.read(frozenImgStream);
    BufferedImage currentPlayerImg = player.getImage();

    assertFalse(compareImages(frozenImg, currentPlayerImg));

    TimeSystem.setFrozen(1);
    player.doUpdate();

    currentPlayerImg = player.getImage();
    assertTrue(compareImages(frozenImg, currentPlayerImg));
  }

  @Test
  public void testShieldTexture() throws Throwable {
    player.doUpdate();
    InputStream shieldImgStream = getClass().getResourceAsStream(playerScheme.getShieldTextureName());
    BufferedImage shieldImg = ImageIO.read(shieldImgStream);
    BufferedImage currentPlayerImg = player.getImage();

    assertFalse(compareImages(shieldImg, currentPlayerImg));

    TimeSystem.setFrozen(0);
    player.setShield(1);
    player.doUpdate();

    currentPlayerImg = player.getImage();

    assertTrue(compareImages(shieldImg, currentPlayerImg));
  }

  @Test
  public void testBackToDefaultTexture() throws Throwable {
    TimeSystem.setFrozen(1);
    player.doUpdate();
    TimeSystem.setFrozen(0);
    player.setShield(0);
    player.doUpdate();

    InputStream defaultPlayerTextureStream = getClass().getResourceAsStream(playerScheme.getTextureName());
    BufferedImage defaultPlayerTexture = ImageIO.read(defaultPlayerTextureStream);
    BufferedImage currentPlayerImg = player.getImage();

    assertTrue(compareImages(defaultPlayerTexture, currentPlayerImg));

    player.setShield(1);
    player.doUpdate();
    player.setShield(0);
    player.doUpdate();

    currentPlayerImg = player.getImage();
    assertTrue(compareImages(defaultPlayerTexture, currentPlayerImg));
  }

  /**
   * Returns true if both images are equal.
   * @param imgA Image 1 to compare
   * @param imgB Image 2 to compare
   * @return True iff images are equal (graphical wise)
   */
  private static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {

    if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
      int width = imgA.getWidth(null);
      int height = imgB.getHeight(null);

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
            return false;
          }
        }
      }
    } else {
      return false;
    }

    return true;
  }
}
