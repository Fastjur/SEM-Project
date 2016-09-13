package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.ControlsObj;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Jaap-Jan on 13-9-2016.
 */
public class ControlsScreen extends Screen{
    public ControlsScreen() {
        try {
            backgroundImage = ImageIO.read(Level.class.getResource("/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        objectList.add(new ControlsObj(0, 10));
    }
}
