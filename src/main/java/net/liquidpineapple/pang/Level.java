package net.liquidpineapple.pang;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
@Slf4j
public class Level {

    private LinkedList<GameObject> objectList;
    private Image backgroundImage;

    public Level() {
        this.objectList = new LinkedList<GameObject>();
    }

    public static Level createFromXML(String xmlFile) {
        Level output = new Level();
        try {
            output.backgroundImage = ImageIO.read(Level.class.getResource("/sprites/bg.png"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        // todo: actually parse an xml file, output.objectList.add() & stuff
        return output;
    }

    public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
        graphics2D.drawImage(backgroundImage, 0, 0, null);
        for(GameObject object : objectList) {
            object.doDrawing(graphics2D, imageObserver);
        }
    }
}
