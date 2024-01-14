package com.embercrest.game.settings.individual_settings_tables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;

import java.util.HashMap;

public class AudioSettings extends Table {
    private final Label label = new Label("Audio Settings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private final Skin skin = Assets.get().getSkin();
    private final HashMap<String, String> audioSettingsData;
    public CheckBox audio;

    public AudioSettings( HashMap<String, String> audioSettingsData) {
        this.audioSettingsData = audioSettingsData;

        initSettings();

        initData();

        initTable();
    }

    private void initSettings() {
        audio = new CheckBox("audio", skin);

    }

    private void initData() {
        audio.setChecked(Boolean.parseBoolean(audioSettingsData.get("audio")));
    }


    private void initTable() {
        setDebug(false);
        add(label).expand().colspan(2);
        row();
        add(audio).expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
        row();
        add().expand();
        add().expand();
    }

    public HashMap<String, String> getCurrentAudioSettingsData(){
        HashMap<String, String> audioSettingsData = new HashMap<>();
        audioSettingsData.put("audio", audio.isChecked()+"");

        return audioSettingsData;
    }
}
