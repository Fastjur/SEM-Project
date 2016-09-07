package net.liquidpineapple.pang;

import net.liquidpineapple.pang.objects.Ball;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BallTest {

    Ball TestBallLeft = new Ball(0, 0, "left", 4);
    Ball TestBallRight = new Ball(0, 0, "right", 3);
    Ball TestBallIncorrectSizeGreater = new Ball(0, 0, "left", 6);
    Ball TestBallIncorrectSizeSmaller = new Ball(0, 0, "left", 0);
    Ball TestBallIncorrectDirection = new Ball(0, 0, "Cheese", 3);

    //Tests if the constructor handles left and right correctly.
    @Test
    public void testConstructorDirection() throws Throwable {
        assertEquals(-15, TestBallLeft.getMovX());
        assertEquals(15, TestBallRight.getMovX());
    }

    //Tests if the constructor handles size correctly
    @Test
    public void testConstructorSize() throws Throwable {
        assertEquals(4, TestBallLeft.getSize());
        assertEquals(3, TestBallRight.getSize());
    }

    //Tests if the constructor handles incorrect sizes correctly
    @Test
    public void testIncorrectSize() throws Throwable {
        assertEquals(4, TestBallIncorrectSizeGreater.getSize());
        assertEquals(4, TestBallIncorrectSizeSmaller.getSize());
    }

    //Tests if the constructor handles incorrect directions properly.
    @Test
    public void testIncorrectDirection() throws Throwable {
        assertTrue(TestBallIncorrectDirection.getMovX() == 15 || TestBallIncorrectDirection.getMovX() == -15);
    }

}
