package net.liquidpineapple.pang.objects;

/**
 * Class that represents the object for the title in the main menu.
 * @author Jaap-Jan
 * @date 9-9-2016.
 */
public class TitlePang extends GameObject {
    /**
     * Constructor of the Title.
     * @param startX - The X location where the object should be on the screen.
     * @param startY - The Y location where the object should be on the screen.
     */
    public TitlePang(int startX, int startY) {
        super("/images/PANG.png", startX, startY);
    }
}
