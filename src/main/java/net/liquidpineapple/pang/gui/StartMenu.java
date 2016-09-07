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
        ImageIcon buttonimage = new ImageIcon(getClass().getResource("/images/background.png"));
        JButton button = new JButton("", buttonimage);
        panel.add(button);

    }
}
