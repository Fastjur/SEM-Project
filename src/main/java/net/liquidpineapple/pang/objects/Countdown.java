package net.liquidpineapple.pang.objects;

import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * An object that displays the countdown at the beginning of a level.
 * @author Govert de Gans
 * @date 2016-10-28
 */
public class Countdown extends GameObject {

  private Instant start = null;
  @Getter
  private int currentNum = 3;
  private static final String TEXTURE_LOCATION = "/sprites/numbers/hud_";
  private static final String TEXTURE_EXTENSION = ".png";

  /**
   * Constructor for the Countdown.
   */
  public Countdown(double startX, double startY) {
    super("/sprites/numbers/hud_3.png", startX, startY);
  }

  @Override
  public void doUpdate() {
    if (start == null) {
      this.start = Instant.now();
    }

    long secondsPassed = ChronoUnit.SECONDS.between(start, Instant.now());
    if (currentNum != 3 - secondsPassed) {
      currentNum = 3 - (int) secondsPassed;
      if (secondsPassed < 3) {
        this.changeImage(getTextureLocation(currentNum));
      }
    }
  }

  /**
   * Gets the texture location for the specified number
   *
   * @param num the number to get the texture location for.
   * @return a String containing the relative texture path.
   */
  private String getTextureLocation(int num) {
    return TEXTURE_LOCATION + num + TEXTURE_EXTENSION;
  }
}
