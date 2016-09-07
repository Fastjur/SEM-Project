package net.liquidpineapple.pang.gui;

import net.liquidpineapple.pang.Level;
import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Jurriaan Den Toonder<jurriaan.toonder@liquidpineapple.net>
 * @date 2016/09/06.
 */
@Slf4j
public class PlayerBoard extends JPanel implements ActionListener {

    private static final int DELAY = 10;
    private static final int BOTTOM_OFFSET = 10;
    private final int boardWidth;
    private final int boardHeight;
    private Player player;
    private Timer timer;
    private Level currentLevel;

    public PlayerBoard(int boardWidth, int boardHeight) {
        super();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        init();
    }

    private void init() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        currentLevel = Level.createFromXML("level1.xml");
        player = new Player(1, 1, boardWidth);
        player.setPos(
            boardWidth / 2 - player.getWidth() / 2,
            boardHeight - player.getHeight() - BOTTOM_OFFSET
        );
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
        currentLevel.doDrawing(g2d, this);
        player.doDrawing(g2d, this);
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
