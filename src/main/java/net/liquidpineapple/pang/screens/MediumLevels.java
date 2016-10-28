package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 17-10-2016.
 */
public class MediumLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/mediumlevel/";

  /**
   * Method to fill the levels array with medium levels.
   */
  public MediumLevels() {
    super.initArray(DIRECTORY);
  }
}
