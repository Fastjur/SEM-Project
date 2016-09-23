package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tim on 23-9-2016.
 */
public class GameOverScreenTest {
    private static String PROPERTIES_LOCATION = "/config.properties";

    @Test
    public void GameOverScreen() throws Exception {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
        GameOverScreen gameOverScreen = new GameOverScreen();
        app.getBoard().changeScreen(gameOverScreen);
        assertFalse(gameOverScreen.objectList.isEmpty());
    }

    @Test
    public void testDisplayScore() throws Exception {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
        GameOverScreen gameOverScreen = new GameOverScreen();
        app.getBoard().changeScreen(gameOverScreen);
        Application.getScoreKeeper().setScore(10);
        gameOverScreen.displayscore();
        assertEquals(gameOverScreen.getPlaces().get(0).getcurrentnumber(), 0);
        assertEquals(gameOverScreen.getPlaces().get(1).getcurrentnumber(), 1);
    }


}
