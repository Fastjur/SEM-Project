package net.liquidpineapple.pang.objects.playerschemes;

import net.liquidpineapple.pang.InputHandler;

import java.awt.event.KeyEvent;

/**
 * @author Govert de Gans
 * @date 2016-09-28
 */
public class Player1 implements PlayerScheme {

  public String getTextureName() {
    return "/sprites/player/p1_front.png";
  }

  public boolean leftPressed() {
    return InputHandler.isKeyPressed(KeyEvent.VK_A);
  }

  public boolean rightPressed() {
    return InputHandler.isKeyPressed(KeyEvent.VK_D);
  }

  public boolean shootPressed() {
    return InputHandler.isKeyPressed(KeyEvent.VK_W);
  }

  public String getName() {
    return "Player 1";
  }
}
