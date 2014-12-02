package com.kry.brickgame.games;

import static com.kry.brickgame.games.GameConsts.ANIMATION_DELAY;
import static com.kry.brickgame.games.GameUtils.insertCellsToBoard;
import static com.kry.brickgame.games.GameUtils.music;
import static com.kry.brickgame.games.GameUtils.playMusic;

import com.kry.brickgame.Main;
import com.kry.brickgame.SoundManager;
import com.kry.brickgame.boards.Board;
import com.kry.brickgame.boards.Board.Cell;
import com.kry.brickgame.boards.BoardNumbers;
import com.kry.brickgame.games.GameConsts.Status;
import com.kry.brickgame.games.GameUtils.Music;

/**
 * @author noLive
 */
public class SplashScreen extends Game {
	private static final long serialVersionUID = 6213953274430176604L;
	
	private final Music welcome = Music.welcome;
	
	public SplashScreen() {
		super();
		SoundManager.prepare(music, welcome);
	}
	
	/**
	 * Animated walking in a spiral with inverting cells on the main board
	 */
	protected void animatedInvertBoard() {
		Board board = getBoard();
		// x: 0 --> board.width
		int fromX = 0;
		int toX = board.getWidth() - 1;
		// y: board.height --> 0
		int fromY = board.getHeight() - 1;
		int toY = 0;
		
		// until it reaches the middle of the board
		while (fromX != board.getWidth() / 2) {
			// spiral motion with a gradually narrowing
			if (!horizontalMove(fromX, toX, fromY--)
					|| !verticalMove(fromY, toY, toX--)
					|| !horizontalMove(toX, fromX, toY++)
					|| !verticalMove(toY, fromY, fromX++)) return;
		}
		sleep(ANIMATION_DELAY * 2);
	}
	
	/**
	 * Blinking "9999" on the board specified number of times
	 * 
	 * @param repeatCount
	 *            the number of repeats of blinks
	 */
	protected void blinkNumbers(int repeatCount) {
		if (repeatCount <= 0) return;
		
		for (int i = 0; i < repeatCount; i++) {
			
			if (isInterrupted()) return;
			
			clearBoard();
			sleep(ANIMATION_DELAY * 5);
			insertNumbers();
			sleep(ANIMATION_DELAY * 6);
		}
	}
	
	/**
	 * Animated horizontal moving and inverting cells
	 * 
	 * @param fromX
	 *            the starting x-coordinate
	 * @param toX
	 *            the finishing x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @return {@code false} if the current thread was interrupted, otherwise -
	 *         {@code true}
	 */
	private boolean horizontalMove(int fromX, int toX, int y) {
		Board board = getBoard();
		// define the direction by coordinates
		boolean isRightDirection = (toX >= fromX);
		
		// left to right
		if (isRightDirection) {
			for (int i = fromX; i <= toX; i++) {
				
				if (isInterrupted()) return false;
				
				// invert cells
				board.setCell(((board.getCell(i, y) == Cell.Empty) ? Cell.Full
						: Cell.Empty), i, y);
				fireBoardChanged(board);
				sleep(ANIMATION_DELAY);
			}
			// right to left
		} else {
			for (int i = fromX; i >= toX; i--) {
				
				if (isInterrupted()) return false;
				
				// invert cells
				board.setCell(((board.getCell(i, y) == Cell.Empty) ? Cell.Full
						: Cell.Empty), i, y);
				fireBoardChanged(board);
				sleep(ANIMATION_DELAY);
			}
		}
		return true;
	}
	
	/**
	 * Draws a "9999" on the main board
	 */
	private void insertNumbers() {
		Board board = getBoard().clone();
		BoardNumbers boardNumber = new BoardNumbers();
		
		boardNumber.setNumber(BoardNumbers.intToNumbers(9));
		
		// upper left
		insertCellsToBoard(board, boardNumber.getBoard(), 1, board.getHeight()
				- boardNumber.getHeight() - 1);
		// lower left
		insertCellsToBoard(board, boardNumber.getBoard(), 1,
				boardNumber.getHeight());
		// upper right
		insertCellsToBoard(board, boardNumber.getBoard(), board.getWidth()
				- boardNumber.getWidth() - 1,
				board.getHeight() - boardNumber.getHeight() * 2);
		// lower right
		insertCellsToBoard(board, boardNumber.getBoard(), board.getWidth()
				- boardNumber.getWidth() - 1, 1);
		
		if (!isInterrupted()) {
			setBoard(board);
		}
	}
	
	/**
	 * Processing of key presses
	 */
	@Override
	protected void processKeys() {
		if (getStatus() == Status.None) return;
		if (!keys.isEmpty()) {
			setStatus(Status.None);
		}
	}
	
	@Override
	public void start() {
		setStatus(Status.DoSomeWork);
		
		playMusic(welcome);
		sleep(ANIMATION_DELAY);
		
		insertNumbers();
		
		// Splash screen will be run in a separate thread
		Thread splashScreenThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!interrupted()) {
					animatedInvertBoard();
					blinkNumbers(5);
				}
			}
		}, "SplashScreen");
		
		splashScreenThread.start();
		
		// by pressing any key status sets to Status.None
		while (getStatus() != Status.None) {
			processKeys();
		}
		
		splashScreenThread.interrupt();
		// Waits for end of interrupting splashScreenThread
		while (splashScreenThread.isAlive()) {
			;// wait
		}
		
		Main.setGame(Main.gameSelector = new GameSelector());
	}
	
	/**
	 * Animated vertical moving and inverting cells
	 * 
	 * @param fromY
	 *            the starting y-coordinate
	 * @param toY
	 *            the finishing y-coordinate
	 * @param x
	 *            the x-coordinate
	 * @return {@code false} if the current thread was interrupted, otherwise -
	 *         {@code true}
	 */
	private boolean verticalMove(int fromY, int toY, int x) {
		Board board = getBoard();
		// define the direction by coordinates
		boolean isUpDirection = (toY >= fromY);
		
		// bottom to top
		if (isUpDirection) {
			for (int i = fromY; i <= toY; i++) {
				
				if (isInterrupted()) return false;
				
				// invert cells
				board.setCell(((board.getCell(x, i) == Cell.Empty) ? Cell.Full
						: Cell.Empty), x, i);
				fireBoardChanged(board);
				sleep(ANIMATION_DELAY);
			}
			// top to bottom
		} else {
			for (int i = fromY; i >= toY; i--) {
				
				if (isInterrupted()) return false;
				
				// invert cells
				board.setCell(((board.getCell(x, i) == Cell.Empty) ? Cell.Full
						: Cell.Empty), x, i);
				fireBoardChanged(board);
				sleep(ANIMATION_DELAY);
			}
		}
		return true;
	}
	
}
