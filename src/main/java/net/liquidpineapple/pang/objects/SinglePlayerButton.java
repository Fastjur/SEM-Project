package net.liquidpineapple.pang.objects;

import net.liquidpineapple.pang.gui.Board;
import net.liquidpineapple.pang.screens.Level;

/**
 * @author Govert de Gans
 * @date 2016-09-08
 */
public class SinglePlayerButton extends Button {

    public SinglePlayerButton(int startX, int startY) {
        super("/images/singleplayer.png", startX, startY);
    }

    @Override
    public void onClick() {
        Board.currentScreen = new Level();
    }
}
