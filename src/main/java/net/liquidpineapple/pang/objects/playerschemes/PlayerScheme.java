package net.liquidpineapple.pang.objects.playerschemes;

/**
 * The PlayerScheme interface implements all methods that differ per player.
 * @author Govert de Gans
 * @date 2016-09-28
 */
public interface PlayerScheme {
  String getTextureName();

  boolean leftPressed();

  boolean rightPressed();

  boolean shootPressed();

  String getName();

  String getBeamTextureName();
}
