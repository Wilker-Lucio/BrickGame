package com.kry.brickgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import com.kry.brickgame.games.Game;
import com.kry.brickgame.games.SplashScreen;

public class Window extends JFrame implements MouseInputListener {
	private static final long serialVersionUID = 3466619047314091863L;

	JFrame frame;

	private int x;
	private int y;

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
		// Color bgColor = new Color(109, 119, 92);
		Color bgColor = new Color(136, 153, 107);

		frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		
		/*frame.setBounds(0, 0, Draw.SQUARE_SIZE * (10 + 6)
				+ Draw.SQUARE_SIZE, Draw.SQUARE_SIZE * 20
				+ Draw.SQUARE_SIZE);*/
		frame.setBounds(0, 0, 360, 640); 
		
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		final Draw drawPanel = new Draw();
		drawPanel.setBackground(bgColor);
		//drawPanel.setOpaque(true);
		frame.getContentPane().add(drawPanel);

		Game.addGameListener(drawPanel);
		frame.addKeyListener(Main.gameKeyAdapter);

		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);

		new Timer("BlinkingSquares", true).schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						drawPanel.blinkingSquares();
					}
				});
			}
		}, 0, 10);
		new Timer("BlinkingPause", true).schedule(new TimerTask() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						drawPanel.blinkingPauseIcon();
					}
				});
			}
		}, 0, 500);

		setTitle("Brick Game");
		Main.setGame(new SplashScreen());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		frame.setLocation(e.getX() + frame.getX() - x, e.getY() + frame.getY()
				- y);
		frame.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
