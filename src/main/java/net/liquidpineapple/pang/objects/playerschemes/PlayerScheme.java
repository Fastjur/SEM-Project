package net.liquidpineapple.pang.objects.playerschemes;

/**
 * The PlayerScheme interface implements all methods that differ per player.
 * @author Govert de Gans
 * @date 2016-09-28
 */
public interface PlayerScheme {
  public String getTextureName();

  public boolean leftPressed();

  public boolean rightPressed();

  public boolean shootPressed();

  public String getName();
}
