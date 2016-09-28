package net.liquidpineapple.pang.logger;

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
  private static Logger instance = new Logger();

  private static int level;

  /**
   * Singleton constructor.
   */
  private Logger() {
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
        System.err.println(type.getPrefix() + " " + message);
      } else {
        System.out.println(type.getPrefix() + " " + message);
      }
    }
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
