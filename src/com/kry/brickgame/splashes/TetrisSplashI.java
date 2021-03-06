package com.kry.brickgame.splashes;

/**
 * @author noLive
 */
public class TetrisSplashI extends Splash {
	private static final long serialVersionUID = 534555333873259137L;
	
	private static final Cell[][][] frameTable = new Cell[][][] { {
	        // 0
	        { E, E, E, E, F, F, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F } }, {
	        // 1
	        { E, E, F, F, E, E, E, E, E, E }, //
	        { E, E, E, F, F, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F } }, {
	        // 2
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, F, E, E, E, E, E, E, E }, //
	        { E, E, F, F, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F } }, {
	        // 3
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, E, F, F, E, E, E, E, E, E }, //
	        { E, E, F, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F } }, {
	        // 4
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, E, F, F, E, E, E, E, E, E }, //
	        { E, F, F, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F } }, {
	        // 5
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E }, //
	        { B, B, B, B, B, B, B, B, B, B } }, {
	        // 5.5
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E }, //
	        { B, B, B, B, B, B, B, B, B, B } }, {
	        // 6
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E } }, {
	        // 7
	        { E, E, E, E, E, F, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E } }, {
	        // 8
	        { E, E, E, E, F, F, F, E, E, E }, //
	        { E, E, E, E, E, E, F, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E } }, {
	        // 9
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, F, F, F, E, E }, //
	        { E, E, E, E, E, E, E, F, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E } }, {
	        // 10
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, F, F, F, E, E }, //
	        { E, E, E, E, E, E, E, F, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E } }, {
	        // 11
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, F, F, F, E, E }, //
	        { E, E, E, F, E, E, E, F, E, E }, //
	        { E, F, F, F, F, F, F, E, F, E } }, {
	        // 12
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 13
	        { E, E, E, E, F, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 14
	        { E, E, E, F, F, E, E, E, E, E }, //
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 15
	        { E, E, E, F, E, E, E, E, E, E }, //
	        { E, E, F, F, E, E, E, E, E, E }, //
	        { E, E, F, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 16
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, F, E, E, E, E, E, E, E }, //
	        { E, F, F, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 17
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, E, E, E, E, E, E, E }, //
	        { F, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 18
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, E, E, E, E, E, E, E }, //
	        { F, E, E, F, E, F, F, F, E, E }, //
	        { E, F, F, F, F, F, F, F, F, E } }, {
	        // 19
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, F, E, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 20
	        { E, E, E, E, F, F, F, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, F, E, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 21
	        { E, E, E, E, E, E, F, E, E, E }, //
	        { E, E, E, E, F, F, F, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, F, E, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 22
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, F, F, E, E, E }, //
	        { E, E, E, E, E, E, F, E, E, E }, //
	        { E, E, E, E, E, E, F, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, F, E, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 23
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, F, F, E, E, E, E }, //
	        { E, E, E, E, E, F, E, E, E, E }, //
	        { E, E, E, E, E, F, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, F, E, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 24
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, F, F, F, E, E, E }, //
	        { E, E, E, E, F, E, E, E, E, E }, //
	        { E, F, E, E, E, E, E, E, E, E }, //
	        { F, F, E, F, E, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 25
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, E, E }, //
	        { F, F, E, F, F, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 26
	        { E, E, E, E, E, F, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, E, E }, //
	        { F, F, E, F, F, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 27
	        { E, E, E, E, E, F, F, E, E, E }, //
	        { E, E, E, E, E, E, F, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, E, E }, //
	        { F, F, E, F, F, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 28
	        { E, E, E, E, E, E, F, E, E, E }, //
	        { E, E, E, E, E, E, F, F, E, E }, //
	        { E, E, E, E, E, E, E, F, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, E, E }, //
	        { F, F, E, F, F, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 29
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, F, E, E }, //
	        { E, E, E, E, E, E, E, F, F, E }, //
	        { E, E, E, E, E, E, E, E, F, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, E, E }, //
	        { F, F, E, F, F, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 30
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, F, E }, //
	        { E, E, E, E, E, E, E, E, F, F }, //
	        { E, E, E, E, E, E, E, E, E, F }, //
	        { E, F, E, E, F, F, F, E, E, E }, //
	        { F, F, E, F, F, F, F, F, E, E }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 31
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, F, E }, //
	        { E, F, E, E, F, F, F, E, F, F }, //
	        { F, F, E, F, F, F, F, F, E, F }, //
	        { F, F, F, F, F, F, F, F, F, E } }, {
	        // 32
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F }, //
	        { B, B, B, B, B, B, B, B, B, B } }, {
	        // 32.5
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F }, //
	        { B, B, B, B, B, B, B, B, B, B } }, {
	        // 33
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, E, E, E, E, E, E, E, E, E }, //
	        { E, F, E, E, F, F, F, E, F, E }, //
	        { F, F, E, F, F, F, F, F, F, F } }, };
	
	public TetrisSplashI() {
		super(frameTable);
	}
	
	@Override
	public int getDelay() {
		final int SPLASH_DELAY = 200;
		return SPLASH_DELAY;
	}
	
}
