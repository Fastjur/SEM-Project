package net.liquidpineapple.pang.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.gui.LifeSystem;

import java.awt.event.KeyEvent;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends GameObject {

    private static final String textureLocation = "/sprites/player/p1_front.png";
    public static boolean isHit = false;

    private enum PlayerMovement {
        LEFT_DIRECTION(-4/5.0),
        RIGHT_DIRECTION(4/5.0),
        NO_MOVEMENT(0);

        private final double dx;
        PlayerMovement(double dx) {
            this.dx = dx;
        }
    }
    private double dx;

    public Player(double startX, double startY) {
        super(textureLocation, startX, startY);
    }

    public void move() {
        xPos += dx;
        if (xPos < 1) {
            xPos = 1;
        }
        int boardWidth = Application.getBoard().getWidth();
        double playerMaxPosX = boardWidth - this.getWidth();
        if (xPos > playerMaxPosX) {
            xPos = playerMaxPosX;
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
            if(!isHit) {
                LifeSystem.loseLife();
                isHit = true;
            }
        }
    }

    public boolean collisionPlayer(){
        for(GameObject object : Application.getBoard().getCurrentScreen().objectList){
            if(object instanceof Ball){
                Ball ball = (Ball) object;

                double playerPos = this.getXPos() + (this.getImage().getWidth(null))/2;

                if(playerPos - ball.getXPos() >= 0 && playerPos - ball.getXPos() <= ball.getWidth()) {
                    if(ball.getYPos() + ball.getHeight() >= this.getYPos()) {
                       return true;
                    }
                }
            }
        }
        isHit = false;
        return false;
    }
}
