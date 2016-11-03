package net.liquidpineapple.pang.runnables;

import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.logger.Logger;

/**
 * The runnable for the draw loop of the {@link net.liquidpineapple.pang.Application}.
 * @author Jurriaan Den Toonder Created on 25-10-16
 */
public class DrawRunnable implements Runnable {

  // Class constants
  private static final int DRAW_DELAY = 5;

  // Class fields
  private Board board;

  // Thread running boolean
  private volatile boolean running = true;

  public DrawRunnable(Board board) {
    this.board = board;
  }

  @Override
  public void run() {
    long beforeTime;
    long timeDiff;
    long sleep;

    beforeTime = System.currentTimeMillis();
    Logger.info("Draw loop is running");

    while (running) {
      timeDiff = System.currentTimeMillis() - beforeTime;
      sleep = DRAW_DELAY - timeDiff;

      if (sleep < 0) {
        sleep = 2;
      }

      try {
        board.revalidate();
        board.repaint();
        beforeTime = System.currentTimeMillis();
        Thread.sleep(sleep);
      } catch (InterruptedException ex) {
        Logger.info("Interrupted: " + ex.getMessage());
        running = false;
      }
    }
    Logger.info("Draw Runnable stopped");
  }

  public void terminate() {
    running = false;
  }
}
