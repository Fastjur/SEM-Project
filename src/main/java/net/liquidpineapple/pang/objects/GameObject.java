package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;


import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/07.
 */
@Data
@EqualsAndHashCode
public abstract class GameObject {

  protected double xpos;
  protected double ypos;
  private double width;
  private double height;
  private Image image;

  /**
   * Constructor for gameobject.
   *
   * @param textureLocation - texture storage location of object.
   * @param startX          - x pos of object.
   * @param startY          - y pos of object.
   */
  public GameObject(String textureLocation, double startX, double startY) {
    this.xpos = startX;
    this.ypos = startY;

    changeImage(textureLocation);
    Logger.info("GameObject registered at (" + xpos + ", " + ypos + ") with width " + width
        + " and height " + height);
  }

  /**
   * Method that changes the image.
   *
   * @param textureLocation - image storage location.
   */
  public void changeImage(String textureLocation) {
    ImageIcon imageIcon;
    Logger.info("Registering object with texture " + textureLocation);

    Image img;
    if ((img = Application.imageCache.getOrDefault(textureLocation, null)) != null) {
      this.image = img;
    } else {
      URL url = this.getClass().getResource(textureLocation);
      if (url == null) {
        throw new IllegalArgumentException("Could not find texture " + textureLocation);
      }
      imageIcon = new ImageIcon(url);
      image = imageIcon.getImage();
      Application.imageCache.put(textureLocation, image);
      Logger.info("Loaded image " + textureLocation);
    }
    getWidthAndHeight();
  }

  private void getWidthAndHeight() {
    this.width = image.getWidth(null);
    this.height = image.getHeight(null);
  }

  public Rectangle2D.Double getBounds() {
    return new Rectangle2D.Double(xpos, ypos, width, height);
  }

  //A Ellipse2D.float does also exists in case we need to be more accurate.
  protected Ellipse2D.Double getEllipseBounds() {
    return new Ellipse2D.Double(xpos, ypos, width, height);
  }

  /**
   * Method that sets the position of an object.
   *
   * @param xposition - x pos of object.
   * @param yposition - y pos of object.
   */
  public void setPos(double xposition, double yposition) {
    this.xpos = xposition;
    this.ypos = yposition;
    Logger.info("Set position for " + this.toString() + "to (" + xposition + "," + yposition + ")");
  }

  public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
    graphics2D.drawImage(this.getImage(), (int) Math.round(this.getXpos()),
        (int) Math.round(this.getYpos()), imageObserver);
  }

  public void doUpdate() {

  }
}
