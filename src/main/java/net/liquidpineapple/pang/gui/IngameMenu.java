package net.liquidpineapple.pang.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tim on 7-9-2016.
 */
public class IngameMenu implements Menu {

    @Override
    public void draw(JPanel panel){
        panel.removeAll();
        JPanel menuLayout = new JPanel();
        menuLayout.setLayout(new BorderLayout());
        addButton("/images/W.png", menuLayout, "PAGE_START");
        addButton("/images/A.png", menuLayout, "LINE_START");
        addButton("/images/D.png", menuLayout, "LINE_END");

        JLabel center_placeholder = new JLabel("                                       ");
        menuLayout.add(center_placeholder, BorderLayout.CENTER);

        JLabel playText = new JLabel("PRESS ANY KEY TO PLAY");
        menuLayout.add(playText, BorderLayout.PAGE_END);

        panel.add(menuLayout);

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                System.out.print("GAME STARTS NOW");
                //GAME.START();

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public Component getComponent() {
        return null;
    }

    @Override
    public void addButton(String filename, JPanel panel, String option){
        ImageIcon buttonImage = new ImageIcon(getClass().getResource(filename));
        JLabel label = new JLabel(buttonImage);
        switch(option){
            case "PAGE_START":
                panel.add(label, BorderLayout.PAGE_START);
                break;
            case "LINE_START":
                panel.add(label, BorderLayout.LINE_START);
                break;
            case "LINE_END":
                panel.add(label, BorderLayout.LINE_END);
                break;
        }
    }

    @Override
    public void clearMenu(JPanel panel){
        panel.removeAll();
    }


}
