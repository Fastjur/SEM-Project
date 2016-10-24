package net.liquidpineapple.pang.logger;

import net.liquidpineapple.pang.FileHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;

/**
 * The logger class containing static methods to log out to the console.
 *
 * @author Jurriaan Den Toonder
 * @date 2016/09/21.
 */

public class Logger {

  @Getter
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  private static final Logger instance;
  static {
    try{
      instance = new Logger();
    } catch(Exception ex) {
      // Note that catching all Exceptions here is allowed!
      throw new ExceptionInInitializerError(ex);
    }
  }

  private FileHandler fileHandler = FileHandler.getInstance();

  private static int level;
  private static final String DATE_FORMAT = "dd-MM-yyyy_hh-mm-ss";
  private static PrintWriter outWriter;

  /**
   * Singleton constructor.
   */
  private Logger() throws IOException {
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    Date now = new Date();
    String dateString = dateFormat.format(now);

    String logFolder = fileHandler.getLogsFolder().toString();
    File file = new File(logFolder + "/Pang-" + dateString + ".log");

    FileWriter fw = new FileWriter(file, true);
    BufferedWriter bw = new BufferedWriter(fw);
    outWriter = new PrintWriter(bw);
    outWriter.println("Logger initialized at: " + dateString);
  }

  public void flush() {
    outWriter.flush();
  }

  /**
   * Set the level at which the logger should print to the console.
   *
   * @param type {@link LoggerTypes} level of logger.
   */
  public static void setLevel(LoggerTypes type) {
    level = type.getPriority();
  }

  /**
   * Prints to the console using the correct prefix, message and the stack trace.
   * Synchronized to prevent stack traces from getting messed up.
   *
   * @param type    {@link LoggerTypes} type of the message to log.
   * @param message {@link String}, readable message to print to the log
   * @param ex       {@link Exception} the exception that occurred to print the stack trace from.
   */
  private static synchronized void print(LoggerTypes type, String message, Exception ex) {
    if (type.getPriority() >= level) {
      print(type, message);
      ex.printStackTrace();
    }
  }

  /**
   * Prints to the console using the correct prefix and message.
   *
   * @param type    {@link LoggerTypes} type of the message to log.
   * @param message {@link String}, readable message to print to the log
   */
  private static void print(LoggerTypes type, String message) {
    if (type.getPriority() >= level) {
      if (type.equals(LoggerTypes.ERROR)) {
        System.err.println(format(type, message));
      } else {
        System.out.println(format(type, message));
      }
      outWriter.println(format(type, message));
    }
  }

  /**
   * Format the correct text using the type prefix.
   *
   * @param type The type to prefix the message with.
   * @param message The message to append to the formatted string.
   * @return {@link String} using correct format.
   */
  private static String format(LoggerTypes type, String message) {
    return type.getPrefix() + " " + message;
  }

  /**
   * Logs to the console using level info.
   *
   * @param message {@link String}, readable message to print to the log
   */
  public static void info(String message) {
    print(LoggerTypes.INFO, message);
  }

  /**
   * Logs to the console using level warning.
   *
   * @param message {@link String}, readable message to print to the log
   */
  public static void warning(String message) {
    print(LoggerTypes.WARNING, message);
  }

  /**
   * Logs to the console using level error and prints out the stack trace of the exception
   *
   * @param message {@link String}, readable message to print to the log
   * @param ex       {@link Exception} the exception that occurred to print the stack trace from.
   */
  public static void error(String message, Exception ex) {
    print(LoggerTypes.ERROR, message, ex);
  }

  /**
   * Logs to the console using level error.
   *
   * @param message {@link String}, readable message to print to the log
   */
  public static void error(String message) {
    print(LoggerTypes.ERROR, message);
  }
}
