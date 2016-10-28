package net.liquidpineapple.pang.objects;

import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.gui.LifeSystem;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 13-9-2016.
 */
public class LifeSystemTest {

  @Before
  public void setUp() {
    LifeSystem.getInstance();
  }

  @Test
  public void gainliveat3() {
    while (LifeSystem.getLives() < 3) {
      LifeSystem.gainLife();
    }
    LifeSystem.gainLife();
    assertEquals(LifeSystem.getLives(), 3);
  }

  @Test
  public void loseliveat3Andthengain() {
    while (LifeSystem.getLives() < 3) {
      LifeSystem.gainLife();
    }
    LifeSystem.loseLife();
    assertEquals(LifeSystem.getLives(), 2);
    LifeSystem.gainLife();
    assertEquals(LifeSystem.getLives(), 3);
  }

  @Test
  public void loseliveAndReset() {
    while (LifeSystem.getLives() < 3) {
      LifeSystem.gainLife();
    }
    LifeSystem.loseLife();
    assertEquals(LifeSystem.getLives(), 2);
    LifeSystem.getInstance().resetLives();
    assertEquals(LifeSystem.getLives(), 3);
  }


}
