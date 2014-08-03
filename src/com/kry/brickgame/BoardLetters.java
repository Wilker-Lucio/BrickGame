package com.kry.brickgame;

public class BoardLetters extends Board {

	/**
	 * ������ - �����
	 */
	enum Letters {
		None, A, B, C, D, E, F, G, H
	};

	private final static int width = 5;
	private final static int height = 5;

	/**
	 * �����
	 */
	private Letters letter;

	/**
	 * �����, � ������������ ������
	 */
	private Cells[][] board = new Cells[width][height];

	/**
	 * ������� �������� ���� �� �����:
	 * [������_�����][����������_y][����������_x]
	 */
	private final static Cells[][][] lettersTable = new Cells[][][] {
			{
					{ Empty, Empty, Empty, Empty, Empty },// None
					{ Empty, Empty, Empty, Empty, Empty },
					{ Empty, Empty, Empty, Empty, Empty },
					{ Empty, Empty, Empty, Empty, Empty },
					{ Empty, Empty, Empty, Empty, Empty } },
			{
					{ Empty, Empty, Full, Empty, Empty },// A
					{ Empty, Full, Empty, Full, Empty },
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Full, Full, Full, Full },
					{ Full, Empty, Empty, Empty, Full } },
			{
					{ Full, Full, Full, Full, Empty },// B
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Full, Full, Full, Full },
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Full, Full, Full, Empty } },
			{
					{ Empty, Full, Full, Full, Empty },// C
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Empty, Empty, Empty, Empty },
					{ Full, Empty, Empty, Empty, Full },
					{ Empty, Full, Full, Full, Empty } },
			{
					{ Full, Full, Full, Full, Empty },// D
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Full, Full, Full, Empty } },
			{
					{ Full, Full, Full, Full, Full },// E
					{ Full, Empty, Empty, Empty, Empty },
					{ Full, Full, Full, Full, Full },
					{ Full, Empty, Empty, Empty, Empty },
					{ Full, Full, Full, Full, Full } },
			{
					{ Full, Full, Full, Full, Full },// F
					{ Full, Empty, Empty, Empty, Empty },
					{ Full, Full, Full, Full, Full },
					{ Full, Empty, Empty, Empty, Empty },
					{ Full, Empty, Empty, Empty, Empty } },
			{
					{ Empty, Full, Full, Full, Empty },// G
					{ Full, Empty, Empty, Empty, Empty },
					{ Full, Empty, Full, Full, Empty },
					{ Full, Empty, Empty, Empty, Full },
					{ Empty, Full, Full, Full, Empty } },
			{
					{ Full, Empty, Empty, Empty, Full },// H
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Full, Full, Full, Full },
					{ Full, Empty, Empty, Empty, Full },
					{ Full, Empty, Empty, Empty, Full } } };

	protected void setLetter(Letters letter) {
		// ������ �� ����� ����� �� #lettersTable
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// [height - y - 1] - ������ ����� ������ (����� �����������
				// ����� �����)
				board[x][y] = lettersTable[letter.ordinal()][height - y - 1][x];
			}
		}
		setBoard(board);
		this.letter = letter;
	}

	protected Letters getLetter() {
		return letter;
	}

	/**
	 * �������������� ������ � ������ Letters
	 * 
	 * @param str
	 *            - ������ ���� "A", "B", "C", ...
	 */
	protected Letters stringToLetters(String str) {
		Letters result;

		result = Letters.None;

		try {
			result = Letters.valueOf(str);
		} catch (IllegalArgumentException e) {
			result = Letters.None;
		}

		return result;
	}

	public BoardLetters() {
		super(width, height);
		setLetter(Letters.None);
	}

}
