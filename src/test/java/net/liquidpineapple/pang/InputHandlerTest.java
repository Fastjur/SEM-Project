package net.liquidpineapple.pang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.HashSet;

/**
 * @author Govert de Gans
 * @date 2016-10-20
 */
@SuppressWarnings("unchecked")
public class InputHandlerTest {

  InputHandler inputHandler;
  static Field keysDown = Whitebox.getField(InputHandler.class, "keysDown");
  static Field keysPressed = Whitebox.getField(InputHandler.class, "keysPressed");
  static Field mouseButtonPressed = Whitebox.getField(InputHandler.class, "mouseButtonPressed");
  static Field mousePos = Whitebox.getField(InputHandler.class, "mousePos");

  @Before
  public void setUp() throws Exception {
    inputHandler = new InputHandler();
    InputHandler.clearState();
    InputHandler.clearKeys();
  }

  @Test
  public void testKeyPressed() throws Exception {
    assertEquals(0, ((HashSet)keysDown.get(null)).size());
    assertEquals(0, ((HashSet)keysPressed.get(null)).size());

    inputHandler.keyPressed(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_0));

    assertEquals(1, ((HashSet)keysDown.get(null)).size());
    assertEquals(1, ((HashSet)keysPressed.get(null)).size());
    assertTrue(((HashSet)keysPressed.get(null)).contains(KeyEvent.VK_0));
    assertTrue(((HashSet)keysDown.get(null)).contains(KeyEvent.VK_0));
  }

  @Test
  public void testKeyReleased() throws Exception {
    ((HashSet)keysDown.get(null)).add(KeyEvent.VK_1);
    ((HashSet)keysPressed.get(null)).add(KeyEvent.VK_1);

    inputHandler.keyReleased(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_1));

    assertEquals(0, ((HashSet)keysDown.get(null)).size());
    assertEquals(0, ((HashSet)keysPressed.get(null)).size());
  }

  @Test
  public void testKeyTyped() throws Exception {
    assertEquals(0, ((HashSet)keysDown.get(null)).size());
    assertEquals(0, ((HashSet)keysPressed.get(null)).size());

    inputHandler.keyTyped(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_2));

    assertEquals(0, ((HashSet)keysDown.get(null)).size());
    assertEquals(0, ((HashSet)keysPressed.get(null)).size());
  }

  @Test
  public void testMouseClicked() throws Exception {
    inputHandler.mouseClicked(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 1));
    assertEquals(0, (int)mouseButtonPressed.get(null));
  }

  @Test
  public void testMousePressed() throws Exception {
    inputHandler.mousePressed(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 1));
    assertEquals(1, (int)mouseButtonPressed.get(null));
  }

  @Test
  public void testMouseReleased() throws Exception {
    mouseButtonPressed.set(null, 3);
    inputHandler.mouseReleased(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 3));
    assertEquals(0, (int)mouseButtonPressed.get(null));
  }

  @Test
  public void testMouseEnteredExited() throws Exception {
    inputHandler.mouseEntered(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 3));
    assertEquals(0, (int)mouseButtonPressed.get(null));
    inputHandler.mouseExited(new MouseEvent(new Component() {}, 0, 0, 0, 1, 2, 3, false, 3));
    assertEquals(0, (int)mouseButtonPressed.get(null));
  }

  @Test
  public void testIsKeyDown() throws Exception {
    assertFalse(InputHandler.isKeyDown(KeyEvent.VK_4));
    ((HashSet)keysDown.get(null)).add(KeyEvent.VK_4);
    assertTrue(InputHandler.isKeyDown(KeyEvent.VK_4));
  }

  @Test
  public void testIsKeyPressed() throws Exception {
    assertFalse(InputHandler.isKeyPressed(KeyEvent.VK_5));
    ((HashSet)keysPressed.get(null)).add(KeyEvent.VK_5);
    assertTrue(InputHandler.isKeyPressed(KeyEvent.VK_5));
  }

  @Test
  public void testClearKeys() throws Exception {
    ((HashSet)keysPressed.get(null)).add(KeyEvent.VK_6);
    InputHandler.clearKeys();
    assertEquals(0, ((HashSet)keysPressed.get(null)).size());
  }

  @Test
  public void testIsAnyKeyPressed() throws Exception {
    assertFalse(InputHandler.isAnyKeyPressed());
    ((HashSet)keysDown.get(null)).add(KeyEvent.VK_7);
    assertTrue(InputHandler.isAnyKeyPressed());
  }

  @Test
  public void testIsLeftMouseButtonDown() throws Exception {
    assertFalse(InputHandler.isLeftMouseButtonDown());
    mouseButtonPressed.set(null, 1);
    assertTrue(InputHandler.isLeftMouseButtonDown());
  }

  @Test
  public void testIsRightMouseButtonDown() throws Exception {
    assertFalse(InputHandler.isRightMouseButtonDown());
    mouseButtonPressed.set(null, 3);
    assertTrue(InputHandler.isRightMouseButtonDown());
  }

  @Test
  public void testIsMiddleMouseButtonDown() throws Exception {
    assertFalse(InputHandler.isMiddleMouseButtonDown());
    mouseButtonPressed.set(null, 2);
    assertTrue(InputHandler.isMiddleMouseButtonDown());
  }

  @Test
  public void testGetMousePos() throws Exception {
    Point pt = new Point(15, 344);
    mousePos.set(null, pt);
    assertEquals(pt, InputHandler.getMousePos());
  }

  @Test
  public void testClearState() throws Exception {
    mouseButtonPressed.set(null, 2);
    ((HashSet)keysDown.get(null)).add(KeyEvent.VK_8);
    InputHandler.clearState();
    assertEquals(0, ((HashSet)keysDown.get(null)).size());
    assertEquals(0, (int)mouseButtonPressed.get(null));
  }
}
