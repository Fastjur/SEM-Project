package net.liquidpineapple.pang;

import net.liquidpineapple.pang.logger.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

/**
 * Class that represents the handler for mouseclicks and keypresses.
 *
 * @author Govert de Gans
 * @date 2016-09-12
 */
public class InputHandler implements MouseListener, KeyListener {

  private static HashSet<Integer> keysDown = new HashSet<>();
  private static HashSet<Integer> keysPressed = new HashSet<>();
  private static Point mousePos;
  private static int mouseButtonPressed;

  public InputHandler() {
  }


  @Override
  public void keyPressed(KeyEvent event) {
    keysDown.add(event.getKeyCode());
    if (!keysPressed.contains(event.getKeyCode())) {
      keysPressed.add(event.getKeyCode());
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {
    keysDown.remove(event.getKeyCode());
    keysPressed.remove(event.getKeyCode());
  }

  @Override
  public void keyTyped(KeyEvent event) {
  }


  @Override
  public void mouseClicked(MouseEvent event) {

  }

  @Override
  public void mousePressed(MouseEvent event) {
    mousePos = new Point(event.getX(), event.getY());
    mouseButtonPressed = event.getButton();
    Logger.info("pang-1");
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    mouseButtonPressed = 0;
  }

  @Override
  public void mouseEntered(MouseEvent event) {

  }

  @Override
  public void mouseExited(MouseEvent event) {

  }

  public static boolean isKeyDown(int keyCode) {
    return keysDown.contains(keyCode);
  }

  public static boolean isKeyPressed(int keyCode) {
    return keysPressed.contains(keyCode);
  }

  public static void clearKeys(){
    keysPressed.clear();
  }

  public static boolean isAnyKeyPressed() {
    return !keysDown.isEmpty();
  }

  public static boolean isLeftMouseButtonDown() {
    return mouseButtonPressed == MouseEvent.BUTTON1;
  }

  public static boolean isRightMouseButtonDown() {
    return mouseButtonPressed == MouseEvent.BUTTON3;
  }

  public static boolean isMiddleMouseButtonDown() {
    return mouseButtonPressed == MouseEvent.BUTTON2;
  }

  public static Point getMousePos() {
    return mousePos;
  }

  public static void clearState() {
    keysDown.clear();
    mouseButtonPressed = 0;
  }
}
