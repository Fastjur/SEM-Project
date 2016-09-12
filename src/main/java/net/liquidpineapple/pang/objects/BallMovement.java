package net.liquidpineapple.pang.objects;

import lombok.Getter;

/**
 * @author Jurriaan Den Toonder
 *         Created on 12-9-16
 */
public enum BallMovement {
    LEFT_MOVEMENT(-15),
    RIGHT_MOVEMENT(15);

    @Getter
    private final int dx;

    BallMovement(int dx) {
        this.dx = dx;
    }
}
