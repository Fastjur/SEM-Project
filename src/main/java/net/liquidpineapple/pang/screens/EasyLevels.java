package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 17-10-2016.
 */
public class EasyLevels extends LevelIterator {
  private static final String DIRECTORY = "/levels/easylevel/";

  /**
   * Method to fill the levels array with easy levels.
   */
  public EasyLevels() {
    super.initArray(DIRECTORY);
  }
}
