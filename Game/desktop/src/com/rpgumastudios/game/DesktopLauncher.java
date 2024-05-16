package com.rpgumastudios.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpgumastudios.game.RPGUMAStudios;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1024, 900);
		config.setForegroundFPS(60);
		//Inicialización de los elementos gráficos
		
		config.setTitle("RPG UMA Studios");
		new Lwjgl3Application(new RPGUMAStudios(), config);
	}
}
