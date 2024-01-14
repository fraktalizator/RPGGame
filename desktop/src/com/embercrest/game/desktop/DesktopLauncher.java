package com.embercrest.game.desktop;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.embercrest.game.EmberCrest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.backgroundFPS = 20;
		config.title = "Ember Crest";
		config.width = 1280;
		config.height = 720;
		//config.resizable = false;
//		config.width = 1920;
//		config.height = 1080;

		new LwjglApplication(EmberCrest.get(), config);
	}
}
