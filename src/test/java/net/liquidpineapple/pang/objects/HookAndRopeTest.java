package net.liquidpineapple.pang.objects;

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
        assertTrue(hookAndRope.isInUse());
        assertEquals(599, hookAndRope.getYPos());
    }

    /**
     *Tests the behaviour of the move method when the rope collides with the maximum height.
     */
    @Test
    public void hitTopMoveTest() throws Exception {
        HookAndRope rope = new HookAndRope(500, 598);
        assertEquals(rope.getYPos(), 600);
        rope.move();
        assertTrue(rope.isInUse());
        assertEquals(rope.getYPos(), 599);
        rope.move();
        assertFalse(rope.isInUse());
        assertEquals(rope.getYPos(), 600);

    }

    /**
     * Tests the doUpdate method of the HookAndRope class.
     * @throws Exception
     */
    @Test
    public void doUpdateWhileNotInUseTest() throws Exception {
        hookAndRope.setInUse(false);
        assertEquals(hookAndRope.getYPos(), 600);
        hookAndRope.doUpdate();
        assertEquals(hookAndRope.getYPos(), 600);
    }

    @Test
    public void doUpdateWhileInUseTest() throws Exception {
        hookAndRope.setInUse(true);
        assertEquals(hookAndRope.getYPos(), 600);
        hookAndRope.doUpdate();
        assertEquals(hookAndRope.getYPos(), 599);
    }

    @After
    public void tearDown(){
        hookAndRope = null;
    }
}
