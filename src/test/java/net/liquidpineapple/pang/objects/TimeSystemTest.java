package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.TimeSystem;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;


import java.sql.Time;

import static org.junit.Assert.assertEquals;

/**
 * Created by Erik on 13-9-2016.
 */
public class TimeSystemTest {

  @Before
  public void setUp() {
    TimeSystem.getInstance();
    TimeSystem.start();

  }

  @Test
  public void TestsetFrozenTime() {
    TimeSystem.getInstance().setFrozen(0);
    assertEquals(0, TimeSystem.getFrozen());
    TimeSystem.getInstance().setFrozen(10);
    assertEquals(10, TimeSystem.getFrozen());
  }

  @Test
  public void TeststopTime() {
    TimeSystem.stop();
    int oldTime = TimeSystem.getTime();
    assertEquals(oldTime, TimeSystem.getTime());
    TimeSystem.start();
  }

  @Test
  public void TestResetTime() {
    TimeSystem.stop();
    TimeSystem.setTime(25);
    TimeSystem.resetTime(111);
    for (NumberToken token : TimeSystem.getTimePlaces()) {
      assertEquals(1, token.getCurrentnumber());
    }
    TimeSystem.start();
  }

  @Test
  //Tests if it properly handles time > 999
  public void TestResetTimeOver999() {
    TimeSystem.stop();
    TimeSystem.setTime(25);
    TimeSystem.resetTime(1110);
    for (NumberToken token : TimeSystem.getTimePlaces()) {
      assertEquals(9, token.getCurrentnumber());
    }
    TimeSystem.start();
  }

  @Test
  public synchronized void TestTimeUp() throws Exception {
    TimeSystem.stop();
    TimeSystem.setTime(0);
    Whitebox.invokeMethod(TimeSystem.getInstance(), "timeTick");
    assertEquals(60, TimeSystem.getTime());
  }

}
