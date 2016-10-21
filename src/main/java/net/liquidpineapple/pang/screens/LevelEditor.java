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
import java.awt.event.KeyEvent;
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
  private static final int gametime = 120;
  private static boolean addedPlayer = false;
  public LinkedList<GameObject> addedObjects = new LinkedList<>();
  private int currentMouseX = 0;
  private int currentMouseY = 0;
  private BallMovement ballMovement = BallMovement.valueOf("LEFT_MOVEMENT");

  /**
   * Constructor for the LevelEditor screen.
   * Tries to set background.
   */
  public LevelEditor() {
    try {
      backgroundImage = ImageIO.read(LevelScreen.class.getResource("/sprites/editorbg.png"));
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
    currentMouseX = (int) (point1.getX() - point2.getX());
    currentMouseY = (int) (point1.getY() - point2.getY());
    if (InputHandler.isAnyKeyPressed()) {
      //key 1 pressed, add ball with size 1
      if (InputHandler.isKeyPressed(KeyEvent.VK_1)) {
        int size = 1;
        addBall(size);
      }
      //key 2 pressed, add ball with size 2
      if (InputHandler.isKeyPressed(KeyEvent.VK_2)) {
        int size = 2;
        addBall(size);
      }
      //key 3 pressed, add ball with size 3
      if (InputHandler.isKeyPressed(KeyEvent.VK_3)) {
        int size = 3;
        addBall(size);
      }
      //key 4 pressed, add ball with size 4
      if (InputHandler.isKeyPressed(KeyEvent.VK_4)) {
        int size = 4;
        addBall(size);
      }
      //key 5 pressed, add player
      if (InputHandler.isKeyPressed(KeyEvent.VK_5) && !addedPlayer) {
        addPlayer();
      }
      //key p pressed, play the last saved level
      if (InputHandler.isKeyPressed(KeyEvent.VK_P)) {
        playGame();
      }
      //key s pressed, save the current level
      if (InputHandler.isKeyPressed(KeyEvent.VK_S)) {
        saveGame();
      }
    }
    //select the object
    GameObject selectedObject = null;
    if (InputHandler.isLeftMouseButtonDown() || InputHandler.isRightMouseButtonDown()) {
      for (GameObject addedObject : addedObjects) {
        if (currentMouseX > addedObject.getXpos()
            && currentMouseX < addedObject.getXpos() + addedObject.getWidth()
            && currentMouseY > addedObject.getYpos()
            && currentMouseY < addedObject.getYpos() + addedObject.getHeight()) {
          selectedObject = addedObject;
        }
      }
    }

    //delete an object
    if (InputHandler.isRightMouseButtonDown() && selectedObject != null) {
      if (selectedObject instanceof Player) {
        addedPlayer = false;
      }
      deleteObject(selectedObject);
    }
    //drag and drop
    if (InputHandler.isLeftMouseButtonDown() && selectedObject != null) {
      dragAndDrop(selectedObject);
    }

    InputHandler.clearKeys();
  }

  private void playGame() {
    addedPlayer = false;
    UserCreatedLevels levels = new UserCreatedLevels();
    Application.getBoard().setLevels(levels.createIterator());
    Application.getBoard().changeScreen((Screen) Application.getBoard().getLevels().next());
  }

  private void saveGame() {
    if (addedPlayer) {
      createLevel();
    } else {
      int fixedPlayerYpos = 475;
      int fixedPlayerXpos = 380;
      addedObjects.add(new Player(fixedPlayerXpos, fixedPlayerYpos, new Player1()));
      addedPlayer = true;
      createLevel();
    }
  }

  private void addPlayer() {
    addedPlayer = true;
    int offset = 0;
    int fixedPlayerYpos = 475;
    try {
      Image playerImage = ImageIO.read(LevelScreen.class
          .getResource("/sprites/player/p1_front.png"));
      offset = playerImage.getWidth(null) / 2;
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    addedObjects.add(new Player(currentMouseX - offset, fixedPlayerYpos, new Player1()));
  }

  private void addBall(int ballSize) {
    int size = ballSize;
    Ball ball = new Ball(0, 0, ballMovement, size);
    int offset = (int) ball.getWidth() / 2;
    ball.setXpos(currentMouseX - offset);
    ball.setYpos(currentMouseY - offset);
    addedObjects.add(ball);
  }

  private void dragAndDrop(GameObject selectedObject) {
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

    XmlHandler.createXmlFromLevel(level, gametime);
  }

  /**
   * Method to delete objects from the screen.
   *
   * @param delete - Object which should be removed.
   */
  public void deleteObject(GameObject delete) {
    int size = addedObjects.size();
    for (int i = 0; i < size; i++) {
      if (addedObjects.get(i).equals(delete)) {
        addedObjects.remove(i);
        break;
      }
    }
  }
}
