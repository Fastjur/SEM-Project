package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControlsScreenTest {
    private static String PROPERTIES_LOCATION = "/config.properties";

    @Test
    public void ControlsScreen() throws Exception {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
        ControlsScreen cs = new ControlsScreen();
        app.getBoard().changeScreen(cs);
        assertTrue(cs.objectList.isEmpty());
    }

}
