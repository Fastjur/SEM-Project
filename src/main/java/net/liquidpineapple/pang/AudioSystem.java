package net.liquidpineapple.pang;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import net.liquidpineapple.pang.logger.Logger;

import java.io.*;

/**
 * @author Govert de Gans
 * @date 2016-09-22
 */
public class AudioSystem {

    private static Player player;
    private static String loopingSound = "";

    public static void start() {
        new Thread(() -> {
            playLoop();
        }).start();
    }

    private static void playLoop() {
        Logger.info("Music loop is running");
        byte[] buffer = null;
        try {
            String currentLoop = "";
            while (true) {
                if (!currentLoop.equals(loopingSound)) {
                    BufferedInputStream resourceStream = new BufferedInputStream(
                        Application.class.getResourceAsStream(loopingSound));
                    buffer = new byte[resourceStream.available()];
                    resourceStream.read(buffer);
                    currentLoop = loopingSound;
                }

                if (buffer != null) {
                    player = new Player(new ByteArrayInputStream(buffer));
                    player.play();
                }
            }
        } catch(JavaLayerException e) {
            Logger.error("The AudioSystem has crashed.", e);
        } catch(IOException e) {
            Logger.error("The AudioSystem has crashed.", e);
        }
    }

    public static void changeLoopingSound(String newLoopingSound) {
        if (!loopingSound.equals("")) {
            player.close();
        }
        loopingSound = newLoopingSound;
    }
}
