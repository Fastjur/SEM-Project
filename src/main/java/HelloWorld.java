/**
 * @author: Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date: 05 09 2016.
 */
public class HelloWorld {

    protected String testTravis() {
        return "Hello world!";
    }

    public HelloWorld() {}

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        System.out.println(helloWorld.testTravis());
    }

}
