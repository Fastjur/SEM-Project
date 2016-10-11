package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * Created by Tim on 10-10-2016.
 */
public abstract class LevelIterator {
  private ArrayList<Screen> levels = new ArrayList<>();

  public Iterator createIterator() {
    return levels.iterator();
  }

  private void loadArrayJAR(File jarFile, String path) throws IOException {
    // Run with JAR file
    final JarFile jar = new JarFile(jarFile);
    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
    while (entries.hasMoreElements()) {
      final String name = entries.nextElement().getName();
      if (name.startsWith(path + "/")) { //filter according to the path
        try {
          Screen levelScreen = LevelScreen.createFromXml("/" + name);
          levels.add(levelScreen);
        } catch (IOException ioEx) {
          Logger.error("File not found", ioEx);
        } catch (ParserConfigurationException parserEx) {
          Logger.error(parserEx.getStackTrace().toString(), parserEx);
        } catch (SAXException saxEx) {
          Logger.error(saxEx.getStackTrace().toString(), saxEx);
        }
      }
    }
    jar.close();

    // Sorting
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

  private void loadArrayIDE(final String directory) {
    System.out.println("loadArrayIDE");
    File[] files = new File(Application.class.getResource("/" + directory).getFile()).listFiles();
    Pattern pattern = Pattern.compile("levels\\\\(single|multi)player\\\\level\\d*.xml");

    for (File file : files) {
      System.out.println(file.toPath());
      if (!file.isDirectory()) {
        try {
          Matcher m = pattern.matcher(file.getPath());
          String path = "";
          while (m.find()) {
            path = "/" + m.group(0);
          }
          System.out.println(path);
          Screen newScreen = LevelScreen.createFromXml(path);
          levels.add(newScreen);
        } catch (IOException ioEx) {
          Logger.error("File not found", ioEx);
        } catch (ParserConfigurationException parserEx) {
          Logger.error(parserEx.getStackTrace().toString(), parserEx);
        } catch (SAXException saxEx) {
          Logger.error(saxEx.getStackTrace().toString(), saxEx);
        }
      }
    }
  }


  public void initArray(final String path) {
    final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
    if (jarFile.isFile()) { //Run with JAR
      try {
        loadArrayJAR(jarFile, path);
      } catch (IOException ioEx) {
        Logger.error("Error opening/closing JAR", ioEx);
      }
    } else { // Run with IDE
      loadArrayIDE(path);
    }
  }
}
