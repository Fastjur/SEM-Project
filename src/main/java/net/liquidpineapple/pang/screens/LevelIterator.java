package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Tim on 10-10-2016.
 */
public abstract class LevelIterator {
  private ArrayList<Screen> levels = new ArrayList<>();

  public Iterator createIterator() {
    return levels.iterator();
  }

  public synchronized void initArray(final String DIRECTORY) {
    List<String> results = new ArrayList<>();
    //TODO: fix this
    File[] files = new File(Application.class.getResource(DIRECTORY).getFile()).listFiles();

    Pattern pattern = Pattern.compile("levels\\\\singleplayer\\\\level\\d*.xml");



    for (File file : files) {
      System.out.println(file.toPath());
      if (!file.isDirectory()) {
        try {
          Matcher m = pattern.matcher(file.getPath());
          String path = "";
          while(m.find()){
            path ="/"+ m.group(0);
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
}
