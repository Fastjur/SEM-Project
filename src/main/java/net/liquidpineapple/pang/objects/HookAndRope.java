package net.liquidpineapple.pang.objects;

import lombok.EqualsAndHashCode;

import lombok.Getter;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.TimeSystem;

/**
 * Created by Tim on 9-9-2016.
 * Class that represents the hook system in the game.
 */
@EqualsAndHashCode(callSuper = true)
public class HookAndRope extends GameObject {

  private final double maxY;
  private double dy = 3; //specify upspeed rope here.
  @Getter
  private Player player;
  private int removeDelay = 0;
  private int removeMoment = -1;
  // Because I want to check this hasn't changed,
  // but it should never be an actual time if it hasn't been set. -1 is a impossible time.

  /**
   * Constructor for the HookAndRope class
   *
   * @param startX - Xlocation where the hook should go up.
   * @param maxY   - Maximum height the rope should go up to.
   */
  public HookAndRope(double startX, double maxY, Player player, int removeDelay) {
    super(player.getPlayerScheme().getBeamTextureName(), startX, 600);
    this.maxY = maxY;
    this.player = player;
    this.removeDelay = removeDelay;
  }

  /**
   * Method to move the hook upwards.
   */
  public void move() {
    ypos -= dy;

    //if ypos reaches 0, the top of the frame has been reached,
    // and ypos should go to boardHeight again
    if (ypos <= maxY && removeMoment == -1) {
      removeMoment = TimeSystem.getTime() - removeDelay;
      dy = 0;
    }
    if (TimeSystem.getTime() == removeMoment) {
      player.activeHooks--;
      Application.getBoard().getCurrentScreen().objectList.remove(this);
    }
  }

  /**
   * method to update the position of the hook,
   * iff its in use.
   */
  public void doUpdate() {
    super.doUpdate();
    move();
  }

}
