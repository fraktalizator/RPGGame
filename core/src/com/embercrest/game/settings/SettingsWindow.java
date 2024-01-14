package com.embercrest.game.settings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.settings.individual_settings_tables.GeneralSettings;
import com.embercrest.game.settings.individual_settings_tables.InterfaceSettings;
import com.embercrest.game.settings.individual_settings_tables.AudioSettings;
import com.embercrest.game.settings.individual_settings_tables.KeybindingsSettings;
import com.embercrest.game.settings.individual_settings_tables.VideoSettings;
import com.embercrest.game.Assets;

import java.util.HashMap;

public class SettingsWindow extends Window {
    private final SettingsFileManager settingsFileManager = new SettingsFileManager();
    private final Texture bgTexture = Assets.PreGameTextureAssets.SettingsWindowBG.getTexture();
    private int currentPage=0;


    private final SettingsButtons settingsButtons;
    public GeneralSettings generalSettings;
    public InterfaceSettings interfaceSettings;
    public AudioSettings audioSettings;
    public KeybindingsSettings keybindingsSettings;
    public VideoSettings videoSettings;

    private final TextButton resetAllSettings = new TextButton("Reset settings to default", Assets.get().getSkin());

    public SettingsWindow() {
        super("", Assets.get().getSkin());
        setMovable(false);
        setTouchable(Touchable.enabled);
        setVisible(false);

        setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));

        readSettingsDataFromFile();

        resetAllSettings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetSettingsToDefault();
            }
        });

        settingsButtons = new SettingsButtons(this);
        videoSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
            }
        });
        generalSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
            }
        });
        interfaceSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
            }
        });
        keybindingsSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
            }
        });
        audioSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
            }
        });

    }

    public void show() {
        resize();
        setVisible(true);
        toFront();
    }

    public void setSettingsPage(int i){
        switch (i){
            case 0:
                clear();
                add(settingsButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*10/284).padTop(getHeight()*12/178);
                add(generalSettings).expand().right().top().width(getWidth()*100/136).height(getHeight()*100/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 0;
                break;
            case 1:
                clear();
                add(settingsButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*10/284).padTop(getHeight()*12/178);
                add(interfaceSettings).expand().right().top().width(getWidth()*100/136).height(getHeight()*100/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 1;
                break;
            case 2:
                clear();
                add(settingsButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*10/284).padTop(getHeight()*12/178);
                add(videoSettings).expand().right().top().width(getWidth()*100/136).height(getHeight()*100/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 2;
                break;
            case 3:
                clear();
                add(settingsButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*10/284).padTop(getHeight()*12/178);
                add(audioSettings).expand().right().top().width(getWidth()*100/136).height(getHeight()*100/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 3;
                break;
            case 4:
                clear();
                add(settingsButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*10/284).padTop(getHeight()*12/178);
                add(keybindingsSettings).expand().right().top().width(getWidth()*100/136).height(getHeight()*100/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 4;
                break;
        }
    }

    private void saveSettings(){
        HashMap<String, HashMap<String, String>> SettingsData = new HashMap<>();
        HashMap<String, String> generalSettingsData = generalSettings.GetCurrentGeneralSettingsData();
        HashMap<String, String> videoSettingsData = videoSettings.GetCurrentVideoSettingsData();
        HashMap<String, String> interfaceSettingsData = interfaceSettings.getCurrentInterfaceSettingsData();
        HashMap<String, String> keybindingsSettingsData = keybindingsSettings.getCurrentKeybindingsSettingsData();
        HashMap<String, String> audioSettingsData = audioSettings.getCurrentAudioSettingsData();

        SettingsData.put("GeneralSettings", generalSettingsData);
        SettingsData.put("VideoSettings", videoSettingsData);
        SettingsData.put("InterfaceSettings", interfaceSettingsData);
        SettingsData.put("KeybindingsSettings", keybindingsSettingsData);
        SettingsData.put("AudioSettings", audioSettingsData);
        settingsFileManager.saveSettings(SettingsData);
    }

    public void readSettingsDataFromFile(){
        HashMap<String, HashMap<String, String>> settingsDataFromFile = settingsFileManager.getSafeData();
        generalSettings = new GeneralSettings(settingsDataFromFile.get("GeneralSettings"), resetAllSettings);
        interfaceSettings = new InterfaceSettings(settingsDataFromFile.get("InterfaceSettings"));
        audioSettings = new AudioSettings(settingsDataFromFile.get("AudioSettings"));
        keybindingsSettings = new KeybindingsSettings(settingsDataFromFile.get("KeybindingsSettings"));
        videoSettings = new VideoSettings(settingsDataFromFile.get("VideoSettings"));
    }

    public void resetSettingsToDefault(){
        settingsFileManager.createDefaultSettings();
        readSettingsDataFromFile();
        setSettingsPage(currentPage);
    }

    public void resize(){
        setSize(EmberCrest.VIEVPORT_WIDTH /64*48, EmberCrest.VIEVPORT_HEIGHT /64*48);

        setX(EmberCrest.VIEVPORT_WIDTH /2- EmberCrest.VIEVPORT_WIDTH /64*48/2);
        setY(EmberCrest.VIEVPORT_HEIGHT /2- EmberCrest.VIEVPORT_HEIGHT /64*48/2);

        setBounds(getX(), getY(), getWidth(), getHeight());

        settingsButtons.resize();

        setSettingsPage(currentPage);
    }
}
