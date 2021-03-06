package com.kry.brickgame.IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;

import com.kry.brickgame.games.Game;
import com.kry.brickgame.games.GameSelector;
import com.kry.brickgame.games.SplashScreen;

/**
 * @author noLive
 */
public enum GameLoader {
	;
	
	private final static String SAVED_GAME_FILE = "lastgame.sav";
	
	/**
	 * Delete a file of the saved game.
	 * 
	 * @return {@code true} if success; {@code false} otherwise
	 */
	public static boolean deleteSavedGame() {
		return IOUtils.deleteFile(SAVED_GAME_FILE);
	}
	
	/**
	 * Load the saved game from a file.
	 * 
	 * @return {@code Game} if success; {@code null} otherwise
	 */
	public static Game loadGame() {
		try (InputStream is = IOUtils.getInputStream(SAVED_GAME_FILE);
				ObjectInputStream ois = new ObjectInputStream(is)) {
			return (Game) ois.readObject();
		} catch (ReflectiveOperationException | ObjectStreamException | ClassCastException e) {
			System.err.println("The gamesave file was corrupted and will be removed:\n" + e);
			// delete corrupted file
			deleteSavedGame();
			return null;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Save the game to a file.
	 * 
	 * @param game
	 *            the game for saving
	 * @return {@code true} if success; {@code false} otherwise
	 */
	public static <T extends Game> boolean saveGame(T game) {
		if (game instanceof GameSelector || game instanceof SplashScreen) return false;
		try (OutputStream os = IOUtils.getOutputStream(SAVED_GAME_FILE);
				ObjectOutputStream oos = new ObjectOutputStream(os)) {
			oos.writeObject(game);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
