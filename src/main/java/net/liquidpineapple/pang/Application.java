package net.liquidpineapple.pang;

import lombok.Getter;
import lombok.Setter;

import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.logger.LoggerTypes;
import net.liquidpineapple.pang.objects.DropRandomizer;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The main application, extends {@link JFrame}.
 *
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class Application extends JFrame {

  private static final int UPDATE_DELAY = 10;
  private static final int DRAW_DELAY = 5;

  // Set to true to prevent loosing lives
  public static final boolean cheatMode = false;

  public static boolean multiplayer;
  public static HashMap<String, Image> imageCache = new HashMap<>();
  public static LifeSystem lifeSystem;

  @Getter
  private static Board board;

  @Getter
  @Setter
  private static ScoreSystem scoreKeeper;

  private PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();

  /**
   * Constructor for the Application.
   *
   * @throws IOException Thrown when properties file can not be read
   */
  public Application() throws IOException {
    super();
    Logger.info("Starting application...");
  }

  /**
   * Starts the application.
   *
   * @throws IOException Thrown when resource file containing properties can not be read
   */
  public void start() throws IOException {
    Integer width = Integer.valueOf(propertiesHandler.getProperty("application-width"));
    Integer height = Integer.valueOf(propertiesHandler.getProperty("application-height"));

    String name = propertiesHandler.getProperty("application-name");
    setTitle(name);

    setResizable(false);
    setSize(width, height);

    scoreKeeper = ScoreSystem.getInstance();
    lifeSystem = LifeSystem.getInstance();
    DropRandomizer.getInstance();
    board = new Board();
    add(board);
    Logger.info("Initialized with width: " + width + " and height: " + height);

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setVisible(true);

    Logger.info("Application started successfully!");
    //TimeSystem.getInstance();

    Runnable doUpdateRunnable = () -> {
      long beforeTime;
      long timeDiff;
      long sleep;

      beforeTime = System.currentTimeMillis();
      Logger.info("Update loop is running");

      while (true) {
        timeDiff = System.currentTimeMillis() - beforeTime;
        sleep = UPDATE_DELAY - timeDiff;

        if (sleep < 0) {
          sleep = 2;
        }

        try {
          board.getCurrentScreen().doUpdate();
          scoreKeeper.displayscore();
          lifeSystem.updateLifes();

          beforeTime = System.currentTimeMillis();
          Thread.sleep(sleep);
        } catch (InterruptedException exception) {
          Logger.info("Interrupted: " + exception.getMessage());
        }
      }
    };

    Runnable doDrawRunnable = () -> {
      long beforeTime;
      long timeDiff;
      long sleep;

      beforeTime = System.currentTimeMillis();
      Logger.info("Draw loop is running");

      while (true) {
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

        }
      }
    };

    new Thread(doUpdateRunnable).start();
    new Thread(doDrawRunnable).start();

    AudioSystem.start();
    AudioSystem.changeLoopingSound("/sounds/bg.mp3");
  }

  /**
   * Main method of the application.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    LoggerTypes type = LoggerTypes.INFO;
    if (args.length > 0) {
      String loggerLevel = args[0].toUpperCase();
      try {
        type = LoggerTypes.valueOf(loggerLevel);
      } catch (IllegalArgumentException ex) {
        Logger.warning("Invalid first command line argument: " + loggerLevel);
        Logger.warning("Valid arguments are:");
        for (LoggerTypes loggerType : LoggerTypes.values()) {
          Logger.warning("  " + loggerType);
        }
      }
    }
    Logger.setLevel(type);

    EventQueue.invokeLater(() -> {
      Application app = null;
      try {
        app = new Application();
        app.start();
      } catch (IOException ex) {
        Logger.error(ex.getMessage(), ex);
      }
    });
  }

  /**
   * Closes the application.
   */
  public void close() {
    Logger.info("Shutting down. Goodbye!");
    AudioSystem.stop();
    dispose();
  }
}
