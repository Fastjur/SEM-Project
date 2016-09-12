package net.liquidpineapple.pang.gui;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.screens.MainMenu;
import net.liquidpineapple.pang.screens.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
@Slf4j
public class Board extends JPanel {

    private final int boardWidth;
    private final int boardHeight;
    private Timer timer;
    public static Screen currentScreen;

    public Board(int boardWidth, int boardHeight) {
        super();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        init();
    }

    private void init() {
        setFocusable(true);
        setBackground(Color.BLACK);

        currentScreen = new MainMenu();
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
