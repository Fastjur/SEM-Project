package net.liquidpineapple.pang.objects;

import static org.junit.Assert.assertEquals;

import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Erik on 13-9-2016.
 */
public class ScoreSystemTest {

  ScoreSystem scoreSystem;

  @Before
  public void setUp() throws Exception {
    scoreSystem = ScoreSystem.getInstance();
    scoreSystem.resetScore();
  }

  @Test
  public void constructorTest() {
    assertEquals(0, ScoreSystem.getScore());
    for (NumberToken token : ScoreSystem.getPlaces()) {
      assertEquals(0, token.getCurrentnumber());
    }
  }

  @Test
  public void addTest() {
    ScoreSystem.addScore(500);
    assertEquals(500, ScoreSystem.getScore());

    ScoreSystem.addScore(356);
    assertEquals(856, ScoreSystem.getScore());

    scoreSystem.displayscore();
    assertEquals(6, ScoreSystem.getPlaces().get(0).getCurrentnumber());
    assertEquals(5, ScoreSystem.getPlaces().get(1).getCurrentnumber());
    assertEquals(8, ScoreSystem.getPlaces().get(2).getCurrentnumber());
  }

}
