package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 17-10-2016.
 */
public class HardLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/hardlevel";

  /**
   * Method to fill the levels array with easy levels.
   */
  public HardLevels() {
    super.initArray(DIRECTORY);
  }
}
