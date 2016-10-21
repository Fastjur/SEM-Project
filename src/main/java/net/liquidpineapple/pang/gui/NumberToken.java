package net.liquidpineapple.pang.gui;

import lombok.Getter;
import net.liquidpineapple.pang.objects.GameObject;



/**
 * Created by Erik on 12-9-2016.
 */
public class NumberToken extends GameObject {

  @Getter
  private int currentnumber;

  public NumberToken(int xpos, int ypos) {
    super("/sprites/numbers/hud_0.png", xpos, ypos);
  }

  /**
   * creates a numbertoken on the screen.
   * @param number - index of the number.
   */
  public void setScoreToken(int number) {
    if (number != currentnumber) {
      changeImage("/sprites/numbers/hud_" + number + ".png");
    }
    currentnumber = number;
  }
}
