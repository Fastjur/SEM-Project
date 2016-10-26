package net.liquidpineapple.pang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.screens.LevelEditor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.awt.event.KeyEvent;


@RunWith(PowerMockRunner.class)
@PrepareForTest(InputHandler.class)
public class LevelEditorTest {
  private LevelEditor levelEditor;

  @Before
  public void setUp() {
    levelEditor = PowerMockito.spy(new LevelEditor());

  }

  @Test
  public void testPlaceBallSize1() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_1)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 1);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 1);
  }

  @Test
  public void testPlaceBallSize2() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_2)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 2);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 2);
  }

  @Test
  public void testPlaceBallSize3() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_3)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 3);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 3);
  }

  @Test
  public void testPlaceBallSize4() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_4)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addBall", 4);
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor).invoke("addBall", 4);
  }

  @Test
  public void testAddBall() throws Exception {
    Whitebox.invokeMethod(levelEditor, "addBall", 1);
    assertEquals(levelEditor.addedObjects.size(), 1);
    assertEquals(((Ball) levelEditor.addedObjects.get(0)).getBallSize(), 1);
  }

  @Test
  public void testPlacePlayer() throws Exception {
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isKeyDown(KeyEvent.VK_5)).thenReturn(true);
    PowerMockito.doNothing().when(levelEditor, "addPlayer");
    levelEditor.doUpdate();
    PowerMockito.verifyPrivate(levelEditor, times(1)).invoke("addPlayer");
  }

  @Test
  public void testAddPlayer() throws Exception {
    Whitebox.invokeMethod(levelEditor, "addPlayer");
    assertEquals(1, levelEditor.addedObjects.size());
    assertTrue(levelEditor.addedObjects.get(0) instanceof Player);
  }
}
