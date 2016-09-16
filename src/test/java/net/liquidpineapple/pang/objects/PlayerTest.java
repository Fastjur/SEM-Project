package net.liquidpineapple.pang.objects;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/12.
 */
public class PlayerTest {

    private static final int startX = 15;
    private static final int startY = 20;
    private static final int max = 50;

    private static final String defaultTexture = "/sprites/no-texture.png";
    private final double DELTA = 0.01;

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player(startX, startY);
    }

    @Test
    public void testGetBounds() throws Exception {
        Rectangle rectangle = new Rectangle(15, 20, 66, 92);
        assertEquals(rectangle, player.getBounds());
    }

    @Test
    public void testSetPos() throws Exception {
        player.setPos(5, 6);
        assertEquals(5, player.getXPos(), DELTA);
        assertEquals(6, player.getYPos(), DELTA);
    }

    @Test
    public void testSetImage() throws Exception {
        ImageIcon ii = new ImageIcon(defaultTexture);
        Image newImg = ii.getImage();
        player.setImage(newImg);
        assertEquals(newImg, player.getImage());
    }

    @Test
    public void testMove() throws Exception {
        player.setPos(10, 20);
        player.setDx(1);
        player.move();
        assertEquals(11, player.getXPos(), DELTA);
        player.move();
        assertEquals(12, player.getXPos(), DELTA);
        player.setDx(-1);
        player.move();
        assertEquals(11, player.getXPos(), DELTA);

        player.setPos(1, 1);
        player.setDx(-1);
        for (int i = 0; i < 10; i++) {
            player.move();
        }
        assertEquals(1, player.getXPos(), DELTA);

        player.setPos(max, 1);
        player.setDx(1);
        for (int i = 0; i < 10; i++) {
            player.move();
        }
        assertEquals(max, player.getXPos(), DELTA);
    }
}
