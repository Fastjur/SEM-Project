package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.screens.MainMenu;
import lombok.Getter;

/**
 * Class that represents the lifeSystem.
 * Created by Erik on 13-9-2016.
 */
public class LifeSystem extends GameObject{

    private static final String textureH0 = "/sprites/hearts0.png";
    private static final String textureH1 = "/sprites/hearts1.png";
    private static final String textureH2 = "/sprites/hearts2.png";
    private static final String textureH3 = "/sprites/hearts3.png";
    @Getter
    private static int lives;
    private int lastLives;

    /**
     * Constructor for the life system.
     */
    public LifeSystem(){
        super(textureH3,636,5);
        lives = 3;
    }

    /**
     * Method that decreases the lives of the player when the player gets hit.
     */
    public static void loseLife(){
        if(lives>0) {
            lives -= 1;
        }

    }

    /**
     * Method that updates the shown lives on the screen.
     */
    public void updateLifes() {
        if(lastLives!=lives) {
            switch (lives) {
                case 3:
                    changeImage(textureH3);
                    break;
                case 2:
                    changeImage(textureH2);
                    break;
                case 1:
                    changeImage(textureH1);
                    break;
                case 0:
                    changeImage(textureH0);
                    Application.getBoard().changeScreen(new MainMenu());
                    break;
            }
            lastLives = lives;
        }
    }

    /**
     * Method that increases the life with 1 if needed.
     */
    public static void gainLife() {
        if (lives < 3) {
            lives += 1;
        }
    }

    /**
     * Method that resets the lives if the player gets hit.
     */
    public void resetLives() { lives = 3; }
}
