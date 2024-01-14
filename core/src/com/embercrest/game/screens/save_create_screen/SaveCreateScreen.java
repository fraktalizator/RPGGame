package com.embercrest.game.screens.save_create_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.Assets;

public class SaveCreateScreen implements Screen {
    private final Stage stage;
    private final Sprite backgroundSprite = new Sprite(Assets.PreGameTextureAssets.MenuBG.getTexture());

    private final SaveCreateWindow saveCreateWindow = new SaveCreateWindow();

    private final OrthographicCamera orthographicCamera = new OrthographicCamera();
    private final FitViewport fitViewport = new FitViewport(EmberCrest.VIEVPORT_WIDTH, EmberCrest.VIEVPORT_HEIGHT, orthographicCamera);

    public SaveCreateScreen() {
        stage = new Stage(fitViewport, EmberCrest.get().mainBatch);

        stage.addActor(saveCreateWindow);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        saveCreateWindow.addListener(new FocusListener() {
            @Override
            public void scrollFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                stage.setScrollFocus(actor);
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act(delta);

        stage.getBatch().begin();
        backgroundSprite.draw(stage.getBatch());
        stage.getBatch().end();


        stage.draw();
    }



    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        saveCreateWindow.resize();
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

//    public static class SaveCreateScreenStage extends Stage{
//        public SaveCreateScreenStage(Viewport viewport, Batch batch) {
//            super(viewport, batch);
//        }
//
//        @Override
//        public boolean mouseMoved(int screenX, int screenY) {
//            boolean handled = super.mouseMoved(screenX, screenY);
//            Actor target = hit(screenX, screenY, true);
//            if(target instanceof ClassButtonsContainer) setScrollFocus(target);
//            if(target instanceof ClassButton){
//                target.setTouchable(Touchable.disabled);
//                Actor target2 = hit(screenX, screenY, true);
//                if(target2 instanceof ClassButtonsContainer) setScrollFocus(target2);
//                target.setTouchable(Touchable.enabled);
//            }
//
//            if(target instanceof CustomizeOptionsContainer) setScrollFocus(target);
//            if(target instanceof CustomizeOption){
//                target.setTouchable(Touchable.disabled);
//                Actor target2 = hit(screenX, screenY, true);
//                if(target2 instanceof CustomizeOptionsContainer) setScrollFocus(target2);
//                target.setTouchable(Touchable.enabled);
//            }
//           // setScrollFocus(target);
//
//            return handled;
//        }
//    }
}
