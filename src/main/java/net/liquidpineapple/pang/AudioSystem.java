package net.liquidpineapple.pang;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import net.liquidpineapple.pang.logger.Logger;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Audiosystem for the application. Plays the background music.
 *
 * @author Govert de Gans
 * @date 2016-09-22
 */

public class AudioSystem {

  private static Player player;
  private static String loopingSound = "";
  private static boolean isRunning;
  private static boolean playMusic = true;
  private static HashMap<String, byte[]> cachedEffects = new HashMap<>();

  /**
   * Start the background music loop.
   */
  public static void start() {
    playMusic = true;
    new Thread(AudioSystem::playLoop).start();
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
    } catch (JavaLayerException | IOException ex) {
      Logger.error("The AudioSystem has crashed.", ex);
    }
    isRunning = false;
  }

  /**
   * Change the looping background music to another file.
   *
   * @param newLoopingSound {@link String} URL to the new looping sound.
   */
  public static void changeLoopingSound(String newLoopingSound) {
    if (!loopingSound.equals("") && isRunning && player != null) {
      player.close();
    }
    loopingSound = newLoopingSound;
  }

  /**
   * Stops the music from playing.
   */
  public static void stop() {
    playMusic = false;
    try {
      player.close();
    } catch (NullPointerException ex) {
      Logger.warning("Attempted to close player but was null");
    }
  }

  public static void playEffect(String path) {
    try {
      Logger.info("Playing sound effect " + path);
      byte[] buffer;
      if ((buffer = cachedEffects.getOrDefault(path, null)) == null) {
        BufferedInputStream inputStream =
            new BufferedInputStream(Application.class.getResourceAsStream(path));
        buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        cachedEffects.put(path, buffer);
      }
      Clip clip = javax.sound.sampled.AudioSystem.getClip();
      clip.open(javax.sound.sampled.AudioSystem.getAudioInputStream(
          new ByteArrayInputStream(buffer)));

      clip.addLineListener((lineEvent) -> {
        if (lineEvent.getType() == LineEvent.Type.STOP)
          clip.close(); // close resources when clip is finished.
      });
      clip.start();
    } catch (LineUnavailableException | IOException e) {
      Logger.error("Could not play sound effect", e);
    } catch (UnsupportedAudioFileException e) {
      Logger.error("The AudioSystem tried to play an unsupported audio file", e);
    }
  }
}
