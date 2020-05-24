package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Rock_Paper_Scissors;

public class DesktopLauncher {
	/*
	Main that runs the game, with a set height and width and runs the Rock_Paper_Scissors
	create method to start the game at main screen.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 800;
		config.width = 800;
		config.resizable = false;
		new LwjglApplication(new Rock_Paper_Scissors(), config);
	}
}
