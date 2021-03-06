package com.kry.brickgame.games;

import static com.kry.brickgame.games.Game.scheduledExecutors;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.kry.brickgame.sound.SoundBank;
import com.kry.brickgame.sound.SoundManager;

/**
 * @author noLive
 */
public enum GameSound {
	;
	/**
	 * Sound effects
	 */
	protected enum Effects {
		select, move, turn, hit_cell, add_cell, bonus, fall, fall_super, remove_line
	}
	
	/**
	 * Melodies for Dance game
	 */
	protected enum Melodies {
		melody1, melody2, melody3, melody4, melody5, melody6, melody7, melody8, melody9
	}
	
	/**
	 * Music
	 */
	protected enum Music {
		welcome, start, win, game_over, tetris, kaboom
	}
	
	// load the sounds at initialization to reduce the delay in the first play
	static final SoundBank effects = new SoundBank();
	static final SoundBank melodies = new SoundBank();
	static final SoundBank music = new SoundBank();
	
	/**
	 * Map of the priority of the sound effects
	 */
	static final Map<Effects, Integer> effectsPriority;
	static {
		effectsPriority = new EnumMap<>(Effects.class);
		effectsPriority.put(Effects.select, Thread.MAX_PRIORITY);
		effectsPriority.put(Effects.move, Thread.MIN_PRIORITY);
		effectsPriority.put(Effects.turn, Thread.NORM_PRIORITY);
		effectsPriority.put(Effects.hit_cell, Thread.MAX_PRIORITY);
		effectsPriority.put(Effects.add_cell, Thread.MAX_PRIORITY);
		effectsPriority.put(Effects.bonus, Thread.MAX_PRIORITY);
		effectsPriority.put(Effects.fall, Thread.MAX_PRIORITY);
		effectsPriority.put(Effects.fall_super, Thread.NORM_PRIORITY);
		effectsPriority.put(Effects.remove_line, Thread.MAX_PRIORITY);
	}
	
	/**
	 * Play the {@code sound} in a circle, from the specified {@code soundBank}.
	 * 
	 * @param soundBank
	 *            specified SoundBank
	 * @param sound
	 *            {@code enum} value, containing the name of the sound
	 * @param echoDelay
	 *            delay before starting the second audio stream. 0 -without
	 *            echo.
	 */
	protected static <E extends Enum<E>> void loop(final SoundBank soundBank, final Enum<E> sound,
			int echoDelay) {
		if (!Game.isMuted()) {
			SoundManager.loop(soundBank, sound);
			// double loop - workaround for ending gap
			if (echoDelay > 0) {
				scheduledExecutors.schedule(new Runnable() {
					@Override
					public void run() {
						SoundManager.loop(soundBank, sound);
					}
				}, echoDelay, TimeUnit.MILLISECONDS);
			}
		}
	}
	
	/**
	 * Play the {@code sound}, from the specified {@code soundBank}.
	 * 
	 * @param soundBank
	 *            specified SoundBank
	 * @param sound
	 *            {@code enum} value, containing the name of the sound
	 */
	protected static <E extends Enum<E>> void play(SoundBank soundBank, Enum<E> sound) {
		if (!Game.isMuted()) {
			SoundManager.play(soundBank, sound);
		}
	}
	
	/**
	 * Play the {@code sound}, from the {@code Effects}.
	 * 
	 * @param sound
	 *            {@code Effects} value, containing the name of the sound
	 */
	protected static void playEffect(Effects sound) {
		if (!Game.isMuted() && !SoundManager.isPlaying(GameSound.music)) {
			// get sound priority
			int priority = GameSound.effectsPriority.containsKey(sound) ? GameSound.effectsPriority
					.get(sound) : Thread.NORM_PRIORITY;
			SoundManager.play(GameSound.effects, sound, priority);
		}
	}
	
	/**
	 * Play the {@code sound}, from the {@code Melodies} with specified
	 * {@code rate}.
	 * 
	 * @param sound
	 *            {@code Melodies} value, containing the name of the sound
	 * @param rate
	 *            playback rate multiplier
	 */
	protected static void playMelody(Melodies sound, double rate) {
		if (!Game.isMuted()) {
			SoundManager.play(GameSound.melodies, sound, rate);
		}
	}
	
	/**
	 * Play the {@code sound}, from the {@code Music}.
	 * 
	 * @param sound
	 *            {@code Music} value, containing the name of the sound
	 */
	protected static void playMusic(Music sound) {
		if (!Game.isMuted() && !SoundManager.isPlaying(music, Music.tetris)) {
			// stopAllSounds(); // <-- too slow
			// stop music only
			SoundManager.stopAll(GameSound.music);
			// and effects in some cases
			if (Music.win.equals(sound) || Music.game_over.equals(sound)) {
				SoundManager.stopAll(GameSound.effects);
			}
			
			SoundManager.play(GameSound.music, sound);
		}
	}
	
	/**
	 * Stop playing the {@code sound}, from the specified {@code soundBank}.
	 * 
	 * @param soundBank
	 *            specified SoundBank
	 * @param sound
	 *            {@code enum} value, containing the name of the sound
	 */
	protected static <E extends Enum<E>> void stop(SoundBank soundBank, Enum<E> sound) {
		SoundManager.stop(soundBank, sound);
	}
	
	/**
	 * Stops playing for all sounds
	 */
	protected static void stopAllSounds() {
		SoundManager.stopAll(GameSound.effects);
		SoundManager.stopAll(GameSound.music);
		SoundManager.stopAll(GameSound.melodies);
	}
	
}
