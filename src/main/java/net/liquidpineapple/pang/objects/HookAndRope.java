package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.logger.Logger;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Tim on 9-9-2016.
 * Class that represents the hook system in the game.
 */
@Data

@EqualsAndHashCode(callSuper = true)
public class HookAndRope extends GameObject {

  private static final String textureLocation = "/sprites/beam.png";
  private final double maxY;
  private double dy = 3; //specify upspeed rope here.
  private Player player;

  /**
   * Constructor for the HookAndRope class
   *
   * @param startX - Xlocation where the hook should go up.
   * @param maxY   - Maximum height the rope should go up to.
   */
  public HookAndRope(double startX, double maxY, Player player) {
    super(textureLocation, startX, 600);
    this.maxY = maxY;
    this.player = player;
  }

  /**
   * Method to move the hook upwards.
   */
  public void move() {
    double oldY = ypos;
    ypos -= dy;

    //if ypos reaches 0, the top of the frame has been reached,
    // and ypos should go to boardHeight again
    if (ypos <= maxY) {
      player.activeHooks--;
      Application.getBoard().getCurrentScreen().objectList.remove(this);
    }

    Logger.info("Moved " + this + " from y: " + oldY + " to y: " + ypos);
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
