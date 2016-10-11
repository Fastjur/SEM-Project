package net.liquidpineapple.pang;

import net.liquidpineapple.pang.objects.Ball;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import net.liquidpineapple.pang.screens.Level;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Jaap-Jan on 12-10-2016.
 */
public class xmlHandler {
  public static void createXmlFromLevel(Level level, int time){
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

      // objects
      Element objects = doc.createElement("objects");
      rootElement.appendChild(objects);

      for(GameObject object : level.objectList){
        if(object instanceof Ball){
          Element ball = doc.createElement("ball");
          objects.appendChild(ball);

          Element position = doc.createElement("position");
          ball.appendChild(position);

          Element x = doc.createElement("x");
          x.appendChild(doc.createTextNode(Double.toString(object.getXpos())));
          position.appendChild(x);
          Element y = doc.createElement("y");
          y.appendChild(doc.createTextNode(Double.toString(object.getYpos())));
          position.appendChild(y);

          Element direction = doc.createElement("direction");
          direction.appendChild(doc.createTextNode("LEFT_MOVEMENT"));
          ball.appendChild(direction);

          Element size = doc.createElement("size");
          size.appendChild(doc.createTextNode(Integer.toString(((Ball) object).getBallSize())));
          ball.appendChild(size);
        }
      }

      for(GameObject object : level.objectList){
        if(object instanceof Player){
          Element player = doc.createElement("player");
          objects.appendChild(player);

          Element position = doc.createElement("position");
          player.appendChild(position);

          Element x = doc.createElement("x");
          x.appendChild(doc.createTextNode(Double.toString(object.getXpos())));
          position.appendChild(x);
          Element y = doc.createElement("y");
          y.appendChild(doc.createTextNode(Double.toString(object.getYpos())));
          position.appendChild(y);

        }
      }

      Element timeElement = doc.createElement("time");
      timeElement.appendChild(doc.createTextNode(Integer.toString(time)));
      objects.appendChild(timeElement);

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(
          Level.class.getResource("/levels/userMadeLevels/userlevel.xml").getFile()));
      transformer.transform(source, result);

    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }

  }
}
