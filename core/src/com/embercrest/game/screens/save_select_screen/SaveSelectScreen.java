package com.embercrest.game.screens.save_select_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.Assets;

public class SaveSelectScreen implements Screen{
    private final SaveSelectWindow saveSelectWindow = new SaveSelectWindow();
    private final Sprite backgroundSprite= new Sprite(Assets.PreGameTextureAssets.MenuBG.getTexture());
    private final Stage stage;

    public SaveSelectScreen() {
        OrthographicCamera camera = new OrthographicCamera();
        stage = new Stage(new ScreenViewport(camera), EmberCrest.get().mainBatch);

        stage.setScrollFocus(saveSelectWindow.scrollPane);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(saveSelectWindow);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.act(delta);

        stage.getBatch().begin();
        backgroundSprite.draw(stage.getBatch());
        stage.getBatch().end();

        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        saveSelectWindow.resize();
        stage.setScrollFocus(saveSelectWindow.scrollPane);
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
        stage.dispose();
    }
}
