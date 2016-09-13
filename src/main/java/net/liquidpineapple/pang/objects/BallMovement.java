package net.liquidpineapple.pang.objects;

import lombok.Getter;

/**
 * @author Jurriaan Den Toonder
 *         Created on 12-9-16
 */
public enum BallMovement {
    LEFT_MOVEMENT(-2),
    RIGHT_MOVEMENT(2);

    @Getter
    private final int dx;

    BallMovement(int dx) {
        this.dx = dx;
    }
}
