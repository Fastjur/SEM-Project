package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.KeyEvent;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class Player extends GameObject {

    private static final String textureLocation = "/sprites/player/p1_front.png";

    private enum PlayerMovement {
        LEFT_DIRECTION(-1),
        RIGHT_DIRECTION(1),
        NO_MOVEMENT(0);

        private final int dx;
        PlayerMovement(int dx) {
            this.dx = dx;
        }
    }
    private final int maxX;
    private int dx;

    public Player(int startX, int startY, int maxX) {
        super(textureLocation, startX, startY);
        this.maxX = maxX;
    }

    public void move() {
        xPos += dx;
        if (xPos < PlayerMovement.LEFT_DIRECTION.dx) {
            xPos = PlayerMovement.LEFT_DIRECTION.dx;
        }
        if (xPos > maxX) {
            xPos = maxX;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_LEFT:
                dx = PlayerMovement.LEFT_DIRECTION.dx; break;
            case KeyEvent.VK_RIGHT:
                dx = PlayerMovement.RIGHT_DIRECTION.dx; break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch(key) {
            case KeyEvent.VK_LEFT:
                dx = PlayerMovement.NO_MOVEMENT.dx; break;
            case KeyEvent.VK_RIGHT:
                dx = PlayerMovement.NO_MOVEMENT.dx; break;
        }
    }
}
