package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
@Slf4j
public class Level extends Screen {

    private static final int BOTTOM_OFFSET = 10;

    public Level(int boardWidth, int boardHeight) {
        super(boardWidth, boardHeight);

        this.objectList = new LinkedList<GameObject>();

        //currentLevel = Level.createFromXML("level1.xml");

        Player player = new Player(1, 1, boardWidth);
        objectList.add(player);
        player.setPos(
            boardWidth / 2 - player.getWidth() / 2,
            boardHeight - player.getHeight() - BOTTOM_OFFSET
        );
    }

    public static Level createFromXML(String xmlFile, int width, int height) {
        Level output = new Level(width,  height);
        try {
            output.backgroundImage = ImageIO.read(Level.class.getResource("/sprites/bg.png"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        // todo: actually parse an xml file, output.objectList.add() & stuff
        return output;
    }
}
