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

    public void rollRandomdrop(double startX, double startY) {
        int roll = randomizer.nextInt(2);
        if(roll == 1){
            Drop testdrop = new Drop("/sprites/numbers/hud_0.png", startX, startY, 100, 2, 1);
            Application.getBoard().addObject(testdrop);
        }
    }
}
