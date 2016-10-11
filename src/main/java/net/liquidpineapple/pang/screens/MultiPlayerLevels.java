package net.liquidpineapple.pang.screens;

import java.util.Iterator;

/**
 * Created by Tim on 11-10-2016.
 */
public class MultiPlayerLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/multiplayer";

  public MultiPlayerLevels() {
    super.initArray(DIRECTORY);
  }

  @Override
  public Iterator createIterator() {
    return super.createIterator();
  }
}
