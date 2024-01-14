package com.embercrest.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.screens.save_create_screen.SaveCreateScreen;
import com.embercrest.game.screens.save_select_screen.SaveSelectScreen;
import com.embercrest.game.Assets;

public class MenuScreen implements Screen {
    private final Image backgroundImage = new Image(Assets.PreGameTextureAssets.MenuBG.getTexture());
    private Table table;
    private TextButton playButton, settingsButton, newGameButton, exitButton;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final FitViewport viewport = new FitViewport(EmberCrest.VIEVPORT_WIDTH, EmberCrest.VIEVPORT_HEIGHT, camera);

    private final Stage stage = new Stage(viewport, EmberCrest.get().mainBatch);


    @Override
    public void show() {
        backgroundImage.setSize(EmberCrest.VIEVPORT_WIDTH, EmberCrest.VIEVPORT_HEIGHT);
        setUpContent();

        stage.addActor(EmberCrest.get().settingsWindow);
        stage.addActor(backgroundImage);
        stage.addActor(table);

        backgroundImage.setZIndex(0);
        table.setZIndex(1);
        playButton.setZIndex(2);
        settingsButton.setZIndex(2);
        newGameButton.setZIndex(2);
        exitButton.setZIndex(2);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act(delta);
        stage.draw();

    }

    private void setUpContent() {
        setUpButtons();
        setUpTable();
    }

    private void setUpButtons() {
        playButton = new TextButton("Play demo", Assets.get().getSkin());


        playButton.setBounds(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        //playButton.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        newGameButton = new TextButton("New game", Assets.get().getSkin());
        newGameButton.setSize(2f, 1f);
        settingsButton = new TextButton("Settings", Assets.get().getSkin());
        settingsButton.setSize(2f, 1f);
        exitButton = new TextButton("Exit", Assets.get().getSkin());
        exitButton.setSize(2f, 1f);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                EmberCrest.get().setScreen(new SaveSelectScreen());
                dispose();
            }
        });
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //EmberCrest.GetGame().setScreen(new SaveCreateScreen2(EmberCrest.GetGame()));
                EmberCrest.get().setScreen(new SaveCreateScreen());
                dispose();
            }
        });

        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                EmberCrest.get().settingsWindow.show();
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO exit warning window
                Gdx.app.exit();
            }
        });
    }

    private void setUpTable() {
        //TODO resize padding on different resolutions
        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.add(playButton);
        table.row();
        table.add(newGameButton).padTop(45);
        table.row();
        table.add(settingsButton).padTop(45);
        table.row();
        table.add(exitButton).padTop(45);
    }


    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
        EmberCrest.get().settingsWindow.resize();
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
