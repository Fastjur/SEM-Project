package net.liquidpineapple.pang;

import java.io.File;
import java.io.FileNotFoundException;

import lombok.Getter;

/**
 * @author Jurriaan Den Toonder Created on 24-10-16
 */
public class FileHandler {

  /**
   * Singleton constructor
   */
  private FileHandler() {}

  @Getter
  private static final FileHandler instance = new FileHandler();

  private PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();

  /**
   * Return the application folder for storage of levels etc.
   * @return {@link File} The folder location.
   * @throws FileNotFoundException thrown when folder could not be found or created.
   */
  public File getApplicationFolder() throws FileNotFoundException {
    String homeFolderName = appendSlash(System.getProperty("user.home"));
    String appFolderName = appendSlash(propertiesHandler.getProperty("application-folder"));

    File file = new File(homeFolderName + appFolderName);
    file = new File(file.getAbsoluteFile().toString());
    file.mkdirs();
    if (file.exists() && file.isDirectory()) {
      return file;
    } else {
      throw new FileNotFoundException("Could not get application folder");
    }
  }

  public File getLogsFolder() throws FileNotFoundException {
    String appFolder = appendSlash(getApplicationFolder().toString());
    String logsFolder = appendSlash(propertiesHandler.getProperty("log-folder-name"));
    File folder = new File(appFolder + logsFolder);
    folder.mkdirs();
    return folder;
  }

  private String appendSlash(String path) {
    path = path.endsWith("/") ? path : path + "/";
    return path;
  }
}
