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
    private static boolean isRunning;
    private static boolean playMusic = true;

    public static void start() {
        playMusic = true;
        new Thread(() -> {
            playLoop();
        }).start();
    }

    private static void playLoop() {
        isRunning = true;
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

                if (buffer != null && playMusic) {
                    Logger.info("Music loop is running");
                    player = new Player(new ByteArrayInputStream(buffer));
                    player.play();
                }
            }
        } catch(JavaLayerException | IOException e) {
            Logger.error("The AudioSystem has crashed.", e);
        }
        isRunning = false;
    }

    public static void changeLoopingSound(String newLoopingSound) {
        if (!loopingSound.equals("") && isRunning && player != null) {
            player.close();
        }
        loopingSound = newLoopingSound;
    }

    public static void stop() {
        playMusic = false;
        try {
            player.close();
        } catch(NullPointerException e){
            Logger.warning("Attempted to close player but was null");
        }
    }
}
