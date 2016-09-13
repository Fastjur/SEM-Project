package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.objects.ControlsObj;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * Created by Jaap-Jan on 13-9-2016.
 */
@Slf4j
public class ControlsScreen extends Screen{
    public ControlsScreen() {
        try {
            backgroundImage = ImageIO.read(Level.class.getResource("/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        objectList.add(new ControlsObj(0, 10));
    }

    @Override
    public void doUpdate() {
        super.doUpdate();
        if(InputHandler.isAnyKeyPressed()){
            try {
                Application.getBoard().changeScreen(Level.createFromXML("src/main/resources/levels/level1.xml"));
            } catch (IOException e) {
                log.error("Could not load level", e);
            }
        }
    }
}
