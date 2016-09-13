package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.screens.Screen;

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
        if (xPos < 1) {
            xPos = 1;
        }
        if (xPos > maxX) {
            xPos = maxX;
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
        } else if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
            boolean hookInUse = false;
            for(GameObject o : Application.getBoard().getCurrentScreen().objectList){
                if(o instanceof HookAndRope){
                    hookInUse = true;
                }
            }
            if(!hookInUse){
                HookAndRope newRope = new HookAndRope(getXPos(), 0);
                Application.getBoard().addObject(newRope);
            }
        } else {
            dx = PlayerMovement.NO_MOVEMENT.dx;
        }
    }
}
