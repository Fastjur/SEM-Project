package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.objects.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.*;
import java.lang.reflect.Array;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
@Slf4j
public class Level extends Screen {

    private static final int BOTTOM_OFFSET = 10;

    public Level() {

        //currentLevel = Level.createFromXML("level1.xml");

        Player player = new Player(1, 1, 600);
        objectList.add(player);
        player.setPos(
            51, 20
        );
    }

    public static Level createFromXML(String xmlFile) {

        Level output = new Level();
        if (createFileReader(xmlFile) != null) {
            Document doc = createFileReader(xmlFile);
            try {
                NodeList balllist = doc.getElementsByTagName("ball");
                NodeList playerlist = doc.getElementsByTagName("player");

                for(int i =0; i<balllist.getLength(); i++){
                    Node ball = balllist.item(i);
                    Element ballElement = (Element) ball;
                    NodeList position = ballElement.getElementsByTagName("position");
                    int x_pos = Integer.getInteger(position.item(0).getTextContent());
                    int y_pos = Integer.getInteger(position.item(1).getTextContent());
                    String direction = ballElement.getElementsByTagName("direction").item(0).getTextContent();
                    int size = Integer.getInteger(ballElement.getElementsByTagName("size").item(0).getTextContent());
                    String color = ballElement.getElementsByTagName("color").item(0).getTextContent();
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public static Document createFileReader(String xmlFile) {
        try {
            File inputFile = new File(xmlFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            return doc;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void doUpdate(){
        int x = 0;
        while(x<100) {
            for (GameObject object : objectList) {
                object.doUpdate();
            }
            x++;
        }
    }
}
