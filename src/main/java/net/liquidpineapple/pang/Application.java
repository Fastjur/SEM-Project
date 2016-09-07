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
 * @date 2016/09/06.
 */
@Slf4j
public class Application extends JFrame {

    private static String PROPERTIES_LOCATION = "/config.properties";

    private Properties properties;

    public Application(String propertiesLocation) throws IOException {
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

    public void start() {


        Integer width = Integer.valueOf(properties.getProperty("application-width"));
        Integer height = Integer.valueOf(properties.getProperty("application-height"));
        String name = properties.getProperty("application-name");

        setSize(width, height);
        log.info("Initialized with width: " + width + " and height: " + height);

        setTitle(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
        add(new Board().getPanel());

        log.info("Application started successfully!");
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
