package net.liquidpineapple.pang.screens;

/**
 * Created by Tim on 13-10-2016.
 * Class which keeps track of the userCreatedLevels.
 * Part of the iterator design pattern.
 */
public class UserCreatedLevels extends LevelIterator {
  private static final String DIRECTORY = "levels/customlevel";

  /**
   * Method to fill the levels array with userCreatedLevels.
   */
  public UserCreatedLevels() {
    super.initArray(DIRECTORY);
  }
}
