package net.liquidpineapple.pang.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tim on 7-9-2016.
 */
public interface Menu {
    public void draw(JPanel panel);
    public Component getComponent();
    public void clearMenu(JPanel panel);
    public void addButton(String filename, JPanel panel, String Option);
}
