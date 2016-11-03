package net.liquidpineapple.pang;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;

import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.logger.LoggerTypes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.awt.Dimension;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.swing.*")
@PrepareForTest(Logger.class)
public class ApplicationTest {

  @Test
  public void testApplicationStart() throws Throwable {
    Application app = new Application();
    PropertiesHandler prophandler = Mockito.mock(PropertiesHandler.class);
    Mockito.when(prophandler.getProperty("application-width")).thenReturn("213");
    Mockito.when(prophandler.getProperty("application-height")).thenReturn("142");
    Mockito.when(prophandler.getProperty("application-name")).thenReturn("Test");
    Whitebox.getField(Application.class, "propertiesHandler").set(app, prophandler);
    
    app.start();
    assertEquals("Test", app.getTitle());
    assertEquals(new Dimension(213, 142), app.getSize());
  }

  @Test
  public void testHandleArgs() throws Throwable {
    PowerMockito.mockStatic(Logger.class);
    ArgumentCaptor<LoggerTypes> loglevel = ArgumentCaptor.forClass(LoggerTypes.class);
    PowerMockito.doNothing().when(Logger.class, "setLevel", loglevel.capture());

    Whitebox.invokeMethod(Application.class, "handleArgs", new Object[] {new String[] {"INFO"}});
    assertEquals(LoggerTypes.INFO, loglevel.getValue());
    Whitebox.invokeMethod(Application.class, "handleArgs", new Object[] {new String[] {"ERROR"}});
    assertEquals(LoggerTypes.ERROR, loglevel.getValue());
    Whitebox.invokeMethod(Application.class, "handleArgs", new Object[] {new String[] {"WARNING"}});
    assertEquals(LoggerTypes.WARNING, loglevel.getValue());
    Whitebox.invokeMethod(Application.class, "handleArgs", new Object[] {new String[] {"invalid"}});
    assertEquals(LoggerTypes.INFO, loglevel.getValue());
  }

  @Test
  public void testNotInCheatMode() throws Throwable {
    assertFalse("Hey there, I know our game is hard. But let's not push any code with cheat "
        + "mode on! Set the boolean 'cheatMode' in application to false!", Application.cheatMode);
  }
}
