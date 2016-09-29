package net.liquidpineapple.pang.gui;

import lombok.Getter;
import lombok.Setter;
import net.liquidpineapple.pang.logger.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;




/**
 * Created by Erik on 12-9-2016.
 */
public class TimeSystem {

  private static int time;
  private static Timer interval;
  @Getter
  private static int frozen = 0;

  @Getter
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  private static TimeSystem instance = new TimeSystem();

  @Getter
  private static ArrayList<NumberToken> timePlaces;

  private ActionListener timerAction = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent ae) {
      if (time > 0 && frozen == 0) {
        time -= 1;
      } else {
        frozen -= 1;
      }
      //Here the proper behaviour when time is run out should be replaced,
      //for now it will add 60 seconds and lose a life.
      if (time == 0) {
        LifeSystem.loseLife();
        time = 60;
      }
      updatetime();
    }
  };

  /**
   * Singleton constructor.
   */
  private TimeSystem() {
    Logger.info("TimeSystem Starting.");
    interval = new Timer(1000, timerAction);
    interval.setRepeats(true);
  }

  /**
   * Method to reset the time to a certain number.
   * @param inTime - time to be reset to.
   */
  public static void resetTime(int inTime) {
    time = inTime;
    frozen = 0;
    if (time > 999) {
      time = 999;
    }

    //These are from right to left.
    timePlaces = new ArrayList<>(3);
    timePlaces.add(new NumberToken(755, 52));
    timePlaces.add(new NumberToken(723, 52));
    timePlaces.add(new NumberToken(691, 52));
    updatetime();
    interval.start();
  }

  public static void setFrozen(int freezeTime) {
    frozen = freezeTime;
  }


  private static void updatetime() {
    int calctime = time;
    int incrementer = 0;
    while (incrementer < 3) {
      timePlaces.get(incrementer).setScoreToken(calctime % 10);
      calctime /= 10;
      incrementer++;
    }
  }

  /**
   * Stops the timer from ticking. The time will stay at the same value
   */
  public static void stop() {
    interval.stop();
  }

}
