package net.liquidpineapple.pang.gui;

import lombok.Getter;
import net.liquidpineapple.pang.Application;
import java.util.ArrayList;

/**
 * Class that represents the ScoreSystem.
 * Created by Erik on 12-9-2016.
 */
public class ScoreSystem {

    private static int score;
    @Getter
    private static ArrayList<NumberToken> Places;

    /**
     * The constructor for the scoresystem.
     */
    public ScoreSystem() {
        score = 0;
        //These are from right to left.
        Places = new ArrayList<>(9);
        Places.add(new NumberToken(261,5));
        Places.add(new NumberToken(229,5));
        Places.add(new NumberToken(197,5));
        Places.add(new NumberToken(165,5));
        Places.add(new NumberToken(133,5));
        Places.add(new NumberToken(101,5));
        Places.add(new NumberToken(69,5));
        Places.add(new NumberToken(37,5));
        Places.add(new NumberToken(5,5));
        displayscore();
    }

    public static void addScore(int scoreIn) {
        score += scoreIn;
        if (score > 999999999) {
            score = 999999999;
        }
    }

    /**
     * Method that makes the current score displayed on the screen correctly.
     */
    public void displayscore() {
        int calcscore = score;
        int i = 0;
        while(calcscore>0){
            Places.get(i).SetScoreToken(calcscore % 10);
            calcscore /= 10;
            i++;
        }
    }

    /**
     * Getter for the current score.
     * @return the current score.
     */
    public int getScore(){
        return score;
    }

    /**
     * Resets the score when the player died.
     */
    public void resetScore(){
        Application.setScoreKeeper(new ScoreSystem());
    }
}
