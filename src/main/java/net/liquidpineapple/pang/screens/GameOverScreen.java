package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.objects.NumberToken;


import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the Controls screen.
 * @author Jaap-Jan
 * @date 21-9-2016.
 */
@Slf4j
public class GameOverScreen extends Screen {
    private static ArrayList<NumberToken> Places;
    /**
     * Constructor for the ControlsScreen.
     */
    public GameOverScreen() {
        try {
            backgroundImage = ImageIO.read(Level.class.getResource("/sprites/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //These are from right to left.
        Places = new ArrayList<>(9);
        NumberToken nine = new NumberToken(524,300);
        NumberToken eight = new NumberToken(492,300);
        NumberToken seven = new NumberToken(460,300);
        NumberToken six = new NumberToken(428,300);
        NumberToken five = new NumberToken(396,300);
        NumberToken four = new NumberToken(364,300);
        NumberToken three = new NumberToken(332,300);
        NumberToken two = new NumberToken(300,300);
        NumberToken one = new NumberToken(268,300);
        Places.add(nine);
        Places.add(eight);
        Places.add(seven);
        Places.add(six);
        Places.add(five);
        Places.add(four);
        Places.add(three);
        Places.add(two);
        Places.add(one);
        objectList.add(nine);
        objectList.add(eight);
        objectList.add(seven);
        objectList.add(six);
        objectList.add(five);
        objectList.add(four);
        objectList.add(three);
        objectList.add(two);
        objectList.add(one);
        displayscore();
    }

    /**
     * Method that updates the controlsScreen.
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

    public void displayscore() {
        int calcscore = Application.getScoreKeeper().getScore();
        int i = 0;

        while(calcscore>0){
            Places.get(i).SetScoreToken(calcscore % 10);
            calcscore /= 10;
            i++;
        }
        doUpdate();
    }

}
