package net.liquidpineapple.pang.screens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.liquidpineapple.pang.XmlHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Tim on 20-10-2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(XmlHandler.class)
public class InsaneLevelsTest {

  private InsaneLevels insaneLevels;

  /**
   * sets up tests.
   */
  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(XmlHandler.class);
    LevelScreen level = new LevelScreen();
    level.setDifficulty(1);
    Mockito.when(XmlHandler.createFromXml(Mockito.anyString())).thenReturn(level);
    insaneLevels = new InsaneLevels();
  }

  @Test
  public void testArrayIsFilled() throws Exception {

    assertTrue(insaneLevels.createIterator().next() != null);
  }

  @Test
  public void testArrayContainsLevels() {
    assertTrue(insaneLevels.createIterator().next() instanceof LevelScreen);
  }

  @Test
  public void testLevelContainsData() {
    assertEquals(1, ((LevelScreen) insaneLevels.createIterator().next()).getDifficulty());
  }

}
