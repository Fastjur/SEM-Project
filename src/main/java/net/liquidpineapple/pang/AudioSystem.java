package net.liquidpineapple.pang;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import net.liquidpineapple.pang.logger.Logger;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

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
  private static HashSet<String> effectsPlaying = new HashSet<>();
  private static Random rng = new Random();

  /**
   * Start the background music loop.
   */
  public static void start() {
    playMusic = true;
    new Thread(AudioSystem::playLoop, "AudioSystem").start();
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

  /**
   * Method to play a soundeffect.
   *
   * @param soundEffect The {@link SoundEffect} you want to play
   */
  public static void playEffect(SoundEffect soundEffect) {
    playEffect(soundEffect, true, 1);
  }

  /**
   * Plays a sound effect.
   *
   * @param soundEffect  The {@link SoundEffect} you want to play
   * @param allowOverlap Decides if this sound can play while the same sound is already playing
   * @param numSounds    The number of different versions of  this sound effect to choose from.
   */
  public static void playEffect(SoundEffect soundEffect, boolean allowOverlap, int numSounds) {
    String name = soundEffect.getName();
    if (!allowOverlap && effectsPlaying.contains(name)) {
      return;
    }

    String path;
    if (numSounds > 1) {
      int num = rng.nextInt(numSounds);
      path = "/sounds/" + name + num + ".wav";
    } else {
      path = "/sounds/" + name + ".wav";
    }
    Logger.info("Playing sound effect " + path);
    byte[] buffer;
    if (!cachedEffects.containsKey(path)) {
      try {
        BufferedInputStream inputStream =
            new BufferedInputStream(Application.class.getResourceAsStream(path));
        buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        cachedEffects.put(path, buffer);
        Logger.info("Loaded sound file " + path);
      } catch (IOException ex) {
        Logger.error("An exception was thrown while reading a sound file", ex);
      }
    }

    playTheSound(path, name);
  }

  private static void playTheSound(final String path, final String name) {
    effectsPlaying.add(name);
    new Thread(() -> {
      byte[] buffer = cachedEffects.get(path);
      try {
        Clip clip = javax.sound.sampled.AudioSystem.getClip();
        clip.open(javax.sound.sampled.AudioSystem.getAudioInputStream(
            new ByteArrayInputStream(buffer)));

        clip.addLineListener((lineEvent) -> {
          if (lineEvent.getType() == LineEvent.Type.STOP) {
            effectsPlaying.remove(name);
            clip.close(); // close resources when clip is finished.
          }
        });
        clip.start();
      } catch (UnsupportedAudioFileException ex) {
        Logger.error("The AudioSystem tried to play an unsupported audio file", ex);
      } catch (LineUnavailableException | IOException ex) {
        ex.printStackTrace();
      } catch (IllegalArgumentException ex) {
        Logger.error("Your system cannot play the specified audio file", ex);
      }
    }).start();
  }
}
