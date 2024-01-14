package com.embercrest.game.screens.splash_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.settings.SettingsWindow;
import com.embercrest.game.screens.MenuScreen;

public class SplashScreen implements Screen {
    private final Sprite splashSprite = new Sprite(new Texture(Gdx.files.internal("Ui/splash.png")));
    private final float splashTimerInSecs = 1.15f;
    private float elapsedTime;


    public SplashScreen() {
        splashSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
    }



    @Override
    public void render(float delta) {
        //System.out.println(Gdx.graphics.getFramesPerSecond()+" FPS");
        ScreenUtils.clear(0, 0, 0, 1);
        elapsedTime += delta;

        EmberCrest.get().mainBatch.begin();
        if(elapsedTime/splashTimerInSecs < 1) splashSprite.draw(EmberCrest.get().mainBatch, (1-elapsedTime/splashTimerInSecs));
        EmberCrest.get().mainBatch.end();

        if(!Assets.get().update()) return;

        EmberCrest.get().settingsWindow  = new SettingsWindow(); // Assets ready, so create settings window, as it is used in the very next screen.

        if(elapsedTime >= splashTimerInSecs){
            EmberCrest.get().setScreen(new MenuScreen());
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        splashSprite.setSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splashSprite.getTexture().dispose();
    }
}
