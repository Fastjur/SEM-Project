package net.liquidpineapple.pang.gui;

import lombok.Getter;
import lombok.Setter;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import java.util.ArrayList;


/**
 * Class that represents the ScoreSystem.
 * Created by Erik on 12-9-2016.
 */
public class ScoreSystem {

  @Getter
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  private static ScoreSystem instance = new ScoreSystem();

  @Getter
  @Setter
  private static int score;

  @Getter
  private static ArrayList<NumberToken> Places;

  /**
   * The singleton constructor for the ScoreSystem.
   */
  private ScoreSystem() {
    score = 0;
    Logger.info("ScoreSystem starting.");
    //These are from right to left.
    Places = new ArrayList<>(9);
    Places.add(new NumberToken(261, 5));
    Places.add(new NumberToken(229, 5));
    Places.add(new NumberToken(197, 5));
    Places.add(new NumberToken(165, 5));
    Places.add(new NumberToken(133, 5));
    Places.add(new NumberToken(101, 5));
    Places.add(new NumberToken(69, 5));
    Places.add(new NumberToken(37, 5));
    Places.add(new NumberToken(5, 5));
    displayscore();
  }

  /**
   * method to add a value to the score.
   *
   * @param scoreIn - number to be added to the score.
   */
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
    int increment = 0;
    while (calcscore > 0) {
      Places.get(increment).setScoreToken(calcscore % 10);
      calcscore /= 10;
      increment++;
    }
  }

  /**
   * Resets the score when the player died.
   */
  public void resetScore() {
    if (Places.size() == 0) {
      Application.setScoreKeeper(new ScoreSystem());
    } else {
      for (NumberToken token : Places) {
        token.setScoreToken(0);
      }
      setScore(0);
    }
  }
}
