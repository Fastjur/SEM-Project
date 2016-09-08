package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.SinglePlayerButton;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author Govert de Gans
 * @date 2016-09-08
 */
public class MainMenu extends Screen {
    public MainMenu(int width, int height) {
        super(width, height);
        try {
            backgroundImage = ImageIO.read(Level.class.getResource("/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        objectList.add(new SinglePlayerButton(20, 30));
    }
}
