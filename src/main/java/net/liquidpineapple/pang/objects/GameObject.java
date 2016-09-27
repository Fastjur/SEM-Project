package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.liquidpineapple.pang.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/07.
 */
@Data
@EqualsAndHashCode
public abstract class GameObject {

    protected double xPos;
    protected double yPos;
    private double width;
    private double height;
    private Image image;

    public GameObject(String textureLocation, double startX, double startY) {
        this.xPos = startX;
        this.yPos = startY;
        changeImage(textureLocation);
        Logger.info("GameObject registered at (" + xPos + ", " + yPos + ") with width " + width + " and height " + height);
    }

    public void changeImage(String textureLocation){
        ImageIcon imageIcon;
        Logger.info("Registering object with texture " + textureLocation);
        URL url = this.getClass().getResource(textureLocation);

        if (url == null) {
            throw new IllegalArgumentException("Could not find texture " + textureLocation);
        }

        imageIcon = new ImageIcon(url);
        image = imageIcon.getImage();
        getWidthAndHeight();
    }

    private void getWidthAndHeight() {
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(xPos, yPos, width, height);
    }

    //A Ellipse2D.float does also exists in case we need to be more accurate.
    protected Ellipse2D.Double getEllipseBounds() {
        return new Ellipse2D.Double(xPos, yPos, width, height);
    }

    public void setPos(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        Logger.info("Set position for " + this.toString() + "to (" + xPos + "," + yPos + ")");
    }

    public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
        graphics2D.drawImage(this.getImage(), (int)Math.round(this.getXPos()), (int)Math.round(this.getYPos()), imageObserver);
    }

    public void doUpdate() {

    }
}
