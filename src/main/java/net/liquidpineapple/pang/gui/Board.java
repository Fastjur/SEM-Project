package net.liquidpineapple.pang.gui;

import javax.swing.*;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
public class Board extends JPanel {

    private JPanel panel = new JPanel();

    public Board() {
        initBoard();
    }

    public void initBoard(){
        new StartMenu().addMenu(panel);
    }

    public JPanel getPanel(){
        return panel;
    }
}
