package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.objects.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
@Slf4j
public class Level extends Screen {

    private static final int BOTTOM_OFFSET = 10;

    public Level() {

        //currentLevel = Level.createFromXML("level1.xml");

        Player player = new Player(1, 1, 600);
        objectList.add(player);
        player.setPos(
            51, 20
        );
    }

    public static Level createFromXML(String xmlFile, int width, int height) {
        Level output = new Level();
        try {
            output.backgroundImage = ImageIO.read(Level.class.getResource("/sprites/bg.png"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        // todo: actually parse an xml file, output.objectList.add() & stuff
        return output;
    }
}
