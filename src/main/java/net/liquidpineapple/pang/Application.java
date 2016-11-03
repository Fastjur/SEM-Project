package net.liquidpineapple.pang;

import lombok.Getter;
import lombok.Setter;

import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.logger.LoggerTypes;
import net.liquidpineapple.pang.runnables.DrawRunnable;
import net.liquidpineapple.pang.runnables.UpdateRunnable;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * The main application, extends {@link JFrame}.
 *
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class Application extends JFrame {

  @Getter
  private static Application app;

  // Set to true to prevent loosing lives
  public static final boolean cheatMode = false;

  public static boolean multiplayer;
  public static HashMap<String, BufferedImage> imageCache = new HashMap<>();
  public static LifeSystem lifeSystem;

  @Getter
  private static Board board;

  @Getter
  @Setter
  private static ScoreSystem scoreSystem;

  private PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();
  private DrawRunnable drawRunnable;
  private UpdateRunnable updateRunnable;
  private Thread drawThread;
  private Thread updateThread;

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

    // Initialize width and height
    Integer width = Integer.valueOf(propertiesHandler.getProperty("application-width"));
    Integer height = Integer.valueOf(propertiesHandler.getProperty("application-height"));
    setResizable(false);
    setSize(width, height);
    Logger.info("Initialized with width: " + width + " and height: " + height);

    // Window title
    String name = propertiesHandler.getProperty("application-name");
    setTitle(name);

    // Initialize systems and fields
    scoreSystem = ScoreSystem.getInstance();
    lifeSystem = LifeSystem.getInstance();
    board = new Board();
    add(board);

    addCloseHandler();

    setLocationRelativeTo(null);
    setVisible(true);

    startRunnables();

    AudioSystem.start();
    AudioSystem.changeLoopingSound("/sounds/bg.mp3");

    Logger.info("Application started successfully!");
  }

  private void startRunnables() {
    updateRunnable = new UpdateRunnable(board, scoreSystem, lifeSystem);
    updateThread = new Thread(updateRunnable, "UpdateLoop");
    Logger.info("Starting update Thread: " + updateThread);
    updateThread.start();

    drawRunnable = new DrawRunnable(board);
    drawThread = new Thread(drawRunnable, "DrawLoop");
    Logger.info("Starting draw Thread: " + drawThread);
    drawThread.start();
  }

  private void addCloseHandler() {
    JFrame frame = this;
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent ex) {
        if (JOptionPane.showConfirmDialog(frame,
            "Are you sure you want to exit the game?", "Closing",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
          close();
        } else {
          frame.setVisible(true);
        }
      }
    });
  }

  /**
   * Main method of the application.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    handleArgs(args);

    EventQueue.invokeLater(() -> {
      try {
        app = new Application();
        app.start();
      } catch (IOException ex) {
        Logger.error(ex.getMessage(), ex);
      }
    });
  }

  private static void handleArgs(String[] args) {
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
  }

  /**
   * Closes the application.
   */
  public void close() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Logger.info("Shutting down. Goodbye!");
    AudioSystem.stop();

    updateRunnable.terminate();
    try {
      updateThread.join(1000);
    } catch (InterruptedException ex) {
      Logger.error("Update Thread interrupted on close", ex);
    }

    drawRunnable.terminate();
    try {
      drawThread.join(1000);
    } catch (InterruptedException ex) {
      Logger.error("Draw Thread interrupted on close", ex);
    }

    Logger.getInstance().flush();
    this.dispose();
  }
}
