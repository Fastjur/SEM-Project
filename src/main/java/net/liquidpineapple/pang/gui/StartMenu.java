package net.liquidpineapple.pang.gui;

import net.liquidpineapple.pang.Application;
import org.omg.SendingContext.RunTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Tim on 7-9-2016.
 * Implementation of the Menu interface,
 * creates menu on startup of the game.
 */
public class StartMenu implements Menu{


    /**
     * Method to draw the menu.
     * @param panel - panel in which the menu should be drawn.
     */
    @Override
    public void draw(JPanel panel) {
        panel.removeAll();
        addMenu(panel);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Method to add the buttons and layout to the menu.
     * @param panel - panel in which these buttons are placed.
     */
    public void addMenu(JPanel panel){
        JPanel menuLayout = new JPanel();
        menuLayout.setLayout(new GridLayout(4, 1));

        ImageIcon pangImage = new ImageIcon(getClass().getResource("/images/PANG.png"));
        JLabel titlelabel = new JLabel(pangImage);
        menuLayout.add(titlelabel);

        addButton("/images/singleplayer.png", menuLayout, "singleplayer");
        addButton("/images/quit.png", menuLayout, "quit");
        panel.add(menuLayout);
    }

    /**
     * Method to create buttons and add them to the panel.
     * @param filename - location of the background-image for the buttons.
     * @param panel - panel in which these buttons should be loaded.
     * @param option - option tag to indicate the purpose of the buttons.
     */
    @Override
    public void addButton(String filename, JPanel panel, String option){
        ImageIcon buttonImage = new ImageIcon(getClass().getResource(filename));
        JLabel button = new JLabel(buttonImage);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(option == "singleplayer"){
                    new IngameMenu().draw(panel);
                }

                else if(option == "quit"){
                    System.exit(0);
                }

                else {
                    System.out.print("Error reading filename");
                }
            }
        });
        panel.add(button);
    }
}
