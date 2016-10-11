package net.liquidpineapple.pang.screens;

import java.util.Iterator;

/**
 * Created by Tim on 10-10-2016.
 */
public class SinglePlayerLevels extends LevelIterator {
  private static final String DIRECTORY = "/levels/singleplayer";

  public SinglePlayerLevels() {
    super.initArray(DIRECTORY);
  }

  @Override
  public Iterator createIterator() {
    return super.createIterator();
  }
}
