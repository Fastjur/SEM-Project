package net.liquidpineapple.pang.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 13-9-2016.
 */
public class LifeSystemTest {

    LifeSystem LifeSystTester = new LifeSystem();

    @Test
    public void constructorTest(){
        assertEquals(LifeSystTester.getLives(),3);
    }


    @Test
    public void Gainliveat3(){
        while(LifeSystTester.getLives()<3){LifeSystem.gainLife();}
        LifeSystem.gainLife();
        assertEquals(LifeSystTester.getLives(), 3);
    }

    @Test
    public void Loseliveat3andthengain(){
        while(LifeSystTester.getLives()<3){LifeSystem.gainLife();}
        LifeSystem.loseLife();
        assertEquals(LifeSystTester.getLives(), 2);
        LifeSystem.gainLife();
        assertEquals(LifeSystTester.getLives(), 3);
    }



}
