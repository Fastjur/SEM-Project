package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.gui.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
public class Player extends GameObject {

    private static final String textureLocation = "/sprites/player/p1_front.png";

    protected int dx;
    protected int dy;

    public Player(int startX, int startY) {
        super(textureLocation, startX, startY);
    }

    public void move() {
        xPos += dx;
        yPos += dy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_LEFT:
                dx = -1; break;
            case KeyEvent.VK_RIGHT:
                dx = 1; break;
            case KeyEvent.VK_DOWN:
                dy = -1; break;
            case KeyEvent.VK_UP:
                dy = 1; break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_LEFT:
                dx = 0; break;
            case KeyEvent.VK_RIGHT:
                dx = 0; break;
            case KeyEvent.VK_DOWN:
                dy = 0; break;
            case KeyEvent.VK_UP:
                dy = 0; break;
        }
    }
}
