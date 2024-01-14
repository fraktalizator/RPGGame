package com.embercrest.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.embercrest.game.settings.SettingsWindow;
import com.embercrest.game.screens.splash_screen.SplashScreen;

public class EmberCrest extends Game {
	public static final float VIEVPORT_WIDTH = 64*16f;
	public static final float VIEVPORT_HEIGHT = 64*9f;
	public SpriteBatch mainBatch;
	public SettingsWindow settingsWindow; // initialized after aseets are loaded in the SplashScreen

	private EmberCrest() {super();}

	private static EmberCrest game;

	public static EmberCrest get(){
		if(game == null) game = new EmberCrest();
		return game;
	}


	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.init("Skin/cloud-form/skin/cloud-form-ui.json");
		mainBatch = new SpriteBatch();
		this.setScreen(new SplashScreen());
	}

	@Override
	public void dispose () {
		mainBatch.dispose();
		Assets.get().dispose();
	}

}
