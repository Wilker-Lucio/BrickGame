package com.kry.brickgame;

import java.util.EventObject;

import com.kry.brickgame.Game.Status;

public class GameEvent extends EventObject {

	private static final long serialVersionUID = 3341669389633046775L;
	private Board board;
	private Status status;
	private String info;

	/**
	 * ������� ����
	 * @param source - �������� �������
	 * @param board - ��������� �����
	 * @param status - ������ ����
	 * @param info - ���. ���������� (���� � �.�.)
	 */
	public GameEvent(Object source, Board board, Status status, String info) {
		super(source);
		this.board = board;
		this.status = status;
		this.info = info;
	}

	public GameEvent(Object source) {
		this(source, null, null, "");
	}

	public GameEvent(Object source, Board board) {
		this(source, board, null, "");
	}

	public GameEvent(Object source, Status status) {
		this(source, null, status, "");
	}

	public GameEvent(Object source, String info) {
		this(source, null, null, info);
	}

	protected Status getStatus() {
		return status;
	}

	protected String getInfo() {
		return info;
	}

	protected Board getBoard() {
		return board;
	}

}
