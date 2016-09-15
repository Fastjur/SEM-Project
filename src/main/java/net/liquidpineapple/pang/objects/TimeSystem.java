package net.liquidpineapple.pang.objects;

import lombok.Getter;

import java.util.ArrayList;

/**
 * Created by Erik on 12-9-2016.
 */
public class TimeSystem {

    private static int score;


    @Getter
    private static ArrayList<ScoreToken> Places;


    public TimeSystem() {
        score = 0;
        //These are from right to left.
        Places = new ArrayList<>(9);
        Places.add(new ScoreToken(261,5));
        Places.add(new ScoreToken(229,5));
        Places.add(new ScoreToken(197,5));
        Places.add(new ScoreToken(165,5));
        Places.add(new ScoreToken(133,5));
        Places.add(new ScoreToken(101,5));
        Places.add(new ScoreToken(69,5));
        Places.add(new ScoreToken(37,5));
        Places.add(new ScoreToken(5,5));
        displayscore();
    }


    public static void AddScore(int scoreIn) {
        if(score != 999999999){
            score += scoreIn;
            if(score > 999999999){score = 999999999;}
        }
    }

    public void displayscore() {
        int calcscore = score;
        int i = 0;
        while(calcscore>0){
            Places.get(i).SetScoreToken(calcscore % 10);
            calcscore /= 10;
            i++;
        }
    }

    public int getScore(){
        return score;
    }
}
