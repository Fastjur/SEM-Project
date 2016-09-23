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

    @Test
    public void testDisplayScore() throws Exception {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
        WinScreen winScreen = new WinScreen();
        app.getBoard().changeScreen(winScreen);
        Application.getScoreKeeper().setScore(10);
        winScreen.displayscore();
        assertEquals(winScreen.getPlaces().get(0).getcurrentnumber(), 0);
        assertEquals(winScreen.getPlaces().get(1).getcurrentnumber(), 1);
    }

}
