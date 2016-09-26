package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.logger.Logger;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
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
                Application.lifeSystem.resetLives();
                Application.getScoreKeeper().resetScore();
                Application.getBoard().changeScreen(Level.createFromXML("/levels/level1.xml"));
            } catch (IOException | ParserConfigurationException | SAXException e) {
                Logger.error("Could not load level", e);
            }
        }
    }
}
