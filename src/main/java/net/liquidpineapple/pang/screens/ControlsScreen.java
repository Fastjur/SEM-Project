package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Class that represents the Controls screen.
 * @author Jaap-Jan
 * @date 13-9-2016.
 */
@Slf4j
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
                log.error("Could not load level", e);
            }
        }
    }
}
