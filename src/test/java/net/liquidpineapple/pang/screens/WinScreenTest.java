package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.ScoreSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class WinScreenTest {

    private Application app;
    private static String PROPERTIES_LOCATION = "/config.properties";

    @Before
    public void setUp() throws Exception {
        app = new Application(PROPERTIES_LOCATION);
        app.start();
    }

    @After
    public void tearDown() throws Exception {
        app.close();
    }

    @Test
    public void WinScreen() throws Exception {
        WinScreen winScreen = new WinScreen();
        Application.getBoard().changeScreen(winScreen);
        assertFalse(winScreen.objectList.isEmpty());
    }

    @Test
    public void testDisplayScore() throws Exception {
        WinScreen winScreen = new WinScreen();
        Application.getBoard().changeScreen(winScreen);
        ScoreSystem.setScore(10);
        winScreen.displayscore();

        assertEquals(0, WinScreen.getPlaces().get(0).getcurrentnumber());
        assertEquals(1, WinScreen.getPlaces().get(1).getcurrentnumber());
    }

}
