package com.kry.brickgame.shapes;

public class GunShape extends CharacterShape {

	static {
		charactersTable = new int[][][] {//
		{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, // gun
		}; //
	}

	public GunShape() {
		super(0);
	}

}
