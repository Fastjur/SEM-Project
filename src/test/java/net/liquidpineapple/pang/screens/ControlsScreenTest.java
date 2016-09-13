package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jaap-Jan on 13-9-2016.
 */
public class ControlsScreenTest {
    private static String PROPERTIES_LOCATION = "/config.properties";

    @Test
    public void ControlsScreen() throws Exception {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
        ControlsScreen cs = new ControlsScreen();
        app.getBoard().changeScreen(cs);
        assertFalse(cs.objectList.isEmpty());
    }

}
