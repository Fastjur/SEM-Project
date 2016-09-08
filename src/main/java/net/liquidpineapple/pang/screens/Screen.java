package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.objects.GameObject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

/**
 * @author Govert de Gans
 * @date 2016-09-08
 */
public abstract class Screen {

    protected LinkedList<GameObject> objectList;
    protected Image backgroundImage;

    public Screen(int width, int height) {
        this.objectList = new LinkedList<GameObject>();
    }

    public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
        graphics2D.drawImage(backgroundImage, 0, 0, null);
        for(GameObject object : objectList) {
            object.doDrawing(graphics2D, imageObserver);
        }
    }

    public void doUpdate() {
        for(GameObject object : objectList) {
            object.doUpdate();
        }
    }
}
