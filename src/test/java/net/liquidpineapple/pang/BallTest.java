package net.liquidpineapple.pang;

import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
public class BallTest {

    private static String PROPERTIES_LOCATION = "/config.properties";

    @Test
    public void testStartup() throws Throwable {
        Application app = new Application(PROPERTIES_LOCATION);
        app.start();
    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongProperties() throws Throwable {
        Application app = new Application("test");
        app.start();
    }

}
