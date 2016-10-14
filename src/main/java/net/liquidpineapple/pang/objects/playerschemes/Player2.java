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

  public String getFrozenTextureName() {
    return "/sprites/player/p2_frozen.png";
  }

  public String getShieldTextureName() {
    return "/sprites/player/p2_shielded.png";
  }

  public boolean leftPressed() {
    return InputHandler.isKeyDown(KeyEvent.VK_LEFT);
  }

  public boolean rightPressed() {
    return InputHandler.isKeyDown(KeyEvent.VK_RIGHT);
  }

  public boolean shootPressed() {
    return InputHandler.isKeyDown(KeyEvent.VK_UP);
  }

  public String getName() {
    return "Player 1";
  }

  public String getBeamTextureName() {
    return "/sprites/beam2.png";
  }
}
