package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/07.
 */
@Data
@EqualsAndHashCode
@Slf4j
public abstract class GameObject extends JComponent {

    private static final String defaultTexture = "/sprites/no-texture.png";

    protected int xPos;
    protected int yPos;
    private int objectWidth;
    private int objectHeight;
    private Image image;

    public GameObject(String textureLocation, int startX, int startY) {
        this.xPos = startX;
        this.yPos = startY;
        ChangeImage(textureLocation);
    }

    public void ChangeImage(String textureLocation){
        ImageIcon imageIcon;
        log.info("Registering object with texture " + textureLocation);
        URL url = this.getClass().getResource(textureLocation);

        if (url != null) {
            imageIcon = new ImageIcon(url);
        } else {
            imageIcon = new ImageIcon(this.getClass().getResource(defaultTexture));
            log.error("Could not find texture " + textureLocation + " resolving to default " +
                "texture");
        }
        image = imageIcon.getImage();
        getWidthAndHeight();
    }

    private void getWidthAndHeight() {
        this.objectWidth = image.getWidth(null);
        this.objectHeight = image.getHeight(null);
    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, objectWidth, objectHeight);

    }
    //A Ellipse2D.float does also exists in case we need to be more accurate.
    protected Ellipse2D.Double getEllipseBounds() {return new Ellipse2D.Double(xPos, yPos, objectWidth, objectHeight); }

    public void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
        graphics2D.drawImage(this.getImage(), this.getXPos(), this.getYPos(), imageObserver);
    }

    public void doUpdate() {

    }
}
