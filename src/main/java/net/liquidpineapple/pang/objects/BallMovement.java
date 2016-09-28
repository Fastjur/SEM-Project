package net.liquidpineapple.pang.objects;

import lombok.Getter;


/**
 * Enum for the ball movement.
 * @author Jurriaan Den Toonder Created on 12-9-16
 */
public enum BallMovement {
  LEFT_MOVEMENT(-2 / 5.0),
  RIGHT_MOVEMENT(2 / 5.0);

  @Getter
  private final double dx;

  BallMovement(double dx) {
    this.dx = dx;
  }
}
