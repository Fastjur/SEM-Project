package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropRandomizerTest {

    private static String PROPERTIES_LOCATION = "/config.properties";

    @Before
    public void testinit() throws Throwable{
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
    }

    @Test
    public void testifitruns(){
        Application.getDropRandomizer().rollRandomdrop(0,0);
        //This is a randomized process, it doest have any guaranteed result and you can't input anything to ensure it either... :/
    }

}
