package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropRandomizer {

    private Random randomizer;
    private final int bronzeNum = 30;
    private final int silverNum = 20;
    private final int goldNum = 10;
    private final int gemNum = 8;
    private final int heartNum = 2;
    private final int nullNum = 100-heartNum-gemNum-goldNum-silverNum-bronzeNum;

    private ArrayList<Drop> randomList;


    public DropRandomizer(){
        Logger.info("Randomizer starting.");
        randomList = new ArrayList<>();
        randomizer = new Random();

        for(int i=0 ;i<bronzeNum; i++){
            randomList.add(new Drop("/sprites/drops/coinBronze.png", 0, 0, 0, 1, 10));
        }
        for(int i=0 ;i<silverNum; i++){
            randomList.add(new Drop("/sprites/drops/coinSilver.png", 0, 0, 0, 1, 100));
        }
        for(int i=0 ;i<goldNum; i++){
            randomList.add(new Drop("/sprites/drops/coinGold.png", 0, 0, 0, 1, 1000));
        }
        for(int i=0 ;i<(gemNum/4); i++) {
            randomList.add(new Drop("/sprites/drops/gemBlue.png", 0, 0, 0, 1, 1500));
            randomList.add(new Drop("/sprites/drops/gemRed.png", 0, 0, 0, 1, 1500));
            randomList.add(new Drop("/sprites/drops/gemYellow.png", 0, 0, 0, 1, 1500));
            randomList.add(new Drop("/sprites/drops/gemGreen.png", 0, 0, 0, 1, 1500));
        }
        for (int i = 0; i < heartNum; i++) {
            randomList.add(new Drop("/sprites/drops/heart.png", 0, 0, 0, 1, 1, 1));
        }
        for (int i = 0; i < nullNum; i++) {
            randomList.add(null);
        }
    }

    /**
     * Rolls for a drop and if it does roll positive creates that drop at startX startY.
     *
     * Currently set up to:
     * 30% Bronze coin 10p
     * 20% Silver coin 100p
     * 10% Gold coin 1000p
     * 8% 1 of 4 colored Gems 1500p
     * 2% A heart that gives a life and 1p
     *
     * @param startX Start position X
     * @param startY Start position Y
     */
    public void rollRandomdrop(double startX, double startY) {

        int length = randomList.size();
        int roll = randomizer.nextInt(length);
        Drop randomDrop = randomList.get(roll);

        if( randomDrop  != null){
            randomDrop.setPos(startX, startY);
            Application.getBoard().addObject( randomDrop );
        }
    }
}
