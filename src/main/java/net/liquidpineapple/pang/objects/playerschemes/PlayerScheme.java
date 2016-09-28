package net.liquidpineapple.pang.objects.playerschemes;

/**
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
