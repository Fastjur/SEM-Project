package net.liquidpineapple.pang.screens;

import lombok.Getter;
import lombok.Setter;

import net.liquidpineapple.pang.objects.GameObject;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Super class for all of the screens.
 *
 * @author Govert de Gans
 * @date 2016-09-08
 */
public abstract class Screen {

  public LinkedList<GameObject> objectList;
  @Setter
  protected Image backgroundImage;
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  @Getter
  @Setter
  private int difficulty;


  /**
   * Constructor of the screen.
   */
  public Screen() {
    this.objectList = new LinkedList<GameObject>();
  }

  /**
   * Method that draws object on the screen.
   *
   * @param graphics2D    - Object that needs to be drawn.
   * @param imageObserver {@link ImageObserver} the image observer.
   */
  public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
    graphics2D.drawImage(backgroundImage, 0, 0, null);
    for (GameObject object : new ArrayList<GameObject>(objectList)) {
      object.doDrawing(graphics2D, imageObserver);
    }
  }

  /**
   * Method to update the level.
   * This is were all the doUpdate() methods from the objectList are called.
   */
  public void doUpdate() {
    for (GameObject object : new ArrayList<GameObject>(objectList)) {
      object.doUpdate();
    }
  }
}
