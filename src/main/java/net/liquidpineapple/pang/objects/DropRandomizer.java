package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;

import java.util.Random;

/**
 * Created by Erik on 22-9-2016.
 */
public class DropRandomizer {

    private Random randomizer;

    public DropRandomizer(){
        randomizer = new Random();
    }

    /**
     * Rolls for an drop and if it does roll postive creates that drop at startX startY.
     *
     * Currently set up to:
     * 30% Bronze coin 10p
     * 20% Silver coin 100p
     * 10% Gold coin 1000p
     * 8% 1 of 4 colored Gems 1500p
     * 2% A heart that gives a life and 1p
     *
     * @param startX
     * @param startY
     */
    public void rollRandomdrop(double startX, double startY) {
        int roll = randomizer.nextInt(100);
        Drop testdrop = null;
        if(0 <= roll && roll < 30){
            testdrop = new Drop("/sprites/drops/coinBronze.png", startX, startY, 0, 1, 10);
        }
        else if(30 <= roll && roll <50 ){
            testdrop = new Drop("/sprites/drops/coinSilver.png", startX, startY, 0, 1, 100);
        }
        else if(50 <= roll && roll <60 ){
            testdrop = new Drop("/sprites/drops/coinGold.png", startX, startY, 0, 1, 1000);
        }
        else if(60 <= roll && roll <62 ){
            testdrop = new Drop("/sprites/drops/gemBlue.png", startX, startY, 0, 1, 1500);
        }
        else if(62 <= roll && roll <64 ){
            testdrop = new Drop("/sprites/drops/gemRed.png", startX, startY, 0, 1, 1500);
        }
        else if(64 <= roll && roll <66 ){
            testdrop = new Drop("/sprites/drops/gemYellow.png", startX, startY, 0, 1, 1500);
        }
        else if(66 <= roll && roll <68 ) {
            testdrop = new Drop("/sprites/drops/gemGreen.png", startX, startY, 0, 1, 1500);
        }
        else if(68 <= roll && roll <70 ) {
            testdrop = new Drop("/sprites/drops/heart.png", startX, startY, 0, 1, 1, 1);
        }
        if(testdrop != null){
            Application.getBoard().addObject(testdrop);
        }

    }
}
