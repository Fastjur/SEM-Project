package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;
import net.liquidpineapple.pang.Application;

/**
 * Created by Jaap-Jan on 9-9-2016.
 */
@Slf4j
public class QuitButton extends Button{
    public QuitButton(int startX, int startY) {
        super("/images/quit.png", startX, startY);
    }

    @Override
    public void onClick() {
        log.info("Close");
        System.exit(0);
    }
}
