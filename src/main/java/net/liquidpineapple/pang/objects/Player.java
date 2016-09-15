package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
    public static boolean isHit = false;

    private enum PlayerMovement {
        LEFT_DIRECTION(-4),
        RIGHT_DIRECTION(4),
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
            if(!InputHandler.isKeyPressed(KeyEvent.VK_D)) {
                dx = PlayerMovement.LEFT_DIRECTION.dx;
                move();
            }

        } else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
            dx = PlayerMovement.RIGHT_DIRECTION.dx;
            move();
        } else {
            dx = PlayerMovement.NO_MOVEMENT.dx;
        }

        if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
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
        }

        if(collisionPlayer()){
            if(!this.isHit) {
                Application.lifeKeeper.loseLife();
                this.isHit = true;
            }
        }
    }

    public boolean collisionPlayer(){
        boolean returnBool = false;
        for(GameObject object : Application.getBoard().getCurrentScreen().objectList){
            if(object instanceof Ball){
                Ball ball = (Ball) object;

                int playerPos = this.getXPos() + (this.getImage().getWidth(null))/2;

                if(playerPos - ball.getXPos() >= 0 && playerPos - ball.getXPos() <= ball.getWidth()) {
                    if(ball.getYPos()+ ball.getHeight() >= this.getYPos()){
                       return true;
                    }
                }
            }
        }
        this.isHit = false;
        return false;
    }
}
