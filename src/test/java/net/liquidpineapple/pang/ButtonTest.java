package net.liquidpineapple.pang;

import net.liquidpineapple.pang.objects.buttons.Button;
import net.liquidpineapple.pang.objects.buttons.EasyButton;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InputHandler.class)
public class ButtonTest {

  private Button button;

  @Before
  public void setUp() throws Exception {
    button = PowerMockito.spy(new EasyButton(0,0));
    button.doUpdate();  //Update to get Clicked to false
    PowerMockito.mockStatic(InputHandler.class);
    Mockito.when(InputHandler.isLeftMouseButtonDown()).thenReturn(true);
  }

  @Test
  public void testDoUpdateMouseNotOnButton() throws Exception {
    //Check if the mouse is not on the button nothing happens.
    Mockito.when(InputHandler.getMousePos()).thenReturn(new Point(100, 100));
    button.doUpdate();
    PowerMockito.verifyPrivate(button, Mockito.times(0)).invoke("onClick");
  }

  @Test
  public void testDoUpdateMouseOnButton() throws Exception {
    //Check if the mouse is on the button onClick is called.
    Mockito.when(InputHandler.getMousePos()).thenReturn(new Point(0,0));
    PowerMockito.doNothing().when(button, "onClick");
    button.doUpdate();
    PowerMockito.verifyPrivate(button).invoke("onClick");
  }
}
