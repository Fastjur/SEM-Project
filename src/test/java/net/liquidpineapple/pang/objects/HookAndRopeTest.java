package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tim on 13-9-2016.
 * Class to test HookAndRope class.
 */
public class HookAndRopeTest {

    HookAndRope hookAndRope;
    private static final int XCOORD = 400;
    private static final int MAXY = 300;

    /**
     * Setup new HookAndRope.
     */
    @Before
    public void setUp(){
        hookAndRope = new HookAndRope(XCOORD, MAXY);
    }

    /**
     * Tests the constructor of the HookAndRope class.
     * @throws Exception
     */
    @Test
    public void constructorTest() throws Exception{
        assertEquals(hookAndRope.getXPos(), XCOORD);
        assertEquals(hookAndRope.getYPos(), 600);
    }

    /**
     * Tests the normal behaviour of move method in the HookAndRope class.
     */
    @Test
    public void moveTest() throws Exception {
        assertEquals(600, hookAndRope.getYPos());
        hookAndRope.move();
        assertEquals(585, hookAndRope.getYPos());
    }

    /**
     *Tests the behaviour of the move method when the rope collides with the maximum height.
     * TODO: MOCK THIS TEST TO PREVENT FAILURE.

    @Test
    public void hitTopMoveTest() throws Exception {
        HookAndRope rope = new HookAndRope(500, 584);
        assertEquals(rope.getYPos(), 600);
        rope.move();
        assertEquals(rope.getYPos(), 585);
        rope.move();
        assertFalse(Application.getBoard().containsObject(rope));

    }
     */
    @After
    public void tearDown(){
        hookAndRope = null;
    }
}
