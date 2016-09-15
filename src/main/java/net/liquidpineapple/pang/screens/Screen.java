package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.GameObject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;

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
     * @param imageObserver
     */
    public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
        graphics2D.drawImage(backgroundImage, 0, 0, null);
        for(GameObject object : new ArrayList<GameObject>(objectList)) {
            object.doDrawing(graphics2D, imageObserver);
        }
    }

    /**
     * Method that updates all of the object on the screen.
     */
    public void doUpdate() {
        for(GameObject object : new ArrayList<GameObject>(objectList)) {
            object.doUpdate();
        }
    }
}
