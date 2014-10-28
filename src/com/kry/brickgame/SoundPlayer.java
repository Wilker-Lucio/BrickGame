package com.kry.brickgame;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	public enum Snd {
		welcome, select, start, win, game_over, move, turn, hit, kaboom, bonus, super_shape, remove_line, tetris, race, //
		melody1, melody2, melody3, melody4, melody5, melody6, melody7, melody8, melody9;
	}

	private final static String soundFolder = "/sounds/";
	private final static String soundExtension = ".au";

	private boolean released = false;
	private Clip clip = null;
	private FloatControl volumeC = null;
	private boolean playing = false;

	public SoundPlayer(InputStream sound) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(sound);
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.addLineListener(new Listener());
			volumeC = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			released = true;
		} catch (NullPointerException | UnsupportedAudioFileException
				| LineUnavailableException | IOException exc) {
			exc.printStackTrace();
			released = false;
		}
	}

	// true ���� ���� ������� ��������, false ���� ��������� ������
	public boolean isReleased() {
		return released;
	}

	// ������������� �� ���� � ������ ������
	public boolean isPlaying() {
		return playing;
	}

	// ������
	/*
	 * breakOld ���������� ���������, ���� ���� ��� �������� ���� reakOld==true,
	 * � ���� ����� ������� � ������� ������ ����� ������ �� ���������
	 */
	public void play(boolean breakOld) {
		if (released) {
			if (breakOld) {
				clip.stop();
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			} else if (!isPlaying()) {
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			}
		}
	}

	// �� �� �����, ��� � play(true)
	public void play() {
		play(true);
	}

	// ������������� ���������������
	public void stop() {
		if (playing) {
			clip.stop();
		}
	}

	// ��������� ���������
	/*
	 * x ����� ���� � �������� �� 0 �� 1 (�� ������ ������ � ������ ��������)
	 */
	public void setVolume(float x) {
		float preparedX = x;
		if (preparedX < 0)
			preparedX = 0;
		else if (preparedX > 1)
			preparedX = 1;

		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		volumeC.setValue((max - min) * preparedX + min);
	}

	// ���������� ������� ��������� (����� �� 0 �� 1)
	public float getVolume() {
		float v = volumeC.getValue();
		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		return (v - min) / (max - min);
	}

	// ���������� ��������� ������������ �����
	public void join() {
		if (!released)
			return;
		synchronized (clip) {
			try {
				while (playing)
					clip.wait();
			} catch (InterruptedException exc) {
			}
		}
	}

	// ����������� �����, ��� ��������
	public static SoundPlayer playSound(Snd sound) {
		StringBuilder soundFile = new StringBuilder();
		soundFile.append(soundFolder).append(sound).append(soundExtension);
		
		InputStream soundStream = SoundPlayer.class.getResourceAsStream(soundFile.toString());
			
		SoundPlayer snd = new SoundPlayer(soundStream);
		snd.play();
		return snd;
	}

	private class Listener implements LineListener {
		public void update(LineEvent ev) {
			if (ev.getType() == LineEvent.Type.STOP) {
				playing = false;
				synchronized (clip) {
					clip.notify();
				}
			}
		}
	}
}
