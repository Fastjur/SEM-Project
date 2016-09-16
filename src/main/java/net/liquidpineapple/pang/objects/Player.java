package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;

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
    private int dx;

    public Player(int startX, int startY) {
        super(textureLocation, startX, startY);
    }

    public void move() {
        xPos += dx;
        if (xPos < 1) {
            xPos = 1;
        }
        int boardWidth = Application.getBoard().getWidth();
        int playerMaxPosX = boardWidth - this.getWidth();
        if (xPos > playerMaxPosX) {
            xPos = playerMaxPosX;
        }
    }

    @Override
    public void doUpdate() {
        super.doUpdate();

        if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
            dx = PlayerMovement.LEFT_DIRECTION.dx;
            move();
        } else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
            dx = PlayerMovement.RIGHT_DIRECTION.dx;
            move();
        } else {
            dx = PlayerMovement.NO_MOVEMENT.dx;
        }
    }
}
