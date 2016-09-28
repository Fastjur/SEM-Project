package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.gui.LifeSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 13-9-2016.
 */
public class LifeSystemTest {

  @Before
  public void setUp() {
    LifeSystem.getInstance();
  }

  @Test
  public void Gainliveat3() {
    while (LifeSystem.getLives() < 3) {
      LifeSystem.gainLife();
    }
    LifeSystem.gainLife();
    assertEquals(LifeSystem.getLives(), 3);
  }

  @Test
  public void Loseliveat3andthengain() {
    while (LifeSystem.getLives() < 3) {
      LifeSystem.gainLife();
    }
    LifeSystem.loseLife();
    assertEquals(LifeSystem.getLives(), 2);
    LifeSystem.gainLife();
    assertEquals(LifeSystem.getLives(), 3);
  }


}
