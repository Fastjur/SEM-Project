package net.liquidpineapple.pang.objects;

import lombok.Getter;
import lombok.Setter;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;

import java.awt.event.KeyEvent;

/**
 * Created by Tim on 9-9-2016.
 * Class that represents the hook system in the game.
 */
public class HookAndRope extends GameObject{

    //TODO: remove white background for sprite
    private static final String textureLocation = "/sprites/beam.png";
    private final int maxY;
    private double dy = 3; //specify upspeed rope here.

    /**
     * Constructor for the HookAndRope class
     * @param startX - Xlocation where the hook should go up.
     * @param maxY - Maximum height the rope should go up to.
     */
    public HookAndRope(int startX, int maxY) {
        super(textureLocation, startX, 600);
        this.maxY = maxY;
    }

    /**
     * Method to move the hook upwards.
     */
    public void move(){
        yPos -= dy;

        //if yPos reaches 0, the top of the frame has been reached,
        // and yPos should go to boardHeight again
        if(yPos<=maxY){
            Application.getBoard().getCurrentScreen().objectList.remove(this);
        }
    }

    /**
     * method to update the position of the hook,
     * iff its in use.
     */
    public void doUpdate() {
        super.doUpdate();
        move();
    }

}
