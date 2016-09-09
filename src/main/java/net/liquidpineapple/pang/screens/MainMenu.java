package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.QuitButton;
import net.liquidpineapple.pang.objects.SinglePlayerButton;
import net.liquidpineapple.pang.objects.TitlePang;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        objectList.add(new TitlePang(50, 30));
        objectList.add(new SinglePlayerButton(200, 250)); 
        objectList.add(new QuitButton(200, 370));
    }
}