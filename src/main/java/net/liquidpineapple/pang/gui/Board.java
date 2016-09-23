package net.liquidpineapple.pang.gui;

import lombok.Getter;
import net.liquidpineapple.pang.InputHandler;
import net.liquidpineapple.pang.logger.Logger;
import net.liquidpineapple.pang.objects.GameObject;
import net.liquidpineapple.pang.screens.MainMenu;
import net.liquidpineapple.pang.screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Class that represents the board.
 * @author Jurriaan Den Toonder
 * @date 2016/09/06.
 */
public class Board extends JPanel {

    private final int boardWidth;
    private final int boardHeight;
    private Timer timer;
    @Getter
    private Screen currentScreen;

    /**
     * Constructor of the board.
     * @param boardWidth - The width of the board.
     * @param boardHeight - The Height of the board.
     * @throws IOException - Throws exception.
     */
    public Board(int boardWidth, int boardHeight) throws IOException {
        super();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setFocusable(true);
        requestFocus();
        addKeyListener(new InputHandler());
        addMouseListener(new InputHandler());
        init();
    }

    /**
     * Method that initializes the board.
     * @throws IOException - Throws exception.
     */
    private void init() throws IOException {
        setFocusable(true);
        setBackground(Color.BLACK);

      //  currentScreen = Level.createFromXML("src/main/resources/levels/level1.xml");
        currentScreen = new MainMenu();
        Logger.info("MainMenu Created.");
    }

    /**
     * Method that makes changing the screen possible.
     * @param screen - The screen that needs to be shown.
     */
    public void changeScreen(Screen screen){
        currentScreen = screen;
        Logger.info("Screen changed to:" + screen);
        this.repaint();
    }

    /**
     * Overrides the PaintComponent method of JComponent.
     * @param g - The graphics object to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Method that draws a graphics2D object.
     * @param g - The graphics object to draw.
     */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        currentScreen.doDrawing(g2d, this);
    }

    /**
     * Method that updates the screen.
     */
    public void doUpdate() {
        this.currentScreen.doUpdate();
    }

    /**
     * Method that makes the addition of gameObjects possible.
     * @param o - The object to add.
     */
    public synchronized void addObject(GameObject o){
        currentScreen.objectList.add(o);
    }

    /**
     * A method that checks if an object is in the current screen.
     * @param o - The object to check.
     * @return an boolean that is true if the object is in the screen, false otherwise.
     */
    public synchronized boolean containsObject(GameObject o){
        return currentScreen.objectList.contains(o);
    }
}
