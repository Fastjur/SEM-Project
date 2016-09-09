package net.liquidpineapple.pang.objects;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Govert de Gans
 * @date 2016-09-08
 */
public class SinglePlayerButton extends Button implements MouseListener {

    public SinglePlayerButton(int startX, int startY) {
        super("/images/singleplayer.png", startX, startY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("ok");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
