package net.liquidpineapple.pang.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
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
    private final PrintStream oldStdOut = System.out;
    private final PrintStream oldStdErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Logger.setLevel(LoggerTypes.INFO);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(oldStdOut);
        System.setErr(oldStdErr);
    }

    @Test
    public void testSetLevel() throws Exception {
        String time = String.valueOf(new Date().getTime());
        final String EXPECTED_WARN_AND_INFO = "[WARNING] " + time + System.lineSeparator() +
                                              "[INFO] " + time + System.lineSeparator();
        final String EXPECTED_WARNING = "[WARNING] " + time + System.lineSeparator();
        final String EXPECTED_ERROR = "[ERROR] " + time + System.lineSeparator();

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
        assertEquals(EXPECTED_ERROR, errContent.toString());

        outContent.reset();
        errContent.reset();

        // Check that all is logged on info
        Logger.setLevel(LoggerTypes.INFO);
        Logger.error(time);
        Logger.warning(time);
        Logger.info(time);
        assertEquals(EXPECTED_ERROR, errContent.toString());
        assertEquals(EXPECTED_WARN_AND_INFO, outContent.toString());
    }

    @Test
    public void testInfo() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[INFO] We are logging this yay!" + System.lineSeparator();
        Logger.info(log);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testWarning() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[WARNING] We are logging this yay!" + System.lineSeparator();
        Logger.warning(log);
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testError() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[ERROR] We are logging this yay!" + System.lineSeparator();
        Logger.error(log);
        assertEquals(expected, errContent.toString());
    }

    @Test
    public void testError1() throws Exception {
        String log = "We are logging this yay!";
        String expected = "[ERROR] We are logging this yay!" + System.lineSeparator() +
            "java.lang.IllegalArgumentException: Some exception";
        Exception exception = new IllegalArgumentException("Some exception");
        Logger.error(log, exception);
        assertTrue(errContent.toString().startsWith(expected));
    }
}
