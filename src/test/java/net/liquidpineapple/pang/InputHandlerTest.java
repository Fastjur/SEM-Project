package net.liquidpineapple.pang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Govert de Gans
 * @date 2016-10-20
 */
@SuppressWarnings("unchecked")
public class InputHandlerTest {

  InputHandler inputHandler;
  @Before
  public void setUp() {
    inputHandler = new InputHandler();
    InputHandler.clearState();
    InputHandler.clearKeys();
  }

  @Test
  public void testIsKeyDown() {
    assertFalse(InputHandler.isKeyDown(KeyEvent.VK_4));
    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_4));
    assertTrue(InputHandler.isKeyDown(KeyEvent.VK_4));
  }

  @Test
  public void testIsKeyPressed() {
    assertFalse(InputHandler.isKeyPressed(KeyEvent.VK_5));
    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_5));
    assertTrue(InputHandler.isKeyPressed(KeyEvent.VK_5));
  }

  @Test
  public void testClearKeys() {
    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_6));
    InputHandler.clearKeys();
    assertFalse(InputHandler.isKeyPressed(KeyEvent.VK_6));
  }

  @Test
  public void testIsAnyKeyDown() {
    assertFalse(InputHandler.isAnyKeyDown());
    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_8));
    assertTrue(InputHandler.isAnyKeyDown());
  }

  @Test
  public void testIsLeftMouseButtonDown() {
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 2));
    assertFalse(InputHandler.isLeftMouseButtonDown());
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 1));
    assertTrue(InputHandler.isLeftMouseButtonDown());
  }

  @Test
  public void testIsRightMouseButtonDown() {
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 2));
    assertFalse(InputHandler.isRightMouseButtonDown());
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 3));
    assertTrue(InputHandler.isRightMouseButtonDown());
  }

  @Test
  public void testIsMiddleMouseButtonDown() {
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 1));
    assertFalse(InputHandler.isMiddleMouseButtonDown());
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 2));
    assertTrue(InputHandler.isMiddleMouseButtonDown());
  }

  @Test
  public void testGetMousePos() {
    Point pt = new Point(15, 344);
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, pt.x, pt.y, 3, false, 1));
    assertEquals(pt, InputHandler.getMousePos());
  }

  @Test
  public void testClearState() {
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 1));
    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_0));
    InputHandler.clearState();
    assertFalse(InputHandler.isAnyKeyDown());
    assertFalse(InputHandler.isLeftMouseButtonDown()
        || InputHandler.isRightMouseButtonDown()
        || InputHandler.isMiddleMouseButtonDown());
  }

  @Test
  public void testKeyReleased() {
    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_9));
    assertTrue(InputHandler.isKeyDown(KeyEvent.VK_9));
    inputHandler.keyReleased(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_9));
    assertFalse(InputHandler.isKeyDown(KeyEvent.VK_9));
  }

  @Test
  public void testEmptyMethods() {
    inputHandler.keyTyped(null);
    inputHandler.mouseClicked(null);
    inputHandler.mouseEntered(null);
    inputHandler.mouseReleased(null);
    inputHandler.mouseExited(null);
    assertFalse(InputHandler.isAnyKeyDown());
  }
}
