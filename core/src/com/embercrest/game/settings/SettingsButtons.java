package com.embercrest.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.ScreenUtils;
import com.embercrest.game.settings.individual_settings_tables.VideoSettings;


public class SettingsButtons extends Table {
    public SettingsButton generalBTN, interfaceBTN, videoBTN, audioBTN, keybindingsBTN, backBTN;

    public SettingsButtons(SettingsWindow settingsWindow) {


        ButtonGroup<SettingsButton> buttonGroup = new ButtonGroup<>();
        generalBTN = new SettingsButton("General");
        interfaceBTN = new SettingsButton("Interface");
        videoBTN = new SettingsButton("Video");
        audioBTN = new SettingsButton("Audio");
        keybindingsBTN = new SettingsButton("Keybindings");
        backBTN = new SettingsButton("Back");


        buttonGroup.add(generalBTN);
        buttonGroup.add(interfaceBTN);
        buttonGroup.add(videoBTN);
        buttonGroup.add(audioBTN);
        buttonGroup.add(keybindingsBTN);

        buttonGroup.setUncheckLast(true);

        generalBTN.setChecked(true);
        generalBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.setSettingsPage(0);
            }
        });
        interfaceBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.setSettingsPage(1);
            }
        });
        videoBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.setSettingsPage(2);
            }
        });
        audioBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.setSettingsPage(3);
            }
        });
        keybindingsBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.setSettingsPage(4);
            }
        });
        backBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.setVisible(false);
                backBTN.setChecked(false);
                //generalBTN.setChecked(true);
            }
        });
    }


    public void setUpTable() {
        clear();
        setDebug(false);
        int padValue = (int) (ScreenUtils.getResolutionProportion().y*8*3);
        add(generalBTN).width(generalBTN.getWidth()).height(generalBTN.getHeight()).padTop(padValue).padBottom(padValue);
        row();
        add(interfaceBTN).width(interfaceBTN.getWidth()).height(interfaceBTN.getHeight()).padTop(padValue).padBottom(padValue);
        row();
        add(videoBTN).width(videoBTN.getWidth()).height(videoBTN.getHeight()).padTop(padValue).padBottom(padValue);
        row();
        add(audioBTN).width(audioBTN.getWidth()).height(audioBTN.getHeight()).padTop(padValue).padBottom(padValue);
        row();
        add(keybindingsBTN).width(keybindingsBTN.getWidth()).height(keybindingsBTN.getHeight()).padTop(padValue).padBottom(padValue);
        row();
        add(backBTN).width(backBTN.getWidth()).height(backBTN.getHeight()).padTop(padValue).padBottom(padValue);
        row();
        setWidth(generalBTN.getWidth());
        setHeight(generalBTN.getHeight()*6+padValue*7);
    }

    public void resize() {
        generalBTN.resize();
        interfaceBTN.resize();
        videoBTN.resize();
        audioBTN.resize();
        keybindingsBTN.resize();
        backBTN.resize();
        setUpTable();
        //setSize(generalBTN.getWidth(), generalBTN.getHeight()*6+12*7);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    static class SettingsButton extends TextButton {
        private final Texture btnUP = Assets.PreGameTextureAssets.SettingsButtonUp.getTexture();
        private final Texture btnDOWN = Assets.PreGameTextureAssets.SettingsButtonDown.getTexture();
        private final Texture btnCHECKED = Assets.PreGameTextureAssets.SettingsButtonChecked.getTexture();

        public SettingsButton(String text){
            super(text, Assets.get().getSkin());
            setName(text);
            setTouchable(Touchable.enabled);
            BitmapFont bitmapFont = Assets.get().getSkin().getFont("title");

            TextButtonStyle textButtonStyle = new TextButtonStyle(
                    new TextureRegionDrawable((btnUP)),
                    new TextureRegionDrawable((btnDOWN)),
                    new TextureRegionDrawable((btnCHECKED)),
                    bitmapFont);
            textButtonStyle.over = new TextureRegionDrawable((btnDOWN));

            setStyle(textButtonStyle);
        }

        public void resize(){
            Vector2 size = new Vector2(btnUP.getWidth(), btnUP.getHeight());
            Vector2 scaledSize = ScreenUtils.getScaledSize(size);

            setSize(scaledSize.x, scaledSize.y);

            getLabel().setFontScale(ScreenUtils.getResolutionProportion().x);

        }

    }
}
