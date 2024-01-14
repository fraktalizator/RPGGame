package com.embercrest.game.screens.save_select_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.ScreenUtils;
import com.embercrest.game.settings.individual_settings_tables.VideoSettings;
import com.embercrest.game.game_tools.Save;
import com.embercrest.game.game_tools.SaveFileManager;
import com.embercrest.game.screens.MenuScreen;
import com.embercrest.game.Assets;
import com.embercrest.game.screens.loading_screen.LoadingScreen;

import java.util.ArrayList;

public class SaveSelectWindow extends Table {
    public static ArrayList<SaveButton> saveButtons = new ArrayList<>();
    private final Texture backgroundTexture = Assets.PreGameTextureAssets.SaveSelectWindowBG.getTexture();
    public ScrollPane scrollPane;

    public SaveSelectWindow() {
        //super("", Assets.get().getSkin());
        super();
        setBackground(new TextureRegionDrawable(backgroundTexture));
        //setMovable(false);

        refreshSaves();
    }

    public void refreshSaves(){
        disposeSaveButtons();
        loadSaveButtons();
        clear();
        resize();

        Table Saves = new Table();
        Saves.setDebug(false);
        for(SaveButton saveButton:saveButtons) {
            Saves.add(saveButton).padTop(7.5f).padBottom(7.5f).height(saveButton.getHeight()).width(saveButton.getWidth());
            Saves.row();
        }
        scrollPane = new ScrollPane(Saves, Assets.get().getSkin());
        scrollPane.setDebug(false);
        scrollPane.setScrollingDisabled(true, false);

        add(scrollPane).width(getWidth()).height(getHeight()/ backgroundTexture.getHeight()*(824-1)).expand().top().padTop(getHeight()/ backgroundTexture.getHeight()*6);
        row();
        setUpButtons();

    }

    private void setUpButtons() {
        // buttons
        TextButton backButton = new TextButton("<- Back", Assets.get().getSkin());
        TextButton playButton = new TextButton("Play ->", Assets.get().getSkin());
        EmberCrest game = (EmberCrest)Gdx.app.getApplicationListener();
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen());
                //TODO call screen dispose
                dispose();
            }
        });
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SaveButton selectedSave = getSelectedSaveButton();
                if(selectedSave != null){
                    game.setScreen(new LoadingScreen(selectedSave.getSave()));
                    dispose();
                    //TODO call screen dispose
                }//TODO warning text else warningText.popMessage(2);
                game.setScreen(new LoadingScreen());
            }
        });

        Table buttonsTable = new Table();
        buttonsTable.setDebug(false);
        buttonsTable.setWidth(getWidth());
        buttonsTable.setHeight(getHeight()/backgroundTexture.getHeight()*96);
        buttonsTable.add(backButton).expand().left().padLeft(backButton.getWidth()/1.5f);
        buttonsTable.add(playButton).expand().right().padRight(playButton.getWidth()/1.5f);
        add(buttonsTable).width(buttonsTable.getWidth()).height(buttonsTable.getHeight()).bottom().expand().padBottom(getHeight()/ backgroundTexture.getHeight()*6);//.padTop(getHeight()/backgroundTexture.getHeight()*32);
    }

    public SaveButton getSelectedSaveButton(){
        for(SaveButton saveButton: saveButtons){
            if(saveButton.selected) return saveButton;
        }
        return null;
    }


    public void resize(){
        Vector2 resolution = EmberCrest.get().settingsWindow.videoSettings.resolution.getSize();

        Vector2 size = new Vector2(backgroundTexture.getWidth(), backgroundTexture.getHeight());

        Vector2 scaledSize = ScreenUtils.getScaledSize(size.x, size.y);
        setWidth(scaledSize.x);
        setHeight(scaledSize.y);

        Vector2 centerPos = ScreenUtils.getScreenCenterOfImageSizeOfActor(this);

        setPosition(centerPos.x, centerPos.y);
        setBounds(getX(),getY(),getWidth(),getHeight());

        for(int i=0;i<saveButtons.size();i++){
            saveButtons.get(i).resize();
        }
    }

    private void loadSaveButtons(){
        SaveFileManager saveFileManager = new SaveFileManager();
        for( Save save: saveFileManager.getSavesList()){
            saveButtons.add(new SaveButton(save));
        }
    }

    public void disposeSaveButtons(){
        if(saveButtons == null) return;
        for(int i=0;i<saveButtons.size();i++){
            saveButtons.get(i).dispose();
        }
        saveButtons = new ArrayList<>(0);
    }

    public void dispose() {
        disposeSaveButtons();
    }
}

