package com.kry.brickgame;

import java.util.Random;

public class TetrisShape extends Shape implements Cloneable {

	/**
	 * ���������� ����� ������ ({@value} )
	 */
	private final static int LENGTH = 4;

	/**
	 * ������ - ���������
	 */
	enum Tetrominoes {
		NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape
	};

	/**
	 * ���� �������� ������ (� ��������)
	 */
	enum RotationAngle {
		d0, d90, d180, d270;

		/**
		 * ��������� ���� �������� ������ ������� �������
		 */
		public RotationAngle getLeft() {
			return this.ordinal() > 0 ? RotationAngle.values()[this.ordinal() - 1]
					: RotationAngle.values()[RotationAngle.values().length - 1];
		}

		/**
		 * ��������� ���� �������� �� ������� �������
		 */
		public RotationAngle getRight() {
			return this.ordinal() < RotationAngle.values().length - 1 ? RotationAngle
					.values()[this.ordinal() + 1] : RotationAngle.values()[0];
		}
	};

	/**
	 * ����� ������
	 */
	private Tetrominoes shape;
	/**
	 * ���� ��������
	 */
	private RotationAngle rotationAngle;

	/**
	 * ������� ��������� ����� �����:
	 * [������_������][������_�����][����������:0-x,1-y]
	 */
	private final static int[][][] coordsTable = new int[][][] {
			{ { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, // NoShape
			{ { -1, 0 }, { 0, -1 }, { 0, 0 }, { -1, 1 } }, // ZShape
			{ { -1, -1 }, { 0, 0 }, { -1, 0 }, { 0, 1 } }, // SShape
			{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, // LineShape
			{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, // TShape
			{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, // SquareShape
			{ { 0, -1 }, { 1, 0 }, { 1, -1 }, { 1, 1 } }, // LShape
			{ { 0, -1 }, { 1, -1 }, { 0, 0 }, { 0, 1 } } }; // MirroredLShape

	public TetrisShape() {
		super(LENGTH);
		setShape(Tetrominoes.NoShape);
	}

	/**
	 * ���������� �����������
	 * 
	 * @param aTetrisShape
	 *            - ������ ��� �����������
	 */
	public TetrisShape(TetrisShape aTetrisShape) {
		super(LENGTH);
		setShape(aTetrisShape.shape, aTetrisShape.rotationAngle);
	}

	public TetrisShape clone() {
		TetrisShape newTetrisShape = new TetrisShape(this);
		return newTetrisShape;

	}

	/**
	 * ����� ������
	 * 
	 * @param shape
	 *            - ������
	 * @param rotationAngle
	 *            - ���� ��������
	 */
	public void setShape(Tetrominoes shape, RotationAngle rotationAngle) {
		for (int i = 0; i < LENGTH; ++i) {
			switch (rotationAngle) {
			case d0:
				setX(i, coordsTable[shape.ordinal()][i][0]);
				setY(i, coordsTable[shape.ordinal()][i][1]);
				break;
			case d90:
				setX(i, coordsTable[shape.ordinal()][i][1]);
				setY(i, -coordsTable[shape.ordinal()][i][0]);
				break;
			case d180:
				setX(i, -coordsTable[shape.ordinal()][i][0]);
				setY(i, -coordsTable[shape.ordinal()][i][1]);
				break;
			case d270:
				setX(i, -coordsTable[shape.ordinal()][i][1]);
				setY(i, coordsTable[shape.ordinal()][i][0]);
				break;
			}
		}

		// �������� ����� ������� ������ ����� � ������������ ������:
		// ����� ������ � �����

		if ((minX() < -1) || (maxX() <= 0) && !(shape == Tetrominoes.LineShape)) {
			for (int i = 0; i < LENGTH; ++i) {
				setX(i, x(i) + 1);
			}
		}
		if ((minY() < 0) || (maxY() <= 0)) {
			for (int i = 0; i < LENGTH; ++i) {
				setY(i, y(i) + 1);
			}
		}

		// while (minX() != 0) {
		// if (minX() < 0) {
		// for (int i = 0; i < LENGTH; ++i) {
		// setX(i, x(i) + 1);
		// }
		// } else {
		// for (int i = 0; i < LENGTH; ++i) {
		// setX(i, x(i) - 1);
		// }
		// }
		// }
		// while (minY() != 0) {
		// if (minY() < 0) {
		// for (int i = 0; i < LENGTH; ++i) {
		// setY(i, y(i) + 1);
		// }
		// } else {
		// for (int i = 0; i < LENGTH; ++i) {
		// setY(i, y(i) - 1);
		// }
		// }
		// }

		this.shape = shape;
		this.rotationAngle = rotationAngle;
	}

	/**
	 * ����� ������ (��� ��������)
	 * 
	 * @param shape
	 *            - ������
	 */
	public void setShape(Tetrominoes shape) {
		setShape(shape, RotationAngle.d0);
	}

	public Tetrominoes getShape() {
		return shape;
	}

	/**
	 * ����� ��������� ������
	 */
	public void setRandomShape() {
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;
		Tetrominoes[] values = Tetrominoes.values();
		setShape(values[x]);
	}

	/**
	 * ����� ���������� ���� ��������
	 */
	public void setRandomRotate() {
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % 3 + 1;
		RotationAngle[] values = RotationAngle.values();
		setShape(getShape(), values[x]);
	}

	/**
	 * ����� ��������� {@link #setRandomShape ������} � ����������
	 * {@link #setRandomRotate ���� ��������}
	 */
	public void setRandomShapeAndRotate() {
		setRandomShape();
		setRandomRotate();
	}

	/**
	 * ������� ������ ������� �������
	 */
	public TetrisShape rotateLeft() {
		if (getShape() != Tetrominoes.SquareShape)
			setShape(getShape(), this.rotationAngle.getLeft());
		return this;
	}

	/**
	 * ������� �� ������� �������
	 */
	public TetrisShape rotateRight() {
		if (getShape() != Tetrominoes.SquareShape)
			setShape(getShape(), this.rotationAngle.getRight());
		return this;
	}

	@Override
	public String toString() {
		return "TetrisShape [" + this.getShape() + ", " + this.rotationAngle //������ � ���� ��������
				+ ", (" + minX() + ";" + minY() + ")" + "]\n" //����� ������ ����
				+ super.toString();
	}

}
