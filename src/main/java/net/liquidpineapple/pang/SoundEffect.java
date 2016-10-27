package net.liquidpineapple.pang;

/**
 * @author Govert de Gans
 * @date 2016-10-25
 **/
public final class SoundEffect {
  public static final SoundEffect COLLECT_COIN = new SoundEffect("coin");
  public static final SoundEffect COLLECT_BOMB = new SoundEffect("genericPowerup");
  public static final SoundEffect COLLECT_FREEZE = new SoundEffect("genericPowerup");
  public static final SoundEffect COLLECT_HEART = new SoundEffect("genericPowerup");
  public static final SoundEffect COLLECT_HOOK = new SoundEffect("genericPowerup");
  public static final SoundEffect COLLECT_SHIELD = new SoundEffect("shield");
  public static final SoundEffect COLLECT_STICKY = new SoundEffect("genericPowerup");
  public static final SoundEffect FOOTSTEP = new SoundEffect("step");
  public static final SoundEffect HOOK_SHOOT = new SoundEffect("lazor");
  public static final SoundEffect PLAYER_HIT = new SoundEffect("hit");
  public static final SoundEffect BALL_DESTROY = new SoundEffect("pop");

  private String name;

  private SoundEffect(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
