package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the game over screen.
 * @author Jaap-Jan
 * @date 21-9-2016.
 */
public class GameOverScreen extends Screen {
    private static ArrayList<NumberToken> places;
    /**
     * Constructor for the game over screen.
     */
    public GameOverScreen() {
        try {
            backgroundImage = ImageIO.read(Level.class.getResource("/sprites/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //These are from right to left.
        places = new ArrayList<>(9);
        int numberPosX = 524;
        int numberPosY = 300;
        int numberOfTokens = 9;
        for(int i = 0; i<numberOfTokens; i++) {
            NumberToken token = new NumberToken(numberPosX, numberPosY);
            places.add(token);
            objectList.add(token);
            numberPosX -= 32;
        }
        displayscore();
    }

    /**
     * Method that updates the game over screen.
     */
    @Override
    public void doUpdate() {
        super.doUpdate();
        if(InputHandler.isAnyKeyPressed()){
                Application.lifeKeeper.resetLives();
                Application.getScoreKeeper().resetScore();
                Application.getBoard().changeScreen(new MainMenu());
        }
    }

    /**
     * Method that makes the score display on the screen.
     */
    public void displayscore() {
        int calcscore = ScoreSystem.getScore();
        int i = 0;

        while(calcscore>0){
            places.get(i).SetScoreToken(calcscore % 10);
            calcscore /= 10;
            i++;
        }
        doUpdate();
    }

}
