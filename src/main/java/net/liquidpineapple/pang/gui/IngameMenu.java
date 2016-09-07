package net.liquidpineapple.pang.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tim on 7-9-2016.
 * Implementation of the Menu interface,
 * Makes sure that the ingame menu is loaded.
 */
public class IngameMenu implements Menu {

    /**
     * method to draw the menu.
     * @param panel - panel in which the menu should be loaded.
     */
    @Override
    public void draw(JPanel panel){
        panel.removeAll();
        createMenu(panel);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * method to create the interface/buttons of the menu.
     * @param panel - panel in which these buttons should be placed.
     */
    private void createMenu(JPanel panel){
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
                panel.repaint();
                System.out.print("GAME STARTS NOW");
                //GAME.START();

            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    /**
     * Method to add buttons to a panel.
     * @param filename - location of background-image of the buttons.
     * @param panel - panel in which these buttons should be displayed.
     * @param option - option tag to indicate the purpose of the button.
     */
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
}
