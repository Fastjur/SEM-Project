package net.liquidpineapple.pang.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/21.
 */
public class LoggerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Logger.setLevel(LoggerTypes.INFO);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testSetLevel() throws Exception {
        String time = String.valueOf(new Date().getTime());
        final String EXPECTED_INFO = "[ERROR] " + time + "\n" +
                                     "[WARNING] " + time + "\n" +
                                     "[INFO] " + time + "\n";
        final String EXPECTED_WARNING = "[WARNING] " + time + "\n";
        final String EXPECTED_ERROR = "[ERROR] " + time + "\n";

        Logger.setLevel(LoggerTypes.WARNING);

        // Check that only the warning line is printed
        Logger.warning(time);
        Logger.info(time);
        assertEquals(EXPECTED_WARNING, outContent.toString());

        outContent.reset();

        // Check that only the error is logged out
        Logger.setLevel(LoggerTypes.ERROR);
        Logger.error(time);
        Logger.warning(time);
        Logger.info(time);
        assertEquals(EXPECTED_ERROR, outContent.toString());

        outContent.reset();

        // Check that all is logged on info
        Logger.setLevel(LoggerTypes.INFO);
        Logger.error(time);
        Logger.warning(time);
        Logger.info(time);
        assertEquals(EXPECTED_INFO, outContent.toString());
    }

    @Test
    public void testInfo() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[INFO] We are logging this yay!\n";
        Logger.info(log);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testWarning() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[WARNING] We are logging this yay!\n";
        Logger.warning(log);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testError() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[ERROR] We are logging this yay!\n";
        Logger.error(log);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testError1() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[ERROR] We are logging this yay!\n";
        Exception exception = new IllegalArgumentException("Some exception");
        Logger.error(log, exception);
        assertEquals(expected, outContent.toString());
    }
}
