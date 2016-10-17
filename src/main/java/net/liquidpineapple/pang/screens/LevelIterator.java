package net.liquidpineapple.pang.screens;

import edu.emory.mathcs.backport.java.util.Collections;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.XmlHandler;
import net.liquidpineapple.pang.logger.Logger;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   * Method that loads the array of levels when we play from a .jar file.
   *
   * @param jarFile - the jarfile from which we play.
   * @param path    - path to the levels folder.
   * @throws IOException - Exception thrown when there goes something wrong with reading/closing the
   *                     jar.
   */
  private void loadArrayJar(File jarFile, String path) throws IOException {
    // Run with JAR file
    final JarFile jar = new JarFile(jarFile);
    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
    while (entries.hasMoreElements()) {
      final String name = entries.nextElement().getName();
      if (name.startsWith(path + "/")) { //filter according to the path
        try { //try to create screens
          Screen levelScreen = XmlHandler.createFromXml("/" + name);
          levels.add(levelScreen);
        } catch (IOException ioEx) {
          Logger.error("File not found", ioEx);
        } catch (ParserConfigurationException | SAXException parserEx) {
          Logger.error(Arrays.toString(parserEx.getStackTrace()), parserEx);
        }
      }
    }
    jar.close();

    // Sorting, because the reading in the jar file is not alphabetic
    Collections.sort(levels, new Comparator<LevelScreen>() {
      @Override
      public int compare(LevelScreen level1, LevelScreen level2) {
        if (level1.getDifficulty() > level2.getDifficulty()) {
          return 1;
        } else if (level1.getDifficulty() < level2.getDifficulty()) {
          return -1;
        } else {
          return 0;
        }
      }
    });
  }

  /**
   * Method that loads the array with levels when we play from the IDE.
   *
   * @param directory - directory of the levels.
   */
  private void loadArrayIde(final String directory) {
    //get the files from the directory
    File[] files = new File(Application.class.getResource("/" + directory).getFile()).listFiles();
    Pattern pattern = Pattern.compile("levels\\\\((single|multi)player|custom)\\\\level\\d*.xml");

    for (File file : files) {
      if (!file.isDirectory()) {
        try {
          Matcher matcher = pattern.matcher(file.getPath());
          String path = "";
          while (matcher.find()) {
            path = "/" + matcher.group(0);
          }
          //create level using the path to the level.
          Screen newScreen = XmlHandler.createFromXml(path);
          levels.add(newScreen);
        } catch (IOException ioEx) {
          Logger.error("File not found", ioEx);
        } catch (ParserConfigurationException | SAXException parserEx) {
          Logger.error(Arrays.toString(parserEx.getStackTrace()), parserEx);
        }
      }
    }
  }


  /**
   * Method to fill the array with levels.
   *
   * @param path - path from where the levels should be loaded.
   */
  public void initArray(final String path) {
    final File jarFile = new File(getClass().getProtectionDomain()
        .getCodeSource().getLocation().getPath());
    if (jarFile.isFile()) { //Run with JAR
      try {
        loadArrayJar(jarFile, path);
      } catch (IOException ioEx) {
        Logger.error("Error opening/closing JAR", ioEx);
      }
    } else { // Run with IDE
      loadArrayIde(path);
    }
  }
}
