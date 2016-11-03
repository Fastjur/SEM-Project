package net.liquidpineapple.pang.runnables;

import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.logger.Logger;

/**
 * The runnable for the update loop of the {@link net.liquidpineapple.pang.Application}.
 * @author Jurriaan Den Toonder Created on 25-10-16
 */
public class UpdateRunnable implements Runnable {

  // Class constants
  private static final int UPDATE_DELAY = 10;

  // Class fields
  private Board board;
  private ScoreSystem scoreSystem;
  private LifeSystem lifeSystem;

  // Thread running boolean
  private volatile boolean running = true;

  /**
   * Constructor for the {@link UpdateRunnable}.
   * @param board The {@link Board}
   * @param scoreSystem The {@link ScoreSystem}
   * @param lifeSystem The {@link LifeSystem}
   */
  public UpdateRunnable(Board board, ScoreSystem scoreSystem, LifeSystem lifeSystem) {
    this.board = board;
    this.scoreSystem = scoreSystem;
    this.lifeSystem = lifeSystem;
  }

  @Override
  public void run() {
    long beforeTime;
    long timeDiff;
    long sleep;

    beforeTime = System.currentTimeMillis();
    Logger.info("Update loop is running");

    while (running) {
      timeDiff = System.currentTimeMillis() - beforeTime;
      sleep = UPDATE_DELAY - timeDiff;

      if (sleep < 0) {
        sleep = 2;
      }

      board.getCurrentScreen().doUpdate();
      scoreSystem.displayscore();
      lifeSystem.updateLifes();

      beforeTime = System.currentTimeMillis();

      try {
        Thread.sleep(sleep);
      } catch (InterruptedException exception) {
        Logger.info("Interrupted: " + exception.getMessage());
        running = false;
      }
    }
    Logger.info("Update Runnable stopped");
  }

  public void terminate() {
    this.running = false;
  }
}
