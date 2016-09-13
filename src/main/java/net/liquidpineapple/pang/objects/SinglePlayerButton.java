package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;
import net.liquidpineapple.pang.screens.ControlsScreen;

/**
 * @author Govert de Gans
 * @date 2016-09-08
 */
@Slf4j
public class SinglePlayerButton extends Button {

    public SinglePlayerButton(int startX, int startY) {
        super("/images/singleplayer.png", startX, startY);
    }

    @Override
    public void onClick() {
        log.info("I change the screen now");
        Application.getBoard().changeScreen(new ControlsScreen());
    }
}
