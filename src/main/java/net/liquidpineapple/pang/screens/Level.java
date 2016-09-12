package net.liquidpineapple.pang.screens;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.objects.Ball;
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
import java.util.ArrayList;

/**
 * @author Govert de Gans
 * @date 2016-09-07
 */
@Slf4j
public class Level extends Screen {

    private static final int BOTTOM_OFFSET = 10;

    public Level() {
    }

    public static Level createFromXML(String xmlFile) {

        Level output = new Level();
        if (createFileReader(xmlFile) != null) {
            Document doc = createFileReader(xmlFile);
            try {
                NodeList balllist = doc.getElementsByTagName("ball");
                NodeList playerlist = doc.getElementsByTagName("player");
                for (Ball ball : loadBalls(balllist)) {
                    output.objectList.add(ball);
                }

                for (Player player : loadPlayer(playerlist)) {
                    output.objectList.add(player);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public static ArrayList<Ball> loadBalls(NodeList balllist){
        ArrayList<Ball> ballArray = new ArrayList<Ball>();
        for(int i =0; i<balllist.getLength(); i++){
            Node ballNode = balllist.item(i);
            Element ballElement = (Element) ballNode;
            int int_Xpos = Integer.parseInt(ballElement.getElementsByTagName("x").item(0).getTextContent());
            int int_Ypos = Integer.parseInt(ballElement.getElementsByTagName("y").item(0).getTextContent());
            String direction = ballElement.getElementsByTagName("direction").item(0).getTextContent();
            int size = Integer.parseInt(ballElement.getElementsByTagName("size").item(0).getTextContent());
            String color = ballElement.getElementsByTagName("color").item(0).getTextContent();
            Ball.Directions directions = Ball.Directions.LEFT;
            Ball ball = new Ball(int_Xpos, int_Ypos, directions, size);
            ballArray.add(ball);
        }
        return ballArray;
    }

    public static ArrayList<Player> loadPlayer(NodeList playerlist){
        ArrayList<Player> playerArray = new ArrayList<Player>();
        for(int i =0; i<playerlist.getLength(); i++){
            Node playernode = playerlist.item(i);
            Element playerElement = (Element) playernode;
            int int_Xpos = Integer.parseInt(playerElement.getElementsByTagName("x").item(0).getTextContent());
            int int_Ypos = Integer.parseInt(playerElement.getElementsByTagName("y").item(0).getTextContent());
            int int_MaxX = Integer.parseInt(playerElement.getElementsByTagName("maxX").item(0).getTextContent());
            Player player = new Player(int_Xpos, int_Ypos, int_MaxX);
            playerArray.add(player);
        }
        return playerArray;
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
