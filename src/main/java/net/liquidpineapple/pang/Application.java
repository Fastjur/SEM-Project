package net.liquidpineapple.pang;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.gui.Board;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The main application, extends {@link JFrame}
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 06 09 2016.
 */
@Slf4j
public class Application extends JFrame {

    private static String PROPERTIES_LOCATION = "/config.properties";

    private Properties properties;

    public Application() throws IOException {
        log.info("Starting application...");
        InputStream stream = this.getClass().getResourceAsStream(PROPERTIES_LOCATION);
        if (stream != null) {
            properties = new Properties();
            properties.load(stream);
        } else {
            String err = "Could not find resource file: " + PROPERTIES_LOCATION;
            throw new FileNotFoundException(err);
        }
        initGUI();
    }

    private void initGUI() {
        add(new Board());

        Integer width = Integer.valueOf(properties.getProperty("application-width"));
        Integer height = Integer.valueOf(properties.getProperty("application-height"));
        String name = properties.getProperty("application-name");

        setSize(width, height);

        setTitle(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        log.info("Application started successfully with width: " + width + " and height: " + height);
    }

    public void start() {
        EventQueue.invokeLater(() -> {
            Application ex;
            try {
                ex = new Application();
                ex.setVisible(true);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
