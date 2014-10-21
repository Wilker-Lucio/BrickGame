package com.kry.brickgame.shapes;

/**
 * @author noLive
 * 
 */
public class DancerShape extends CharacterShape {
	/**
	 * A set of the coordinates of points of the player character:
	 * [type][point][coordinate:0-x,1-y]
	 */
	private static int[][][] charactersTable = new int[][][] {//
	{ { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { -1, 1 },
			{ 1, 1 } } // dancer
	}; //

	@Override
	protected int[][][] getCharactersTable() {
		return charactersTable;
	}

	/**
	 * Constructor for the Dancer
	 */
	public DancerShape() {
		super(0, charactersTable[0].length);
	}

}
