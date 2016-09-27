package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import java.util.ArrayList;
import java.util.Random;

public class DropRandomizer {

    private Random randomizer;
    private static final int BRONZE_NUM = 30;
    private static final int SILVER_NUM = 20;
    private static final int GOLD_NUM = 10;
    private static final int GEM_NUM = 8;
    private static final int TOTAL_GEMS = 4;
    private static final int HEART_NUM = 2;
    private static final int TOTAL_CHANCE = 100;

    private ArrayList<Drop> randomList;

    /**
     * Creates the DropRandomizer and sets the arraylist the Random Roll pulls it's result from.
     */
    public DropRandomizer() {
        Logger.info("Randomizer starting.");
        randomList = new ArrayList<>();
        randomizer = new Random();

        for (int i = 0; i < BRONZE_NUM; i++) {
            randomList.add(new Drop("/sprites/drops/coinBronze.png", 0, 0, 0, 1, 10));
        }
        for (int i = 0; i < SILVER_NUM; i++) {
            randomList.add(new Drop("/sprites/drops/coinSilver.png", 0, 0, 0, 1, 100));
        }
        for (int i = 0; i < GOLD_NUM; i++) {
            randomList.add(new Drop("/sprites/drops/coinGold.png", 0, 0, 0, 1, 1000));
        }
        for (int i = 0; i <(GEM_NUM / TOTAL_GEMS); i++) {
            randomList.add(new Drop("/sprites/drops/gemBlue.png", 0, 0, 0, 1, 1500));
            randomList.add(new Drop("/sprites/drops/gemRed.png", 0, 0, 0, 1, 1500));
            randomList.add(new Drop("/sprites/drops/gemYellow.png", 0, 0, 0, 1, 1500));
            randomList.add(new Drop("/sprites/drops/gemGreen.png", 0, 0, 0, 1, 1500));
        }
        for (int i = 0; i < HEART_NUM; i++) {
            randomList.add(new Drop("/sprites/drops/heart.png", 0, 0, 0, 1, 1, 1));
        }
    }

    /**
     * Rolls for a drop and if it does roll positive creates that drop at startX startY.
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
        int roll = randomizer.nextInt(TOTAL_CHANCE);
        Drop randomDrop = randomList.get(roll);

        if (roll < length) {
            randomDrop.setPos(startX, startY);
            Application.getBoard().addObject( randomDrop );
        }
    }
}
