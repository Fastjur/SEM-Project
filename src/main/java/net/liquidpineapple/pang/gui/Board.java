package net.liquidpineapple.pang.gui;

import javax.swing.*;
import java.awt.*;

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
        JPanel overlay = new JPanel();
        overlay.setLayout(new OverlayLayout(overlay));
        //drawBackground(overlay);
        new StartMenu().addMenu(overlay);
        panel.add(overlay);

    }

    public JPanel getPanel(){
        return panel;
    }

    public void drawBackground(JPanel panel){

        ImageIcon buttonimage = new ImageIcon(getClass().getResource("/images/background.png"));
        JLabel button = new JLabel(buttonimage);
        button.setPreferredSize( new Dimension(800, 600));
        panel.add(button);
    }
}
