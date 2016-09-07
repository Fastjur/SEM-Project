package net.liquidpineapple.pang.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tim on 7-9-2016.
 */
public  class StartMenu implements Menu{

    private Component component;
    public void draw(JPanel panel) {

    }

    @Override
    public void draw() {

    }

    public Component getComponent() {
        return component;
    }

    public void addMenu(JPanel panel){
        JPanel menuLayout = new JPanel();
        menuLayout.setLayout(new BoxLayout(menuLayout, BoxLayout.Y_AXIS));

        ImageIcon pangImage = new ImageIcon(getClass().getResource("/images/PANG.png"));
        ImageIcon singleplrImage = new ImageIcon(getClass().getResource("/images/singleplayer.png"));
        ImageIcon quitImage = new ImageIcon(getClass().getResource("/images/quit.png"));

        JLabel titlelabel = new JLabel(pangImage);
        titlelabel.setPreferredSize(new Dimension(730, 225));
        menuLayout.add(titlelabel);

        JButton singleplrButton = new JButton("", singleplrImage);
        singleplrButton.setPreferredSize(new Dimension(730, 150));
        menuLayout.add(singleplrButton);

        JButton quitButton = new JButton("", quitImage);
        quitButton.setPreferredSize(new Dimension(730, 150));
        menuLayout.add(quitButton);
        panel.add(menuLayout);
    }
}
