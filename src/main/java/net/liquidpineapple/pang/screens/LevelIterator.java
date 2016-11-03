package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.XmlHandler;
import net.liquidpineapple.pang.logger.Logger;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Tim on 10-10-2016.
 * Class that creates an iterator over the levels
 */
public abstract class LevelIterator {
  private ArrayList<Screen> levels = new ArrayList<>();

  public Iterator createIterator() {
    return levels.iterator();
  }

  /**
   * Method to fill the array with levels.
   *
   * @param path - path from where the levels should be loaded.
   */
  public void initArray(final String path) {
    List<String> filenames = new ArrayList<>();

    try (
        InputStream in = getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

      String resource;
      while ((resource = br.readLine()) != null) {
        filenames.add(resource);
      }
    } catch (IOException ex) {
      Logger.error(ex.getMessage(), ex);
    }

    for (String file : filenames) {
      Screen level;
      try {
        level = XmlHandler.createFromXml(path + file);
      } catch (IOException | ParserConfigurationException | SAXException ex) {
        level = null;
        Logger.error(ex.getMessage(), ex);
      }
      if (level != null) {
        levels.add(level);
      } else {
        Logger.warning("Attempted to add null level");
      }
    }

    if (levels.size() == 0) {
      throw new ExceptionInInitializerError("Could not load levels, level array size was 0.");
    }
    Logger.info("Array initialized, " + levels.size() + " added to array");
  }

  private InputStream getResourceAsStream(String resource) {
    final InputStream in = getContextClassLoader().getResourceAsStream(resource);
    return in == null ? getClass().getResourceAsStream(resource) : in;
  }

  private ClassLoader getContextClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }
}
