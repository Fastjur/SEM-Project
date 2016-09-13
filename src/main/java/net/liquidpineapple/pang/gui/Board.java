package net.liquidpineapple.pang.gui;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.screens.ControlsScreen;
import net.liquidpineapple.pang.screens.Level;
import net.liquidpineapple.pang.screens.MainMenu;
import net.liquidpineapple.pang.screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
@Slf4j
public class Board extends JPanel {

    private final int boardWidth;
    private final int boardHeight;
    private Timer timer;
    private Screen currentScreen;


    public Board(int boardWidth, int boardHeight) throws IOException {
        super();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        init();
    }

    private void init() throws IOException {
        setFocusable(true);
        setBackground(Color.BLACK);

        //currentScreen = Level.createFromXML("src/main/resources/levels/level1.xml");
        currentScreen = new ControlsScreen();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        currentScreen.doDrawing(g2d, this);
    }

    public void doUpdate() {
        this.currentScreen.doUpdate();
    }
}
