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
 * Created by Tim on 14-10-2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(XmlHandler.class)
public class UserCreatedLevelsTest {

  private UserCreatedLevels userCreatedLevels;

  /**
   * sets up tests.
   */
  @Before
  public void setUp() throws Exception {
    PowerMockito.mockStatic(XmlHandler.class);
    LevelScreen level = new LevelScreen();
    level.setDifficulty(1);
    Mockito.when(XmlHandler.createFromXml(Mockito.anyString())).thenReturn(level);
    userCreatedLevels = new UserCreatedLevels();
  }

  @Test
  public void testArrayIsFilled() throws Exception {

    assertTrue(userCreatedLevels.createIterator().next() != null);
  }

  @Test
  public void testArrayContainsLevels() {
    assertTrue(userCreatedLevels.createIterator().next() instanceof LevelScreen);
  }

  @Test
  public void testLevelContainsData() {
    assertEquals(1, ((LevelScreen) userCreatedLevels.createIterator().next()).getDifficulty());
  }
}
