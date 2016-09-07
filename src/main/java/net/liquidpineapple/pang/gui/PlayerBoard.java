package net.liquidpineapple.pang.gui;

import net.liquidpineapple.pang.objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
public class PlayerBoard extends JPanel implements ActionListener {

    private static final int DELAY = 20;
    private Player player;
    private Timer timer;

    public PlayerBoard() {
        init();
    }

    private void init() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        player = new Player(40, 60);
        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(player.getImage(), player.getXPos(), player.getYPos(), this);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
