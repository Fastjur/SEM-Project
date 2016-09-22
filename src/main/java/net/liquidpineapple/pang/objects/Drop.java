package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;

/**
 * Created by Erik on 22-9-2016.
 */
public class Drop extends GameObject {

    private int score;
    private double movY;
    private double movX;
    private int livesChange;

    /**
     * Creates a new drop.
     *
     * @param textureLocation as image
     * @param startX          as start position X
     * @param startY          as start position Y
     * @param score           as score change when this pickup collides with player
     * @param movX            as vertical movement every update
     * @param movY            as horizontal movement every update
     */
    public Drop(String textureLocation, double startX, double startY, double movX, double movY, int score, int livesChange){
        super(textureLocation, startX, startY);
        this.score = score;
        this.movY = movY;
        this.movX = movX;
        this.livesChange = livesChange;
    }

    public Drop(String textureLocation, double startX, double startY, double movX, double movY, int score){
        super(textureLocation, startX, startY);
        this.score = score;
        this.movY = movY;
        this.movX = movX;
    }

    /**
     * Calculates and sets the next position the ball should be drawn in.
     */
    public void move(){
        xPos += movX;
        yPos += movY;
    }

    /**
     * Method that updates the drop.
     */
    @Override
    public void doUpdate() {
        super.doUpdate();

        move();
        if (yPos + this.getHeight() - 1 > Application.getBoard().getHeight())
            yPos = Application.getBoard().getHeight() - this.getHeight();
        if (xPos + this.getWidth() > Application.getBoard().getWidth())
            xPos = Application.getBoard().getWidth() - this.getWidth();
        if (xPos < 0)
            xPos = 0;

        if (yPos + this.getHeight() - 1 == Application.getBoard().getHeight()){
            movX = 0;
        }
        else  if (xPos == 0 || xPos + this.getWidth() == Application.getBoard().getWidth()) {
            movX = -movX;
        }
    }

    public void playerCollision() {
        ScoreSystem.addScore(score);
        while(livesChange>0){
            LifeSystem.gainLife();
            livesChange-=1;}
        while(livesChange<0){
            LifeSystem.loseLife();
            livesChange+=1;}
        Application.getBoard().getCurrentScreen().objectList.remove(this);
    }
}
