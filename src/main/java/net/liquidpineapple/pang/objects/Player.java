package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;

import java.awt.event.KeyEvent;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
@Slf4j
public class Player extends GameObject {

    private static final String textureLocation = "/sprites/player/p1_front.png";
    private final int maxX;
    private int dx;

    public Player(int startX, int startY, int maxX) {
        super(textureLocation, startX, startY);
        this.maxX = maxX;
    }

    public void move() {
        xPos += dx;
        if (xPos < 1) {
            xPos = 1;
        }
        if (xPos > maxX) {
            xPos = maxX;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_LEFT:
                dx = -1; break;
            case KeyEvent.VK_RIGHT:
                dx = 1; break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_LEFT:
                dx = 0; break;
            case KeyEvent.VK_RIGHT:
                dx = 0; break;
        }
    }
}
