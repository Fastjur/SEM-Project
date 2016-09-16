package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.InputHandler;

import java.awt.*;

/**
 * Super class for every button in the game.
 * @author Govert de Gans
 * @date 2016-09-08
 */
@Slf4j
public abstract class Button extends GameObject {

    private boolean clicked = false;

    /**
     * Constructor of a button.
     * @param textureLocation - The storage location of the texture.
     * @param startX - The X position where the image should be on the screen.
     * @param startY - The Y position where the image should be on the screen.
     */
    public Button(String textureLocation, int startX, int startY) {
        super(textureLocation, startX, startY);
    }

    /**
     * Method that updates the buttons.
     */
    @Override
    public void doUpdate() {

        if (InputHandler.isLeftMouseButtonDown()) {
                Point mousePos = InputHandler.getMousePos();
                if (this.getBounds().contains(mousePos)) {
                    if (!clicked) {
                        clicked = true;
                        log.info("contains");
                        this.onClick();
                    }
                } else {
                    clicked = false;
                }
        }
        else{
            clicked = false;
        }
    }

    /**
     * Super method that checks for clicks.
     */
    public void onClick() {

    }
}
