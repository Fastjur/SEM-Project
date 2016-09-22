package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropTest {

    private static String PROPERTIES_LOCATION = "/config.properties";
    LifeSystem lifeSystem = new LifeSystem();
    Drop Drop1live  = new Drop("/sprites/drops/heart.png", 0, 0, 0, 1, 0, 1);
    Drop Droplose2live  = new Drop("/sprites/drops/heart.png", 0, 0, 0, 1, 0, -2);

    private final double DELTA = 0.01;

    float movement = 1;
    Drop Drop100p  = new Drop("/sprites/drops/heart.png", 0, 0, 2*movement, movement, 100);
    Drop Drop1000p  = new Drop("/sprites/drops/heart.png", 0, 0, 0, 1, 1000);

    @Before
    public void testinit() throws Throwable{
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
    }

    @Test
    public void testlifegain(){
        int oldlives = lifeSystem.getLives();
        Droplose2live.playerCollision();
        assertEquals(oldlives-2, LifeSystem.getLives());
        Drop1live.playerCollision();
        assertEquals(oldlives-1, LifeSystem.getLives());
    }

    @Test
    public void testscore(){
        int oldscore = ScoreSystem.getScore();
        Drop100p.playerCollision();
        assertEquals(oldscore+100, ScoreSystem.getScore());
        Drop1000p.playerCollision();
        assertEquals(oldscore+1100, ScoreSystem.getScore());
    }

    @Test
    public void updatetest(){
        Drop100p.doUpdate();
        assertEquals(2*movement,Drop100p.getXPos(), DELTA);
        assertEquals(movement,Drop100p.getYPos(), DELTA);
    }

}
