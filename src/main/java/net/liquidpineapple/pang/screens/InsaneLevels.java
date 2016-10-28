package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 20-10-2016.
 */
public class InsaneLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/insanelevel/";

  /**
   * Method to fill the levels array with insane levels.
   */
  public InsaneLevels() {
    super.initArray(DIRECTORY);
  }
}
