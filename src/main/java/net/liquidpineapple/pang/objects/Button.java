package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.InputHandler;

import java.awt.*;

/**
 * @author Govert de Gans
 * @date 2016-09-08
 */
@Slf4j
public abstract class Button extends GameObject {
    public Button(String textureLocation, int startX, int startY) {
        super(textureLocation, startX, startY);
    }

    private boolean clicked = false;
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

    public void onClick() {

    }
}
