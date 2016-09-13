package net.liquidpineapple.pang;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.objects.ScoreSystem;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The main application, extends {@link JFrame}
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
@Slf4j
public class Application extends JFrame {

    private static String PROPERTIES_LOCATION = "/config.properties";
    private static final int UPDATE_DELAY = 10;
    private static final int DRAW_DELAY = 5;

    private ScoreSystem scoreKeeper;

    @Getter
    private static Board board;
    private Properties properties;

    public Application(String propertiesLocation) throws IOException {
        super();
        log.info("Starting application...");
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
        board = new Board(width, height);
        new InputHandler(board);
        add(board);
        log.info("Initialized with width: " + width + " and height: " + height);

        setTitle(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        log.info("Application started successfully!");

        Runnable doUpdateRunnable = () -> {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            log.info("Update loop is running");

            while(true) {
                timeDiff = System.currentTimeMillis() - beforeTime;
                sleep = UPDATE_DELAY - timeDiff;

                if (sleep < 0) {
                    sleep = 2;
                }

                try {
                    board.doUpdate();

                    beforeTime = System.currentTimeMillis();
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    log.info("Interrupted: " + e.getMessage());
                }
            }
        };

        Runnable doDrawRunnable = () -> {
            long beforeTime, timeDiff, sleep;
            beforeTime = System.currentTimeMillis();
            log.info("Draw loop is running");

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
                    log.info("Interrupted: " + e.getMessage());

                }
            }
        };

        new Thread(doUpdateRunnable).start();
        new Thread(doDrawRunnable).start();
    }

    public static void main(String[] args) throws Throwable {
        EventQueue.invokeLater(() -> {
            Application app = null;
            try {
                app = new Application(PROPERTIES_LOCATION);
                app.start();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
