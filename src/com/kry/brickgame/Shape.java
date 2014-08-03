package com.kry.brickgame;


/**
 * <p>
 * ��������� ����� ��� �����
 * </p>
 */
public class Shape {

	/**
	 * ����� ��������� �����: [������_�����][����������:0-x,1-y]
	 */
	private int coords[][];

	protected int[][] getCoords() {
		return coords;
	}

	protected void setCoords(int[][] coords) {
		this.coords = coords;
	}

	/**
	 * @param length
	 *            - ���������� ����� ������
	 */
	public Shape(int length) {
		super();
		coords = new int[length][2];
	}

	/**
	 * ������������� �������� ���������� x ����� ������
	 * 
	 * @param index
	 *            - ������ ����� ������
	 * @param x
	 *            - �������� ���������� x
	 */
	protected void setX(int index, int x) {
		coords[index][0] = x;
	}

	/**
	 * ������������� �������� ���������� y ����� ������
	 * 
	 * @param index
	 *            - ������ ����� ������
	 * @param y
	 *            - �������� ���������� y
	 */
	protected void setY(int index, int y) {
		coords[index][1] = y;
	}

	public int x(int index) {
		return coords[index][0];
	}

	public int y(int index) {
		return coords[index][1];
	}

	/**
	 * @return ����������� �������� ���������� "x" ����� ���� ����� ������
	 */
	public int minX() {
		int result = coords[0][0];
		for (int i = 0; i < coords.length; i++) {
			result = Math.min(result, coords[i][0]);
		}
		return result;
	}

	/**
	 * @return ����������� �������� ���������� "y" ����� ���� ����� ������
	 */
	public int minY() {
		int result = coords[0][1];
		for (int i = 0; i < coords.length; i++) {
			result = Math.min(result, coords[i][1]);
		}
		return result;
	}

	/**
	 * @return ������������ �������� ���������� "x" ����� ���� ����� ������
	 */
	public int maxX() {
		int result = coords[0][0];
		for (int i = 0; i < coords.length; i++) {
			result = Math.max(result, coords[i][0]);
		}
		return result;
	}

	/**
	 * @return ������������ �������� ���������� "y" ����� ���� ����� ������
	 */
	public int maxY() {
		int result = coords[0][1];
		for (int i = 0; i < coords.length; i++) {
			result = Math.max(result, coords[i][1]);
		}
		return result;
	}

	/**
	 * �������� ������ ������ ������� �������
	 * 
	 * @param shape
	 *            - ��������� ������
	 */
	public Shape rotateLeft(Shape shape) {
		Shape result = new Shape(shape.coords.length);

		for (int i = 0; i < shape.coords.length; ++i) {
			result.setX(i, shape.y(i));
			result.setY(i, -shape.x(i));
		}
		return result;
	}

	/**
	 * �������� ������ �� ������� �������
	 * 
	 * @param shape
	 *            - ��������� ������
	 */
	public Shape rotateRight(Shape shape) {
		Shape result = new Shape(shape.coords.length);

		for (int i = 0; i < shape.coords.length; ++i) {
			result.setX(i, -shape.y(i));
			result.setY(i, shape.x(i));
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append("Shape\n");

		int min_x = minX();
		int max_x = maxX();
		int min_y = minY();
		int max_y = maxY();

		// ���������� �������� �� max_y �� min_y (������ ����) �
		// �� min_x �� max_x (����� �������) �, ���� �������� x � y �������� ��
		// ��������� ��������� � �����-���� �����, �� �� ������ x,y ������ 0,
		// ����� " " (������).
		// [y + (0 - min_x) - �������� ����� ������� ��� ������� �������� �
		// ����.
		// ����������:
		// _0_ | 0__ | 00
		// 000 | 000 | 00
		for (int x = max_y; x >= min_y; --x) {
			char line[] = new char[(max_x - min_x) + 1];
			for (int y = min_x; y <= max_x; ++y) {
				line[y + (0 - min_x)] = ' ';
				for (int k = 0; k < coords.length; ++k) {
					if ((coords[k][0] == y) && (coords[k][1] == x)) {
						line[y + (0 - min_x)] = '0';
						break;
					}
				}
			}
			result.append(line).append("\n");
		}
		return result.toString();
	}

}
