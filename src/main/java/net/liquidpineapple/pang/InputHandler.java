package net.liquidpineapple.pang;

import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.gui.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Govert de Gans
 * @date 2016-09-12
 */
@Slf4j
public class InputHandler implements MouseListener, KeyListener{

    private static HashSet<Integer> keysPressed = new HashSet<>();
    private static Point mousePos;
    private static int mouseButtonPressed;

    public InputHandler() {}

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePos = new Point(e.getX(), e.getY());
        mouseButtonPressed = e.getButton();
        log.info("pang-1");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtonPressed = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static boolean isKeyPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    public static boolean isLeftMouseButtonDown() {
        return mouseButtonPressed == MouseEvent.BUTTON1;
    }

    public static boolean isRightMouseButtonDown() {
        return mouseButtonPressed == MouseEvent.BUTTON2;
    }

    public static boolean isMiddleMouseButtonDown() {
        return mouseButtonPressed == MouseEvent.BUTTON3;
    }

    public static Point getMousePos() {
        return mousePos;
    }

    public static void clearState() {
        keysPressed.clear();
        mouseButtonPressed = 0;
    }
}
