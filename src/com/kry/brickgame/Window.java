package com.kry.brickgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class Window extends JFrame {
	private static final long serialVersionUID = 3466619047314091863L;

	JFrame frame;
	private JLabel statusbar;

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Color bgColor = new Color(109, 119, 92);

		frame = new JFrame();
		frame.setBounds(100, 100, 338, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel gameName = new JLabel("New label");
		frame.getContentPane().add(gameName, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.7);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.25);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);

		statusbar = new JLabel("0");

		final DrawBoard drawBoard = new DrawBoard(this);
		drawBoard.setBackground(bgColor);
		final DrawPreview drawPreview = new DrawPreview();
		drawPreview.setBackground(bgColor);

		splitPane_1.setRightComponent(statusbar);

		splitPane_1.setLeftComponent(drawPreview);

		splitPane.setLeftComponent(drawBoard);

		Game.addGameListener(drawPreview);
		Game.addGameListener(drawBoard);
		drawBoard.addKeyListener(Main.gameKeyAdapter);

		Timer timer = new Timer("Blinking", true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						drawBoard.blinking();
						drawPreview.blinking();
					}
				});
			}
		}, 0, 50);

		setTitle("Brick Game");
		Main.setGame(Main.gameSelector);

	}

	public JLabel getStatusBar() {
		return statusbar;
	}
}
