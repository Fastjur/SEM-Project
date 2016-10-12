package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 11-10-2016.
 * Class that creates an iterator with multiplayer levels.
 * part of the iterator design pattern
 */
public class MultiPlayerLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/multiplayer";

  /**
   * Method to fill the arraylist with levels.
   */
  public MultiPlayerLevels() {
    super.initArray(DIRECTORY);
  }

}
