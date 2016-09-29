package net.liquidpineapple.pang.objects.playerschemes;

import net.liquidpineapple.pang.InputHandler;

import java.awt.event.KeyEvent;

/**
 * The PlayerScheme for the second player.
 * @author Govert de Gans
 * @date 2016-09-28
 */
public class Player2 implements PlayerScheme {

  public String getTextureName() {
    return "/sprites/player/p2_front.png";
  }

  public boolean leftPressed() {
    return InputHandler.isKeyPressed(KeyEvent.VK_LEFT);
  }

  public boolean rightPressed() {
    return InputHandler.isKeyPressed(KeyEvent.VK_RIGHT);
  }

  public boolean shootPressed() {
    return InputHandler.isKeyPressed(KeyEvent.VK_UP);
  }

  public String getName() {
    return "Player 1";
  }

  public String getBeamTextureName() {
    return "/sprites/beam2.png";
  }
}