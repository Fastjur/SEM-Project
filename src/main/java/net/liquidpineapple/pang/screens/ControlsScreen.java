package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.logger.Logger;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Class that represents the Controls screen.
 * @author Jaap-Jan
 * @date 13-9-2016.
 */
public class ControlsScreen extends Screen{
    /**
     * Constructor for the ControlsScreen.
     */
    public ControlsScreen() {
        try {
            backgroundImage = ImageIO.read(Level.class.getResource("/sprites/controlsbg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that updates the controlsScreen.
     */
    @Override
    public void doUpdate() {
        super.doUpdate();
        if(InputHandler.isAnyKeyPressed()){
            try {
                Application.lifeKeeper.resetLives();
                Application.getScoreKeeper().resetScore();
                Application.getBoard().changeScreen(Level.createFromXML("src/main/resources/levels/level9000.xml"));
            } catch (IOException e) {
                Logger.error("Could not load level", e);
            }
        }
    }
}
