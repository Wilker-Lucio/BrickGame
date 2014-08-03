package com.kry.brickgame;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.Timer;

import com.kry.brickgame.Board.Cells;
import com.kry.brickgame.TetrisShape.Tetrominoes;

public class TetrisGame extends Game {

	/**
	 * ������� ������ �����������?
	 */
	private boolean isFallingFinished = false;
	/**
	 * ������� ��������� �����
	 */
	private int numLinesRemoved = 0;
	/**
	 * ���������� "x" ���������� �� ����� ������� ������
	 */
	private int curX = 0;
	/**
	 * ���������� "y" ���������� �� ����� ������� ������
	 */
	private int curY = 0;
	/**
	 * ������� ������
	 */
	private TetrisShape curPiece;
	/**
	 * ��������� ������
	 */
	private TetrisShape nextPiece;

	public TetrisGame() {
		super();

		setStatus(Status.None);
		timer = new Timer(400, this);

		curPiece = new TetrisShape();
		nextPiece = new TetrisShape();

		clearPreview();
		clearBoard();
	}

	/**
	 * ������� �� �������
	 */
	public void actionPerformed(ActionEvent e) {
		// ���� ������� ����������� - ������� ����� ������, ����� - ������
		// ������
		if (isFallingFinished) {
			isFallingFinished = false;
			newPiece();
		} else {
			oneLineDown();
		}
	}

	/**
	 * ������ ����
	 */
	public void start() {
		super.start();

		if (getStatus() == Status.Paused)
			return;

		setStatus(Status.Running);
		isFallingFinished = false;
		numLinesRemoved = 0;

		// ������� "���������" ������
		nextPiece.setRandomShapeAndRotate();
		newPiece();
		timer.start();
	}

	/**
	 * �����/�����������
	 */
	private void pause() {
		if (getStatus() == Status.Running) {
			timer.stop();
			setStatus(Status.Paused);
		} else if (getStatus() == Status.Paused) {
			timer.start();
			setStatus(Status.Running);
		}
	}

	/**
	 * ������� ������� �� ���� ����� ��� �����������
	 */
	private void dropDown() {
		int newY = curY;
		while (newY > 0) {
			if (!tryMove(curPiece, curX, newY - 1))
				break;
			--newY;
		}
		pieceDropped();
	}

	/**
	 * ������� �� ���� ����� ����
	 */
	private void oneLineDown() {
		if (!tryMove(curPiece, curX, curY - 1))
			pieceDropped();
	}

	/**
	 * ������ ����� ����
	 */
	private void pieceDropped() {
		Cells fill = (curPiece.getShape() == Tetrominoes.NoShape) ? Cells.Empty
				: Cells.Full;

		// ������������ ������� ������ � �����
		setBoard(drawPiece(getBoard(), curX, curY, curPiece, fill));

		removeFullLines();

		if (!isFallingFinished)
			newPiece();
	}

	/**
	 * �������� ����� ������
	 */
	private void newPiece() {

		curPiece.setShape(Tetrominoes.NoShape);

		// ���������� x - �������� �����
		curX = BOARD_WIDTH / 2 - 1;
		// ���������� y - ������� ����, ������ ����� �������, ����� ������ ����
		// ������ ��������� � ������� �� ������������ ����� #UNSHOWED_LINES
		curY = BOARD_HEIGHT - (UNSHOWED_LINES - nextPiece.maxY());

		// Logger.getAnonymousLogger().info(nextPiece.toString()); // TODO
		// �������
		// �����

		// ������� ��������� ������ �� �����
		if (!tryMove(nextPiece, curX, curY)) {
			timer.stop();
			setStatus(Status.GameOver);
		} else { // ���� ������� �������
			// ������� ��������� ������ � �������� �� � ������������
			nextPiece.setRandomShapeAndRotate();
			clearPreview();
			Logger.getAnonymousLogger().info(nextPiece.toString()); // TODO
																	// �������
			setPreview(drawPiece(getPreview(),//
					// ���������� "x":
					(PREVIEW_WIDTH / 2) // �������� ������ �����
							// �������� ������ ������
							- ((nextPiece.maxX() - nextPiece.minX() + 1) / 2)
							- (nextPiece.minX()),
					// ���������� "y" (������, ��� y �������� ����� �����):
					(PREVIEW_HEIGHT / 2 - 1)// �������� ������ �����
							// �������� ������ ������
							+ ((nextPiece.maxY() - nextPiece.minY() + 1) / 2)
							+ (nextPiece.minY()),//
					nextPiece, Cells.Full));
		}
	}

	/**
	 * ��������� ������ �� �����
	 * 
	 * @param board
	 *            - ����� (�������� ����� ��� ������������ ��� ��������� �����
	 *            ��� ��������)
	 * @param x
	 *            - ���������� "x" ��� ���������� ������ �� �����
	 * @param y
	 *            - ���������� "y" ��� ���������� ������ �� �����
	 * @param piece
	 *            - ������
	 * @param fill
	 *            - �������: Cells.Full - ��� ��������� ������, Cells.Empty -
	 *            ��� ��������
	 * @return - ���������� ����� � ������������ (��� �������) �������
	 */
	private Board drawPiece(Board board, int x, int y, TetrisShape piece,
			Cells fill) {
		for (int i = 0; i < piece.getCoords().length; ++i) {
			int board_x = x + piece.x(i);
			int board_y = y - piece.y(i);
			board.setCell(fill, board_x, board_y);
		}
		return board;
	}

	/**
	 * ����������� ������
	 * 
	 * @param newPiece
	 *            - ������ ����� ����������� (����., ��� �������� ������)
	 * @param newX
	 *            - ���������� "x" ��� ���������� ������ �� ����� �����
	 *            �����������
	 * @param newY
	 *            - ���������� "y" ��� ���������� ������ �� ����� �����
	 *            �����������
	 * @return - ���������� true - ���� ����������� ������� � false - �
	 *         ��������� ������
	 */
	private boolean tryMove(TetrisShape newPiece, int newX, int newY) {
		// ������� ��������� �����, ����� ��������
		Board board = getBoard().clone();

		// ������� ������� ������ � ��������� �����, ����� �� �������� ���
		// ���������
		board = drawPiece(board, curX, curY, curPiece, Cells.Empty);

		// ��� ����������� � ������� �������� �����, �������� ���������� �
		// �������
		int prepX = newX;
		while (checkBoardCollisionHorisontal(newPiece, prepX)) {
			prepX = ((prepX + newPiece.minX()) < 0) ? prepX + 1 : prepX - 1;
		}
		// ��������
		if (checkBoardCollisionVertical(newPiece, newY))
			return false;
		if (checkCollision(board, newPiece, prepX, newY))
			return false;

		// ������� ������� ������ � �������� ����� � ������ �����
		setBoard(drawPiece(getBoard(), curX, curY, curPiece, Cells.Empty));
		setBoard(drawPiece(getBoard(), prepX, newY, newPiece, Cells.Full));

		// �������� ������� ������ �� �����
		curPiece = newPiece.clone();
		curX = prepX;
		curY = newY;

		return true;
	}

	/**
	 * �������� ����������� �����
	 */
	private void removeFullLines() {
		Board board = getBoard().clone();

		isFallingFinished = true;

		int numFullLines = 0;

		// �������� �� ���� �������
		for (int y = 0; y < BOARD_HEIGHT - 1; ++y) {
			boolean lineIsFull = true;
			// �������� �� ������� ������ ������ � ������
			for (int x = 0; x < BOARD_WIDTH; ++x) {
				if (board.getCell(x, y) == Cells.Empty) {
					lineIsFull = false;
					break;
				}
			}
			if (lineIsFull) {
				++numFullLines;
				// �������� ������ ������ �� ����������� ������
				for (int k = y; k < BOARD_HEIGHT - 1; ++k) {
					for (int x = 0; x < BOARD_WIDTH; ++x)
						board.setCell(board.getCell(x, k + 1), x, k);
				}
				// ������������ �� ������ ����� (�.�. ����������� �� �������)
				--y;
			}
		}

		setBoard(board);

		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
		}
		curPiece.setShape(Tetrominoes.NoShape);
		fireInfoChanged(String.valueOf(numLinesRemoved));
	}

	/**
	 * ��������� ������� ������
	 */
	public void keyPressed(KeyPressed key) {
		if (getStatus() == Status.None)
			return;

		if (key == KeyPressed.KeyStart) {
			if (getStatus() == Status.GameOver) {
				start();
			} else {
				pause();
			}
			return;
		}

		if (getStatus() != Status.Running)
			return;

		if (!isFallingFinished) {
			switch (key) {
			case KeyLeft:
				tryMove(curPiece, curX - 1, curY);
				break;
			case KeyRight:
				tryMove(curPiece, curX + 1, curY);
				break;
			case KeyRotate:
				TetrisShape rotatedPiece = new TetrisShape(curPiece)
						.rotateRight();
				tryMove(rotatedPiece, curX, curY);
				break;
			case KeyDown:
				oneLineDown();
				break;
			case KeyMode:
				dropDown();
				break;
			default:
				break;
			}
		}
	}

}
