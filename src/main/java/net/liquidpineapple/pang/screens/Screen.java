package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import static net.liquidpineapple.pang.screens.Level.createFileReader;

/**
 * Super class for all of the screens.
 * @author Govert de Gans
 * @date 2016-09-08
 */
public abstract class Screen {

    public LinkedList<GameObject> objectList;
    protected Image backgroundImage;


    /**
     * Constructor of the screen.
     */
    public Screen() {
        this.objectList = new LinkedList<GameObject>();
    }

    /**
     * Method that draws object on the screen.
     * @param graphics2D - Object that needs to be drawn.
     * @param imageObserver {@link ImageObserver} the image observer.
     */
    public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
        graphics2D.drawImage(backgroundImage, 0, 0, null);
        for(GameObject object : new ArrayList<GameObject>(objectList)) {
            object.doDrawing(graphics2D, imageObserver);
        }
    }

    /**
     * Method to update the level.
     * This is were all the doUpdate() methods from the objectList are called.
     */
    public void doUpdate() {
        for(GameObject object : new ArrayList<GameObject>(objectList)) {
            object.doUpdate();
        }
    }
}
