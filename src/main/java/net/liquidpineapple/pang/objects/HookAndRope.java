package net.liquidpineapple.pang.objects;

/**
 * Created by Tim on 9-9-2016.
 * Class that represents the hook system in the game.
 */
public class HookAndRope extends GameObject{

    //TODO: remove white background for sprite
    private static final String textureLocation = "/sprites/hookandrope/combined.png";
    private final int maxY;
    private double dy = 1; //specify upspeed rope here.
    private boolean inUse = true;

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
        if(yPos<=0){
            yPos = 600;
            inUse = false;
        }
    }

    /**
     * method to update the position of the hook,
     * iff its in use.
     */
    public void doUpdate() {
        if(this.inUse) {
            move();
        }
    }

    /**
     * Get method to get the boolean inUse, indicating if the hook is in use.
     * @return boolean inUse;
     */
    public boolean isInUse() {
        return inUse;
    }


}
