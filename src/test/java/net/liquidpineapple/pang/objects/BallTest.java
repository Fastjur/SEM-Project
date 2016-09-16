package net.liquidpineapple.pang.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BallTest {

    private Ball TestBallLeft = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 4);
    private Ball TestBallRight = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 3);
    private Ball TestBallIncorrectSizeGreater = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 6);
    private Ball TestBallIncorrectSizeSmaller = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 0);
    private final double DELTA = 0.01;

    //Tests if the constructor handles left and right correctly.
    @Test
    public void testConstructorDirection() throws Throwable {
        assertEquals(-2/5.0, TestBallLeft.getMovX(), DELTA);
        assertEquals(2/5.0, TestBallRight.getMovX(), DELTA);
    }

   //Tests if the constructor handles sets movY correctly.
    @Test
    public void testConstructorYSpeed() throws Throwable {
        assertEquals(0, TestBallLeft.getMovY(), DELTA);
        assertEquals(0, TestBallRight.getMovY(), DELTA);
    }


    //Tests if the constructor handles size correctly
    @Test
    public void testConstructorSize() throws Throwable {
        assertEquals(4, TestBallLeft.getBallSize());
        assertEquals(3, TestBallRight.getBallSize());
    }

    //Tests if the constructor handles incorrect sizes correctly
    @Test
    public void testIncorrectSize() throws Throwable {
        assertEquals(4, TestBallIncorrectSizeGreater.getBallSize());
        assertEquals(4, TestBallIncorrectSizeSmaller.getBallSize());
    }

    @Test
    public void testMove() throws Exception {
        TestBallLeft.setPos(5, 5);
        TestBallLeft.setMovX(1);

        TestBallLeft.move();
        assertEquals(6, TestBallLeft.getXPos(), DELTA);

        TestBallLeft.setMovX(-1);
        TestBallLeft.move();
        assertEquals(5, TestBallLeft.getXPos(), DELTA);

        TestBallLeft.setMovY(1);
        TestBallLeft.move();
        assertEquals(6.16, TestBallLeft.getYPos(), DELTA);

        TestBallLeft.setMovY(-1);
        TestBallLeft.move();
        assertEquals(5.2, TestBallLeft.getYPos(), DELTA);

    }

}
