package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.XmlHandler;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.BallMovement;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * Class that represents the LevelEditor.
 *
 * @author Jaap-Jan
 * @date 10-10-2016.
 */
public class LevelEditor extends Screen {
  public LinkedList<GameObject> addedObjects = new LinkedList<>();

  /**
   * Constructor for the LevelEditor screen.
   * Tries to set background.
   */
  public LevelEditor() {
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/bg.png"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Method that updates the Leveleditor.
   */
  @Override
  public void doUpdate() {
    super.doUpdate();
    PointerInfo pointerInfo = MouseInfo.getPointerInfo();
    Point point1 = pointerInfo.getLocation();
    Point point2 = Application.getBoard().getLocationOnScreen();
    int intXpos = (int) (point1.getX() - point2.getX());
    int intYpos = (int) (point1.getY() - point2.getY());
    if (InputHandler.isAnyKeyPressed()) {
      BallMovement ballMovement = BallMovement.valueOf("LEFT_MOVEMENT");
      //key 1 pressed, add ball with size 1
      if (InputHandler.getKeysDown().contains(49)) {
        InputHandler.getKeysDown().clear();
        int size = 1;
        int offset = 0;
        try {
          Image ballImage = ImageIO.read(LevelScreen.class.getResource("/sprites/Balls/green.png"));
          offset = ballImage.getWidth(null) / 2;
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        addedObjects.add(new Ball(intXpos - offset, intYpos - offset, ballMovement, size));
      }
      //key 2 pressed, add ball with size 2
      if (InputHandler.getKeysDown().contains(50)) {
        InputHandler.getKeysDown().clear();
        int size = 2;
        int offset = 0;
        try {
          Image ballImage = ImageIO.read(LevelScreen.class.getResource("/sprites/Balls/red.png"));
          offset = ballImage.getWidth(null) / 2;
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        addedObjects.add(new Ball(intXpos - offset, intYpos - offset, ballMovement, size));
      }
      //key 3 pressed, add ball with size 3
      if (InputHandler.getKeysDown().contains(51)) {
        InputHandler.getKeysDown().clear();
        int size = 3;
        int offset = 0;
        try {
          Image ballImage = ImageIO.read(LevelScreen.class
              .getResource("/sprites/Balls/yellow.png"));
          offset = ballImage.getWidth(null) / 2;
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        addedObjects.add(new Ball(intXpos - offset, intYpos - offset, ballMovement, size));
      }
      //key 4 pressed, add ball with size 4
      if (InputHandler.getKeysDown().contains(52)) {
        InputHandler.getKeysDown().clear();
        int size = 4;
        int offset = 0;
        try {
          Image ballImage = ImageIO.read(LevelScreen.class.getResource("/sprites/Balls/blue.png"));
          offset = ballImage.getWidth(null) / 2;
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        addedObjects.add(new Ball(intXpos - offset, intYpos - offset, ballMovement, size));
      }
      //key 5 pressed, add player
      if (InputHandler.getKeysDown().contains(53)) {
        InputHandler.getKeysDown().clear();
        int offset = 0;
        int fixedPlayerYpos = 475;
        try {
          Image ballImage = ImageIO.read(LevelScreen.class
              .getResource("/sprites/player/p1_front.png"));
          offset = ballImage.getWidth(null) / 2;
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        addedObjects.add(new Player(intXpos - offset, fixedPlayerYpos, new Player1()));
      }
      //key p pressed, play the last saved level
      if (InputHandler.getKeysDown().contains(80)) {
        InputHandler.getKeysDown().clear();
        UserCreatedLevels levels = new UserCreatedLevels();
        Application.getBoard().setLevels(levels.createIterator());
        Application.getBoard().changeScreen((Screen) Application.getBoard().getLevels().next());
      }
      //key s pressed, save the current level
      if (InputHandler.getKeysDown().contains(83)) {
        InputHandler.getKeysDown().clear();
        createLevel();
      }
    }
    //select the object
    GameObject selectedObject = null;
    if (InputHandler.isLeftMouseButtonDown() || InputHandler.isRightMouseButtonDown()) {
      for (GameObject addedObject : addedObjects) {
        if (intXpos > addedObject.getXpos()
            && intXpos < addedObject.getXpos() + addedObject.getWidth()
            && intYpos > addedObject.getYpos()
            && intYpos < addedObject.getYpos() + addedObject.getHeight()) {
          selectedObject = addedObject;
        }
      }
    }

    //delete an object
    if (InputHandler.isRightMouseButtonDown() && selectedObject != null) {
      deleteObject(selectedObject);
    }

    //drag and drop
    while (InputHandler.isLeftMouseButtonDown() && selectedObject != null) {
      PointerInfo mousePoint = MouseInfo.getPointerInfo();
      Point pointb = mousePoint.getLocation();
      Point pointc = Application.getBoard().getLocationOnScreen();
      int mouseXpos = (int) (pointb.getX() - pointc.getX());
      int mouseYpos = (int) (pointb.getY() - pointc.getY());
      double newXpos = mouseXpos - (selectedObject.getWidth() / 2);
      double newYpos = mouseYpos - (selectedObject.getHeight() / 2);
      if (mouseXpos >= 0 + (selectedObject.getWidth() / 2)
          && mouseXpos + (selectedObject.getWidth() / 2) <= Application.getBoard().getWidth()) {
        selectedObject.setXpos(newXpos);
      }
      if (selectedObject instanceof Ball
          && mouseYpos >= 0 + (selectedObject.getHeight() / 2)
          && mouseYpos + (selectedObject.getHeight() / 2) <= Application.getBoard().getHeight()) {
        selectedObject.setYpos(newYpos);
      }
    }
  }

  /**
   * Method used to draw the objects on the screen.
   */
  @Override
  public void doDrawing(Graphics2D graphics2D, ImageObserver imageObserver) {
    super.doDrawing(graphics2D, imageObserver);
    for (GameObject addedObject : addedObjects) {
      addedObject.doDrawing(graphics2D, imageObserver);
    }
  }

  /**
   * Method used to parse the current objects on the screen into a xml-file.
   */
  public void createLevel() {

    LevelScreen level = new LevelScreen();
    level.objectList = addedObjects;

    XmlHandler.createXmlFromLevel(level, 120);
  }

  /**
   * Method to delete objects from the screen.
   *
   * @param delete - Object which should be removed.
   */
  public void deleteObject(GameObject delete) {
    LinkedList<GameObject> templist;
    templist = addedObjects;
    for (int i = 0; i < templist.size(); i++) {
      if (templist.get(i).equals(delete)) {
        addedObjects.remove(i);
        break;
      }
    }
  }
}
