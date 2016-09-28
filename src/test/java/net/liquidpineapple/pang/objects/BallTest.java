package net.liquidpineapple.pang.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BallTest {

  private Ball testBallLeft = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 4);
  private Ball testBallRight = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 3);
  private Ball testBallIncorrectSizeGreater = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 6);
  private Ball testBallIncorrectSizeSmaller = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 0);
  private static final double DELTA = 0.01;

  //Tests if the constructor handles left and right correctly.
  @Test
  public void testConstructorDirection() throws Throwable {
    assertEquals(-2 / 5.0, testBallLeft.getMovX(), DELTA);
    assertEquals(2 / 5.0, testBallRight.getMovX(), DELTA);
  }

  //Tests if the constructor handles sets movY correctly.
  @Test
  public void testConstructorYSpeed() throws Throwable {
    assertEquals(-2.0, testBallLeft.getMovY(), DELTA);
    assertEquals(-2.0, testBallRight.getMovY(), DELTA);
  }


  //Tests if the constructor handles size correctly
  @Test
  public void testConstructorSize() throws Throwable {
    assertEquals(4, testBallLeft.getBallSize());
    assertEquals(3, testBallRight.getBallSize());
  }

  //Tests if the constructor handles incorrect sizes correctly
  @Test
  public void testIncorrectSize() throws Throwable {
    assertEquals(4, testBallIncorrectSizeGreater.getBallSize());
    assertEquals(4, testBallIncorrectSizeSmaller.getBallSize());
  }

  @Test
  public void testMove() throws Exception {
    testBallLeft.setPos(5, 5);
    testBallLeft.setMovX(1);

    testBallLeft.move();
    assertEquals(6, testBallLeft.getXpos(), DELTA);

    testBallLeft.setMovX(-1);
    testBallLeft.move();
    assertEquals(5, testBallLeft.getXpos(), DELTA);

    testBallLeft.setMovY(1);
    testBallLeft.move();
    assertEquals(2.16, testBallLeft.getYpos(), DELTA);

    testBallLeft.setMovY(-1);
    testBallLeft.move();
    assertEquals(1.2000000000000002, testBallLeft.getYpos(), DELTA);

  }

}
