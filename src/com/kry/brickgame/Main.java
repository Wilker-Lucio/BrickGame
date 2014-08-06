package com.kry.brickgame;

import java.awt.EventQueue;

/**
 * �������� ����� ��� �������
 * @author noLive
 *
 */
public final class Main{

	/**
	 * ������� ���� 
	 */
	private static Game game;
	/**
	 * ������� ����� ���� 
	 */
	private static Thread gameThread;
	/**
	 * ������ ������ ����
	 */
	public static GameSelector gameSelector = new GameSelector();
	/**
	 * ��������� �� ������� ������� ������
	 */
	public static GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Main.game = game;
		gameThread = new Thread(game, "TGameThread");
		gameThread.start();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
