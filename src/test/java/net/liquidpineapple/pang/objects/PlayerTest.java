package net.liquidpineapple.pang.objects;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/12.
 */
public class PlayerTest {

    private static final int startX = 15;
    private static final int startY = 20;
    private static final int max = 50;

    private static final String playerTexture = "/sprites/player/p1_front.png";
    private static final String defaultTexture = "/sprites/no-texture.png";

    private Player player;

    @Before
    public void setUp() {
        player = new Player(startX, startY, max);
    }

    @Test
    public void testGetBounds() throws Exception {
        Rectangle rectangle = new Rectangle(15, 20, 66, 92);
        assertEquals(rectangle, player.getBounds());
    }

    @Test
    public void testSetPos() throws Exception {
        player.setPos(5, 6);
        assertEquals(5, player.getXPos());
        assertEquals(6, player.getYPos());
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
        assertEquals(11, player.getXPos());
        player.move();
        assertEquals(12, player.getXPos());
        player.setDx(-1);
        player.move();
        assertEquals(11, player.getXPos());
    }

    @Test
    public void testKeyPressed() throws Exception {
        KeyEvent event = new KeyEvent(player, 1, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'a');
        player.keyPressed(event);
        assertEquals(1, player.getDx());

        event.setKeyCode(KeyEvent.VK_LEFT);
        player.keyPressed(event);
        assertEquals(-1, player.getDx());
    }

    @Test
    public void testKeyReleased() throws Exception {
        KeyEvent event = new KeyEvent(player, 1, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'a');
        player.keyPressed(event);
        assertEquals(1, player.getDx()); // Check if value actually changed from default zero
        player.keyReleased(event);
        assertEquals(0, player.getDx()); // Check if value went back to default
    }
}