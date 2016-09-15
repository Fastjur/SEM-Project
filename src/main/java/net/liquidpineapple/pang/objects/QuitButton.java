package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;

/**
 * Class that represents the object for the Quitbutton.
 * @author Jaap-Jan
 * @date 9-9-2016.
 */
@Slf4j
public class QuitButton extends Button{
    /**
     * Constructor of the Quitbutton.
     * @param startX - The X location where the button should be on the screen.
     * @param startY - The Y location where the button should be on the screen.
     */
    public QuitButton(int startX, int startY) {
        super("/images/quit.png", startX, startY);
    }

    /**
     * Method that exits the game when the button is clicked.
     */
    @Override
    public void onClick() {
        log.info("Close");
        System.exit(0);
    }
}
