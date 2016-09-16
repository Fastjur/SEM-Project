package net.liquidpineapple.pang.objects;

/**
 * Class that represents the object for the Controls Screen.
 * @author Jaap-Jan
 * @date 13-9-2016.
 */
public class ControlsObj extends GameObject {
    /**
     * Constructor of the Controls Object.
     * @param startX - The X location where the object should be on the screen.
     * @param startY - The Y location where the object should be on the screen.
     */
    public ControlsObj(int startX, int startY) {
        super("/images/controls.png", startX, startY);
    }
}
