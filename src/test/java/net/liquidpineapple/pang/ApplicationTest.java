package net.liquidpineapple.pang;

import org.junit.Test;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 06 09 2016.
 */
public class ApplicationTest {

    @Test
    public void testStartup() throws Throwable {
        Application app = new Application();
        app.start();
    }

}
