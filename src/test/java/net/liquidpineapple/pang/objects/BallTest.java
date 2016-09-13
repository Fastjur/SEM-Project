package net.liquidpineapple.pang.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BallTest {

    private Ball TestBallLeft = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 4);
    private Ball TestBallRight = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 3);
    private Ball TestBallIncorrectSizeGreater = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 6);
    private Ball TestBallIncorrectSizeSmaller = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 0);

    //Tests if the constructor handles left and right correctly.
    @Test
    public void testConstructorDirection() throws Throwable {
        assertEquals(-2, TestBallLeft.getMovX());
        assertEquals(2, TestBallRight.getMovX());
    }

   //Tests if the constructor handles sets movY correctly.
    @Test
    public void testConstructorYSpeed() throws Throwable {
        assertEquals(0, TestBallLeft.getMovY());
        assertEquals(0, TestBallRight.getMovY());
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
        assertEquals(6, TestBallLeft.getXPos());

        TestBallLeft.setMovX(-1);
        TestBallLeft.move();
        assertEquals(5, TestBallLeft.getXPos());

        TestBallLeft.setMovY(1);
        TestBallLeft.move();
        assertEquals(10, TestBallLeft.getYPos());

        TestBallLeft.setMovY(-1);
        TestBallLeft.move();
        assertEquals(10, TestBallLeft.getYPos());

    }

}
