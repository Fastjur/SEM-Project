package net.liquidpineapple.pang.objects;

import lombok.Getter;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import net.liquidpineapple.pang.objects.powerups.BombDrop;
import net.liquidpineapple.pang.objects.powerups.FreezeTimeDrop;
import net.liquidpineapple.pang.objects.powerups.HeartDrop;
import net.liquidpineapple.pang.objects.powerups.HookDrop;
import net.liquidpineapple.pang.objects.powerups.ShieldDrop;
import net.liquidpineapple.pang.objects.powerups.StickyHookDrop;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents the drop randomizer.
 */
public class DropRandomizer {

  private Random randomizer;
  private static final int BRONZE_NUM = 20;
  private static final int SILVER_NUM = 15;
  private static final int GOLD_NUM = 10;
  private static final int GEM_NUM = 8;
  private static final int HEART_NUM = 5;
  private static final int HOOK_NUM = 5;
  private static final int SHIELD_NUM = 5;
  private static final int BOMB_NUM = 5;
  private static final int FROZEN_NUM = 5;
  private static final int STICKY_NUM = 5;
  private static final int TOTAL_CHANCE = 100;

  private ArrayList<Drop> randomList;
  private static final int TOTAL_GEMS = 4;
  //Whatever you do, don't put this to 0
  // Should be 4 unless you add different types of gems.

  @Getter
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  private static DropRandomizer instance = new DropRandomizer();


  /**
   * Singleton constructor for DropRandomizer.
   */
  private DropRandomizer() {
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
    for (int i = 0; i < (GEM_NUM / TOTAL_GEMS); i++) {
      randomList.add(new Drop("/sprites/drops/gemBlue.png", 0, 0, 0, 1, 1500));
      randomList.add(new Drop("/sprites/drops/gemRed.png", 0, 0, 0, 1, 1500));
      randomList.add(new Drop("/sprites/drops/gemYellow.png", 0, 0, 0, 1, 1500));
      randomList.add(new Drop("/sprites/drops/gemGreen.png", 0, 0, 0, 1, 1500));
    }
    for (int i = 0; i < HEART_NUM; i++) {
      randomList.add(new HeartDrop("/sprites/drops/heart.png", 0, 0, 0, 1, 1, 1));
    }
    for (int i = 0; i < HOOK_NUM; i++) {
      randomList.add(new HookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 3));
    }
    for (int i = 0; i < SHIELD_NUM; i++) {
      randomList.add(new ShieldDrop("/sprites/drops/shieldBronze.png", 0, 0, 0, 1, 1, 1));
    }
    for (int i = 0; i < BOMB_NUM; i++) {
      randomList.add(new BombDrop("/sprites/drops/bomb.png", 0, 0, 0, 1, 1));
    }
    for (int i = 0; i < FROZEN_NUM; i++) {
      randomList.add(new FreezeTimeDrop("/sprites/drops/freezeIcon.png", 0, 0, 0, 1, 1, 5));
    }
    for (int i = 0; i < STICKY_NUM; i++) {
      randomList.add(new StickyHookDrop("/sprites/drops/chain.png", 0, 0, 0, 1, 1, 2));
    }
  }

  /**
   * Rolls for a drop and if it does roll positive creates that drop at startX startY.
   *
   * @param startX Start position X
   * @param startY Start position Y
   */
  public void rollRandomdrop(double startX, double startY) {

    int length = randomList.size();
    int roll = randomizer.nextInt(TOTAL_CHANCE);

    if (roll < length) {
      Drop randomDrop = randomList.get(roll);
      randomDrop.setPos(startX, startY);
      Application.getBoard().addObject(randomDrop);
    }
  }
}
