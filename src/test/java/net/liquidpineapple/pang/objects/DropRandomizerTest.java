package net.liquidpineapple.pang.objects;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropRandomizerTest {

    @Before
    public void setUp() throws Throwable{
        DropRandomizer.getInstance();
    }

    @Test
    public void testRollRandomDrop(){
        DropRandomizer.rollRandomdrop(0,0);
        //This is a randomized process, it doest have any guaranteed result and you can't input anything to ensure it either... :/
    }

}
