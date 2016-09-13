package net.liquidpineapple.pang.objects;

import lombok.extern.slf4j.Slf4j;

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
    }
}
