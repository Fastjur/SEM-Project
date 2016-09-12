package net.liquidpineapple.pang;

import net.liquidpineapple.pang.objects.Ball;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.liquidpineapple.pang.objects.BallMovement;
import org.junit.Test;

public class BallTest {

    private Ball TestBallLeft = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 4);
    private Ball TestBallRight = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 3);
    private Ball TestBallIncorrectSizeGreater = new Ball(0, 0, BallMovement.LEFT_MOVEMENT, 6);
    private Ball TestBallIncorrectSizeSmaller = new Ball(0, 0, BallMovement.RIGHT_MOVEMENT, 0);

    //Tests if the constructor handles left and right correctly.
    @Test
    public void testConstructorDirection() throws Throwable {
        assertEquals(-15, TestBallLeft.getMovX());
        assertEquals(15, TestBallRight.getMovX());
    }

   //Tests if the constructor handles sets movY correctly.
    @Test
    public void testConstructorYSpeed() throws Throwable {
        assertEquals(17, TestBallLeft.getMovY());
        assertEquals(14, TestBallRight.getMovY());
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

}
