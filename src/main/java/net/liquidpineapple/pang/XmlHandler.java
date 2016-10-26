package net.liquidpineapple.pang;

import net.liquidpineapple.pang.gui.NumberToken;
import net.liquidpineapple.pang.gui.ScoreSystem;
import net.liquidpineapple.pang.gui.TimeSystem;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.BallMovement;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.objects.playerschemes.Player1;
import net.liquidpineapple.pang.objects.playerschemes.Player2;
import net.liquidpineapple.pang.screens.LevelScreen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Jaap-Jan on 12-10-2016.
 * Class which handles the parsing to and from XML files.
 */
public class XmlHandler {

  /**
   * Method which parses a Level into a xml file.
   *
   * @param level - Level to be parsed
   */
  public static void createXmlFromLevel(LevelScreen level) {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // root elements
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("level");
      doc.appendChild(rootElement);

      // background element
      Element bg = doc.createElement("background");
      bg.appendChild(doc.createTextNode("bg.png"));
      rootElement.appendChild(bg);

      //difficulty element
      Element difficulty = doc.createElement("difficulty");
      difficulty.appendChild(doc.createTextNode("1"));
      rootElement.appendChild(difficulty);

      // objects
      Element objects = doc.createElement("objects");
      rootElement.appendChild(objects);

      // parse Balls
      parseBalls(objects, doc, level);

      //parse Players
      parsePlayer(objects, doc, level);

      //time element
      Element timeElement = doc.createElement("time");
      timeElement.appendChild(doc.createTextNode(Integer.toString(level.getTime())));
      objects.appendChild(timeElement);

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(
          LevelScreen.class.getResource("/levels/customlevel/level1.xml").getFile()));
      transformer.transform(source, result);

    } catch (ParserConfigurationException pce) {
      Logger.error(pce.getStackTrace().toString(), pce);
    } catch (TransformerException tfe) {
      Logger.error(tfe.getStackTrace().toString(), tfe);
    }

  }

  private static void parsePlayer(Element objects, Document doc, LevelScreen level) {
    for (GameObject object : level.objectList) {
      if (object instanceof Player) {
        Element player = doc.createElement("player");
        objects.appendChild(player);

        Element position = doc.createElement("position");
        player.appendChild(position);

        Element xelement = doc.createElement("x");
        int xposInt = (int) (object.getXpos() + 0.5);
        int yposInt = (int) (object.getYpos() + 0.5);
        xelement.appendChild(doc.createTextNode(Integer.toString(xposInt)));
        position.appendChild(xelement);
        Element yelement = doc.createElement("y");
        yelement.appendChild(doc.createTextNode(Integer.toString(yposInt)));
        position.appendChild(yelement);

      }
    }

  }

  private static void parseBalls(Element objects, Document doc, LevelScreen level) {
    for (GameObject object : level.objectList) {
      if (object instanceof Ball) {
        Element ball = doc.createElement("ball");
        objects.appendChild(ball);

        Element position = doc.createElement("position");
        ball.appendChild(position);

        int xposInt = (int) (object.getXpos() + 0.5);
        int yposInt = (int) (object.getYpos() + 0.5);

        Element xelement = doc.createElement("x");
        xelement.appendChild(doc.createTextNode(Integer.toString(xposInt)));
        position.appendChild(xelement);
        Element yelement = doc.createElement("y");
        yelement.appendChild(doc.createTextNode(Integer.toString(yposInt)));
        position.appendChild(yelement);

        Element direction = doc.createElement("direction");
        direction.appendChild(doc.createTextNode("LEFT_MOVEMENT"));
        ball.appendChild(direction);

        Element size = doc.createElement("size");
        size.appendChild(doc.createTextNode(Integer.toString(((Ball) object).getBallSize())));
        ball.appendChild(size);
      }
    }
  }

  /**
   * Method that parses a XML-file into a level.
   *
   * @param xmlFile - path/filename of the XML-file that should be parsed.
   * @return - returns a new level.
   */
  public static LevelScreen createFromXml(String xmlFile)
      throws IOException, ParserConfigurationException, SAXException {

    LevelScreen output = new LevelScreen();
    if (createFileReader(xmlFile) != null) {
      Document doc = createFileReader(xmlFile);
      String backgroundPath = "/sprites/"
          + doc.getElementsByTagName("background").item(0).getTextContent();
      output.setBackgroundImage(ImageIO.read(LevelScreen.class.getResource(backgroundPath)));

      int levelDifficulty = Integer.parseInt(doc.getElementsByTagName("difficulty")
          .item(0).getTextContent());
      output.setDifficulty(levelDifficulty);

      for (Ball ball : loadBalls(doc.getElementsByTagName("ball"))) {
        output.objectList.add(ball);
      }

      for (Player player : loadPlayer(doc.getElementsByTagName("player"))) {
        output.objectList.add(player);
      }

      output.setTime(loadTime(doc));
      TimeSystem.resetTime(loadTime(doc));

      for (NumberToken token : TimeSystem.getTimePlaces()) {
        output.getHudObjectList().add(token);
      }

    }
    for (NumberToken token : ScoreSystem.getPlaces()) {
      output.getHudObjectList().add(token);
    }
    Application.lifeSystem.updateLifes();
    output.getHudObjectList().add(Application.lifeSystem);
    return output;
  }

  /**
   * Method that reads the balls from the XML-file and parses them into Ball objects.
   *
   * @param ballList - NodeList of the different balls to be parsed.
   * @return - Arraylist with Ball objects.
   */
  public static ArrayList<Ball> loadBalls(NodeList ballList) {
    ArrayList<Ball> ballArray = new ArrayList<>();

    for (int i = 0; i < ballList.getLength(); i++) {
      Node ballNode = ballList.item(i);
      Element ballElement = (Element) ballNode;

      int intXpos = Integer.parseInt(ballElement.getElementsByTagName("x")
          .item(0).getTextContent());
      int intYpos = Integer.parseInt(ballElement.getElementsByTagName("y")
          .item(0).getTextContent());

      int size = Integer.parseInt(ballElement.getElementsByTagName("size").item(0)
          .getTextContent());

      String direction = ballElement.getElementsByTagName("direction").item(0).getTextContent();
      BallMovement ballMovement = BallMovement.valueOf(direction);
      Ball ball = new Ball(intXpos, intYpos, ballMovement, size);
      ballArray.add(ball);
    }
    return ballArray;
  }

  /**
   * Method that reads the players from the XML-file and parses them into Player objects.
   * Simular to the loadBalls method
   *
   * @param playerlist - NodeList of the players that should be parsed.
   * @return - ArrayList of Player objects.
   */
  public static ArrayList<Player> loadPlayer(NodeList playerlist) {
    ArrayList<Player> playerArray = new ArrayList<>();
    for (int i = 0; i < playerlist.getLength(); i++) {
      Node playernode = playerlist.item(i);
      Element playerElement = (Element) playernode;

      int intXpos = Integer.parseInt(playerElement.getElementsByTagName("x")
          .item(0).getTextContent());
      int intYpos = Integer.parseInt(playerElement.getElementsByTagName("y")
          .item(0).getTextContent());

      if (i == 0) {
        playerArray.add(new Player(intXpos, intYpos, new Player1()));
      } else if (i == 1 && Application.multiplayer) {
        playerArray.add(new Player(intXpos, intYpos, new Player2()));
      }
    }
    return playerArray;
  }

  /**
   * Method to read time form an XML-file.
   *
   * @param doc {@link Document} to be read from
   * @return - returns an int contained in the {@code <time></time>}
   */
  public static int loadTime(Document doc) {
    return Integer.parseInt(doc.getElementsByTagName("time").item(0).getTextContent());
  }

  /**
   * Method to create a FileReader to read the XML-file.
   *
   * @param xmlFile - Path/name of the XML-file to be parsed.
   * @return - returns a new FileReader if no exception is thrown, else it will return null.
   */
  public static Document createFileReader(String xmlFile)
      throws ParserConfigurationException, SAXException {

    DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    InputStream in = LevelScreen.class.getResourceAsStream(xmlFile);
    try {
      return documentBuilder.parse(in);
    } catch (IOException | IllegalArgumentException ex) {
      //TODO Fix this shit
      ex.printStackTrace();
      return null;
    }
  }
}
