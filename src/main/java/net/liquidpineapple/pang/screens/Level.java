package net.liquidpineapple.pang.screens;

import net.liquidpineapple.pang.Application;

import net.liquidpineapple.pang.gui.Board;

import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.gui.TimeSystem;

import net.liquidpineapple.pang.objects.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
public class Level extends Screen {

    private static final int BOTTOM_OFFSET = 10;

    public Level() {
    }

    /**
     * Method that parses a XML-file into a level.
     * @param xmlFile - path/filename of the XML-file that should be parsed.
     * @return - returns a new level.
     * @throws IOException
     */
    public static Level createFromXML(String xmlFile) throws IOException {

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
        output.objectList.add(Application.lifeKeeper);
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
            String color = ballElement.getElementsByTagName("color").item(0).getTextContent();

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
    public static Document createFileReader(String xmlFile) {
        try {
            File inputFile = new File(xmlFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            return doc;

        } catch (Exception e) {
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
            Board currentBoard = Application.getBoard();
            currentBoard.setLevelCount(currentBoard.getLevelCount()+1);
            Screen newScreen = null;
            String levelLocation= "src/main/resources/levels/level" + currentBoard.getLevelCount() + ".xml";
            if(createFileReader(levelLocation) != null){
                try {
                    newScreen = Level.createFromXML("src/main/resources/levels/level" + currentBoard.getLevelCount() + ".xml");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            } else{
                //set behaviour when all levels have been completed here.
                newScreen = new WinScreen();
            }
            currentBoard.changeScreen(newScreen);
        } else {
            for(GameObject object : new ArrayList<GameObject>(objectList)) {
                object.doUpdate();
            }
        }
    }
}
