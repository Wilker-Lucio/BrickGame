package com.kry.brickgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.kry.brickgame.Board.Cells;

/**
 * <p>
 * ��������� ����� ��� ���.
 * </p>
 */
public class Game implements ActionListener {

	/**
	 * ���������� ����� ��������� ���� (�����), ������� �� ������ ���������� ��
	 * ����� ({@value} ). ������������ ��� �������� ��������� �����.
	 */
	public final static int UNSHOWED_LINES = 4;
	/**
	 * ������ ��������� ���� (�����) ({@value} ).
	 */
	public final static int BOARD_WIDTH = 10;
	/**
	 * ������ ��������� ���� (�����) ({@value} ).
	 */
	public final static int BOARD_HEIGHT = 20 + UNSHOWED_LINES;
	/**
	 * ������ ���� ������������� ({@value} ).
	 */
	public final static int PREVIEW_WIDTH = 4;
	/**
	 * ������ ���� ������������� ({@value} ).
	 */
	public final static int PREVIEW_HEIGHT = 4;

	protected Timer timer;

	/**
	 * ���������, ����������� �� ������� ����.
	 */
	private static ArrayList<IGameListener> listeners = new ArrayList<IGameListener>();

	static enum Status {
		None, Running, Paused, GameOver
	};

	/**
	 * ��������� ����
	 */
	private Status status;

	protected Status getStatus() {
		return status;
	}

	protected void setStatus(Status status) {
		this.status = status;
		fireStatusChanged(status);
	}

	/**
	 * �������� �����
	 */
	private Board board;
	/**
	 * ������������ �����
	 */
	private Board preview;

	static enum KeyPressed {
		KeyNone, KeyLeft, KeyRight, KeyUp, KeyDown, KeyRotate, KeyStart, KeyMode, KeyMute
	};

	public Game() {
		board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		preview = new Board(PREVIEW_WIDTH, PREVIEW_HEIGHT);
	}

	public static void addGameListener(IGameListener listener) {
		listeners.add(listener);
	}

	public static IGameListener[] getGameListeners() {
		return listeners.toArray(new IGameListener[listeners.size()]);
	}

	public static void removeGameListener(IGameListener listener) {
		listeners.remove(listener);
	}

	/**
	 * ������� ��������� ��������� �����
	 */
	protected void fireBoardChanged(Board board) {
		GameEvent event = new GameEvent(this, board);
		for (IGameListener listener : listeners) {
			listener.boardChanged(event);
		}
	}

	/**
	 * ������� ��������� ��������� �������������
	 */
	protected void firePreviewChanged(Board preview) {
		GameEvent event = new GameEvent(this, preview);
		for (IGameListener listener : listeners) {
			listener.previewChanged(event);
		}
	}

	/**
	 * ������� ��������� ��������� ����
	 */
	protected void fireStatusChanged(Status status) {
		GameEvent event = new GameEvent(this, status);
		for (IGameListener listener : listeners) {
			listener.statusChanged(event);
		}
	}

	/**
	 * ������� ��������� �������������� ���������� (���� � �.�.)
	 */
	protected void fireInfoChanged(String info) {
		GameEvent event = new GameEvent(this, info);
		for (IGameListener listener : listeners) {
			listener.infoChanged(event);
		}
	}

	protected Board getBoard() {
		return board;
	}

	protected void setBoard(Board board) {
		this.board = board;
		fireBoardChanged(board);
	}

	protected Board getPreview() {
		return preview;
	}

	protected void setPreview(Board preview) {
		this.preview = preview;
		firePreviewChanged(preview);
	}

	/**
	 * ������� �����
	 */
	protected void clearBoard() {
		board.clearBoard();
		fireBoardChanged(board);
	}

	/**
	 * ������� �������������
	 */
	protected void clearPreview() {
		preview.clearBoard();
		firePreviewChanged(preview);
	}

	/**
	 * �������� �� ����������� (������������) ����� ������ � ������������
	 * �������� �� �����
	 * 
	 * @param board
	 *            - �����
	 * @param piece
	 *            - ����� ������
	 * @param x
	 *            - ���������� "x" ������
	 * @param y
	 *            - ���������� "y" ������
	 * @return true - ���� ���� �����������, false - ���� ����������� ���
	 * 
	 * @see #checkBoardCollisionHorisontal
	 * @see #checkBoardCollisionVertical
	 * @see #checkBoardCollision
	 */
	protected boolean checkCollision(Board board, Shape piece, int x, int y) {
		try {
			for (int i = 0; i < piece.getCoords().length; ++i) {
				int board_x = x + piece.x(i);
				int board_y = y - piece.y(i);
				if (board.getCell(board_x, board_y) != Cells.Empty)
					return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * �������� �� ����������� (������������) ����� ������ � �������������
	 * (������� � ������) ��������� �����
	 * 
	 * @param piece
	 *            - ����� ������
	 * @param y
	 *            - ���������� "y" ������
	 * @return true - ���� ���� �����������, false - ���� ����������� ���
	 * 
	 * @see #checkBoardCollisionHorisontal
	 * @see #checkBoardCollision
	 * @see #checkCollision
	 */
	protected boolean checkBoardCollisionVertical(Shape piece, int y) {
		if ((y - piece.maxY()) < 0 || (y - piece.minY()) >= BOARD_HEIGHT)
			return true;
		return false;
	}

	/**
	 * �������� �� ����������� (������������) ����� ������ � ���������������
	 * (����� � ������) ��������� �����
	 * 
	 * @param piece
	 *            - ����� ������
	 * @param x
	 *            - ���������� "x" ������
	 * @return true - ���� ���� �����������, false - ���� ����������� ���
	 * 
	 * @see #checkBoardCollisionVertical
	 * @see #checkBoardCollision
	 * @see #checkCollision
	 */
	protected boolean checkBoardCollisionHorisontal(Shape piece, int x) {
		if ((x + piece.minX()) < 0 || (x + piece.maxX()) >= BOARD_WIDTH)
			return true;
		return false;
	}

	/**
	 * �������� �� ����������� (������������) ����� ������ �
	 * {@link Game#checkBoardCollisionVertical �������������} �
	 * {@link Game#checkBoardCollisionHorisontal ���������������} ���������
	 * �����
	 * 
	 * @param piece
	 *            - ����� ������
	 * @param x
	 *            - ���������� "x" ������
	 * @param y
	 *            - ���������� "y" ������
	 * @return true - ���� ���� �����������, false - ���� ����������� ���
	 * 
	 * @see #checkBoardCollisionVertical
	 * @see #checkBoardCollisionHorisontal
	 * @see #checkCollision
	 */
	protected boolean checkBoardCollision(Shape piece, int x, int y) {
		return checkBoardCollisionVertical(piece, y)
				|| checkBoardCollisionHorisontal(piece, x);
	}

	protected void animatedClearBoard() {
		for (int y = BOARD_HEIGHT - 1; y >= 0; --y) {
			for (int x = 0; x < BOARD_WIDTH; ++x) {
				board.setCell(Cells.Full, x, y);
			}
			try {
				Thread.sleep(100);
				fireBoardChanged(board);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	protected void keyPressed(KeyPressed key) {
		// TODO Auto-generated method stub

	}

	public void start() {
		clearBoard();
		clearPreview();
	}

}
