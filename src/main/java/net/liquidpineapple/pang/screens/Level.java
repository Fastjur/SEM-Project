package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.BallMovement;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
public class Level extends Screen {

    public Level() {
    }

    /**
     * Method that parses a XML-file into a level.
     * @param xmlFile - path/filename of the XML-file that should be parsed.
     * @return - returns a new level.
     * @throws IOException
     */
    public static Level createFromXML(String xmlFile) throws IOException, ParserConfigurationException, SAXException {

        Level output = new Level();
        if (createFileReader(xmlFile) != null) {
            Document doc = createFileReader(xmlFile);
            String background_path = "/sprites/" + doc.getElementsByTagName("background").item(0).getTextContent();
            output.backgroundImage = ImageIO.read(Level.class.getResource(background_path));

            for (Ball ball : loadBalls(doc.getElementsByTagName("ball"))) {
                output.objectList.add(ball);
            }

            for (Player player : loadPlayer(doc.getElementsByTagName("player"))) {
                output.objectList.add(player);
            }
            TimeSystem.resetTime(loadTime(doc));

            for (NumberToken token: TimeSystem.getTimePlaces()){
                output.objectList.add(token);
            }

        }
        for (NumberToken token : ScoreSystem.getPlaces()) {
            output.objectList.add(token);
        }
        Application.lifeSystem.updateLifes();
        output.objectList.add(Application.lifeSystem);
        return output;
    }

    /**
     * Method that reads the balls from the XML-file and parses them into Ball objects.
     * @param ballList - NodeList of the different balls to be parsed.
     * @return - Arraylist with Ball objects.
     */
    public static ArrayList<Ball> loadBalls(NodeList ballList){
        ArrayList<Ball> ballArray = new ArrayList<>();

        for (int i = 0; i < ballList.getLength(); i++) {
            Node ballNode = ballList.item(i);
            Element ballElement = (Element) ballNode;

            int int_Xpos = Integer.parseInt(ballElement.getElementsByTagName("x").item(0).getTextContent());
            int int_Ypos = Integer.parseInt(ballElement.getElementsByTagName("y").item(0).getTextContent());

            int size = Integer.parseInt(ballElement.getElementsByTagName("size").item(0)
                .getTextContent());

            String direction = ballElement.getElementsByTagName("direction").item(0).getTextContent();
            BallMovement ballMovement = BallMovement.valueOf(direction);
            Ball ball = new Ball(int_Xpos, int_Ypos, ballMovement, size);
            ballArray.add(ball);
        }
        return ballArray;
    }

    /**
     * Method that reads the players from the XML-file and parses them into Player objects.
     * Simular to the loadBalls method
     * @param playerlist - NodeList of the players that should be parsed.
     * @return - ArrayList of Player objects.
     */
    public static ArrayList<Player> loadPlayer(NodeList playerlist){
        ArrayList<Player> playerArray = new ArrayList<>();
        for (int i = 0; i < playerlist.getLength(); i++){
            Node playernode = playerlist.item(i);
            Element playerElement = (Element) playernode;

            int int_Xpos = Integer.parseInt(playerElement.getElementsByTagName("x").item(0).getTextContent());
            int int_Ypos = Integer.parseInt(playerElement.getElementsByTagName("y").item(0).getTextContent());

            Player player = new Player(int_Xpos, int_Ypos);
            playerArray.add(player);
        }
        return playerArray;
    }

    /**
     * method to read time form an XML-file
     * @param doc {@link Document} to be read from
     * @return - returns an int contained in the {@code <time></time>}
     */
    public static int loadTime(Document doc){
        return Integer.parseInt(doc.getElementsByTagName("time").item(0).getTextContent());
    }

    /**
     * Method to create a FileReader to read the XML-file.
     * @param xmlFile - Path/name of the XML-file to be parsed.
     * @return - returns a new FileReader if no exception is thrown,
     * else it will return null.
     */
    public static Document createFileReader(String xmlFile) throws ParserConfigurationException, SAXException {
        InputStream in = Level.class.getResourceAsStream(xmlFile);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        try {
            return documentBuilder.parse(in);
        } catch (IOException | IllegalArgumentException e) {
            //TODO Fix this shit
            e.printStackTrace();
            return null;
        }
    }

    @Override
    /**
     * Update method for the level class.
     * Checks if the level is completed and if so, it loads a new level.
     * Updates all components in level when the level is not ended.
     */
    public void doUpdate() {
        boolean levelEnded = true;
        for(GameObject object: objectList) {
            if (object instanceof Ball) {
                levelEnded = false;
            }
        }

        if(levelEnded){
            try {
                nextLevel();
            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        } else {
            new ArrayList<>(objectList).forEach(GameObject::doUpdate);
        }
    }

    /**
     * Attempst to load the new level (WIP)
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    private void nextLevel() throws ParserConfigurationException, SAXException {
        Board currentBoard = Application.getBoard();
        currentBoard.setLevelCount(currentBoard.getLevelCount()+1);
        Screen newScreen = null;
        String levelLocation = "/levels/level" + currentBoard.getLevelCount() + ".xml";
        if(createFileReader(levelLocation) != null){
            try {
                newScreen = Level.createFromXML("/levels/level" + currentBoard.getLevelCount() + ".xml");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            //set behaviour when all levels have been completed here.
            newScreen = new WinScreen();
        }
        currentBoard.changeScreen(newScreen);
    }
}
