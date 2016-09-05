import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date: 05 09 2016.
 */
public class HelloWorldTest {

    @Test
    public void TestTravis() {
        HelloWorld helloWorld = new HelloWorld();
        assertEquals("Hello world!", helloWorld.testTravis());
    }
}
