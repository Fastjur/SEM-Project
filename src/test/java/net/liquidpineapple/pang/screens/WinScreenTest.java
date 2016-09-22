package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import org.junit.Test;

import static org.junit.Assert.*;


public class WinScreenTest {
    private static String PROPERTIES_LOCATION = "/config.properties";
    @Test
    public void WinScreen() throws Exception {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
        WinScreen winScreen = new WinScreen();
        app.getBoard().changeScreen(winScreen);
        assertFalse(winScreen.objectList.isEmpty());
    }

}
