package net.liquidpineapple.pang.objects;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Govert de Gans
 * @date 2016-11-03
 */
public class CountdownTest {

  private Countdown countdown;
  private Field start = Whitebox.getField(Countdown.class, "start");

  @Before
  public void setUp() {
    countdown = new Countdown(0,0);
  }

  @Test
  public void testDoUpdateStart() {
    assertEquals(3, countdown.getCurrentNum());
    countdown.doUpdate();
    assertEquals(3, countdown.getCurrentNum());
  }

  @Test
  public void testDoUpdateTwo() throws Exception {
    start.set(countdown, Instant.now().minus(1, ChronoUnit.SECONDS));
    countdown.doUpdate();
    assertEquals(2, countdown.getCurrentNum());
  }

  @Test
  public void testDoUpdateOne() throws Exception {
    start.set(countdown, Instant.now().minus(2, ChronoUnit.SECONDS));
    countdown.doUpdate();
    assertEquals(1, countdown.getCurrentNum());
  }
}
