package net.liquidpineapple.pang.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 13-9-2016.
 */
public class ScoreSystemTest {

    ScoreSystem ScoreSysTester = new ScoreSystem();

    @Test
    public void constructorTest(){
        assertEquals(ScoreSysTester.getScore(),0);
        for (NumberToken token : ScoreSystem.getPlaces()) {
            assertEquals(token.getcurrentnumber(), 0);
        }
    }


    @Test
    public void addTest(){
        ScoreSystem.AddScore(500);
        assertEquals(ScoreSysTester.getScore(),500);
        ScoreSysTester.AddScore(356);
        assertEquals(ScoreSysTester.getScore(),856);
        ScoreSysTester.displayscore();
        assertEquals(ScoreSystem.getPlaces().get(0).getcurrentnumber(), 6);

        assertEquals(ScoreSystem.getPlaces().get(1).getcurrentnumber(), 5);

        assertEquals(ScoreSystem.getPlaces().get(2).getcurrentnumber(), 8);
    }

}
