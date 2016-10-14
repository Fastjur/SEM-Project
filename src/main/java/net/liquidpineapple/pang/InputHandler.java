package net.liquidpineapple.pang;

import lombok.Getter;

import net.liquidpineapple.pang.logger.Logger;

import java.awt.Point;
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

  private static HashSet<Integer> keysPressed = new HashSet<>();
  @Getter
  private static HashSet<Integer> keysDown = new HashSet<>();
  private static Point mousePos;
  private static int mouseButtonPressed;

  public InputHandler() {
  }


  @Override
  public void keyPressed(KeyEvent event) {
    keysPressed.add(event.getKeyCode());
    if (!keysDown.contains(event.getKeyCode())) {
      keysDown.add(event.getKeyCode());
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {
    keysPressed.remove(event.getKeyCode());
    keysDown.remove(event.getKeyCode());
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

  public static boolean isKeyPressed(int keyCode) {
    return keysPressed.contains(keyCode);
  }

  public static boolean isKeyDown(int keyCode) {
    return keysDown.contains(keyCode);
  }

  public static boolean isAnyKeyPressed() {
    return !keysPressed.isEmpty();
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
    keysPressed.clear();
    mouseButtonPressed = 0;
  }
}
