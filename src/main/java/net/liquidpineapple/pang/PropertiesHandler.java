package net.liquidpineapple.pang;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesHandler class.
 *
 * @author Jurriaan Den Toonder Created on 30-9-16
 */
public class PropertiesHandler {

  private static final String PROPERTIES_LOCATION = "/config.properties";

  @Getter
  @SuppressWarnings("PMD.UnusedPrivateField") // It is used in the generated getter method
  private static PropertiesHandler instance = new PropertiesHandler();

  private Properties properties;

  /**
   * Singleton constructor for the PropertiesHandler.
   */
  @SneakyThrows
  private PropertiesHandler() {
    InputStream stream = this.getClass().getResourceAsStream(PROPERTIES_LOCATION);
    if (stream != null) {
      properties = new Properties();
      properties.load(stream);
    } else {
      String err = "Could not find resource file: " + PROPERTIES_LOCATION;
      throw new FileNotFoundException(err);
    }
  }

  /**
   * Get a property by key.
   *
   * @param key The key to lookup
   * @return {@link String} containing the value of the property.
   */
  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
