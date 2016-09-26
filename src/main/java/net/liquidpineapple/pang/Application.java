package net.liquidpineapple.pang;

import lombok.Getter;
import lombok.Setter;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.LifeSystem;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.objects.DropRandomizer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The main application, extends {@link JFrame}
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class Application extends JFrame {

    private static String PROPERTIES_LOCATION = "/config.properties";
    private static final int UPDATE_DELAY = 10;
    private static final int DRAW_DELAY = 5;

    // Set to true to prevent loosing lives
    public static final boolean cheatMode = false;

    public static LifeSystem lifeKeeper;

    @Getter
    private static Board board;

    @Getter
    private static DropRandomizer dropRandomizer;

    @Getter
    @Setter
    private static ScoreSystem scoreKeeper;

    private Properties properties;

    public Application(String propertiesLocation) throws IOException {
        super();
        Logger.info("Starting application...");
        InputStream stream = this.getClass().getResourceAsStream(propertiesLocation);
        if (stream != null) {
            properties = new Properties();
            properties.load(stream);
        } else {
            String err = "Could not find resource file: " + propertiesLocation;
            throw new FileNotFoundException(err);
        }
    }

    public void start() throws IOException {


        Integer width = Integer.valueOf(properties.getProperty("application-width"));
        Integer height = Integer.valueOf(properties.getProperty("application-height"));
        String name = properties.getProperty("application-name");

        setResizable(false);
        setSize(width, height);

        scoreKeeper = new ScoreSystem();
        lifeKeeper = new LifeSystem();
        dropRandomizer = new DropRandomizer();
        board = new Board(width, height);
        add(board);
        Logger.info("Initialized with width: " + width + " and height: " + height);

        setTitle(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        Logger.info("Application started successfully!");

        TimeSystem.getInstance();

        Runnable doUpdateRunnable = () -> {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            Logger.info("Update loop is running");

            while(true) {
                timeDiff = System.currentTimeMillis() - beforeTime;
                sleep = UPDATE_DELAY - timeDiff;

                if (sleep < 0) {
                    sleep = 2;
                }

                try {
                    board.doUpdate();
                    scoreKeeper.displayscore();
                    lifeKeeper.updateLifes();

                    beforeTime = System.currentTimeMillis();
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    Logger.info("Interrupted: " + e.getMessage());
                }
            }
        };

        Runnable doDrawRunnable = () -> {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            Logger.info("Draw loop is running");

            while(true) {
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
                } catch (InterruptedException e) {
                    Logger.info("Interrupted: " + e.getMessage());

                }
            }
        };

        new Thread(doUpdateRunnable).start();
        new Thread(doDrawRunnable).start();

        AudioSystem.start();
        AudioSystem.changeLoopingSound("/sounds/bg.mp3");
    }

    public static void main(String[] args) throws Throwable {
        EventQueue.invokeLater(() -> {
            Application app = null;
            try {
                app = new Application(PROPERTIES_LOCATION);
                app.start();
            } catch (IOException e) {
                Logger.error(e.getMessage(), e);
            }
        });
    }
}
