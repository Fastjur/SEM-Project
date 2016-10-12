package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 10-10-2016.
 * Class which keeps track of the singleplayerlevels.
 * Part of the iterator design pattern.
 */
public class SinglePlayerLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/singleplayer";

  /**
   * Method to fill the levels array with singleplayerlevels.
   */
  public SinglePlayerLevels() {
    super.initArray(DIRECTORY);
  }
}
