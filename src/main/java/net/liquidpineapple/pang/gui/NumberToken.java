package net.liquidpineapple.pang.gui;

import lombok.Getter;
import net.liquidpineapple.pang.objects.GameObject;



/**
 * Created by Erik on 12-9-2016.
 */
public class NumberToken extends GameObject {

  private static final String textureLocation0 = "/sprites/numbers/hud_0.png";
  private static final String textureLocation1 = "/sprites/numbers/hud_1.png";
  private static final String textureLocation2 = "/sprites/numbers/hud_2.png";
  private static final String textureLocation3 = "/sprites/numbers/hud_3.png";
  private static final String textureLocation4 = "/sprites/numbers/hud_4.png";
  private static final String textureLocation5 = "/sprites/numbers/hud_5.png";
  private static final String textureLocation6 = "/sprites/numbers/hud_6.png";
  private static final String textureLocation7 = "/sprites/numbers/hud_7.png";
  private static final String textureLocation8 = "/sprites/numbers/hud_8.png";
  private static final String textureLocation9 = "/sprites/numbers/hud_9.png";
  @Getter
  private int currentnumber;

  public NumberToken(int xpos, int ypos) {
    super(textureLocation0, xpos, ypos);
  }

  /**
   * creates a numbertoken on the screen.
   * @param number - index of the number.
   */
  public void setScoreToken(int number) {
    if (number != currentnumber) {
      switch (number) {
        case 0:
          changeImage(textureLocation0);
          break;
        case 1:
          changeImage(textureLocation1);
          break;
        case 2:
          changeImage(textureLocation2);
          break;
        case 3:
          changeImage(textureLocation3);
          break;
        case 4:
          changeImage(textureLocation4);
          break;
        case 5:
          changeImage(textureLocation5);
          break;
        case 6:
          changeImage(textureLocation6);
          break;
        case 7:
          changeImage(textureLocation7);
          break;
        case 8:
          changeImage(textureLocation8);
          break;
        case 9:
          changeImage(textureLocation9);
          break;
        default:
          break;
      }
    }
    currentnumber = number;
  }

  public int getcurrentnumber() {
    return currentnumber;
  }

}
