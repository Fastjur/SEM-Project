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
     * 
     * @param startX
     * @param startY
     */
    public void rollRandomdrop(double startX, double startY) {
        int roll = randomizer.nextInt(500);
        Drop testdrop = null;
        if(0 <= roll && roll < 150){
            testdrop = new Drop("/sprites/drops/coinBronze.png", startX, startY, 10, 0, 1);
        }
        else if(150 <= roll && roll <250 ){
            testdrop = new Drop("/sprites/drops/coinSilver.png", startX, startY, 100, 0, 1);
        }
        else if(250 <= roll && roll <300 ){
            testdrop = new Drop("/sprites/drops/coinGold.png", startX, startY, 1000, 0, 1);
        }
        else if(300 <= roll && roll <310 ){
            testdrop = new Drop("/sprites/drops/gemBlue.png", startX, startY, 1500, 0, 1);
        }
        else if(310 <= roll && roll <320 ){
            testdrop = new Drop("/sprites/drops/gemRed.png", startX, startY, 1500, 0, 1);
        }
        else if(320 <= roll && roll <330 ){
            testdrop = new Drop("/sprites/drops/gemYellow.png", startX, startY, 1500, 0, 1);
        }
        else if(330 <= roll && roll <340 ){
            testdrop = new Drop("/sprites/drops/gemGreen.png", startX, startY, 1500, 0, 1);
        }
        if(roll<340){
            Application.getBoard().addObject(testdrop);
        }

    }
}
