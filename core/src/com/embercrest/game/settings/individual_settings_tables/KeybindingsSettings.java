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

public class KeybindingsSettings extends Table {
    private final Label label = new Label("Keybindings Settings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private final Skin skin = Assets.get().getSkin();
    private final HashMap<String, String> keybindingsSettingsData;
    public CheckBox bind;
    public KeybindingsSettings(HashMap<String, String> keybindingsSettingsData) {
        this.keybindingsSettingsData = keybindingsSettingsData;

        initSettings();

        initData();

        initTable();

    }

    private void initSettings() {
        bind = new CheckBox("bind", skin);


    }

    private void initData() {
        bind.setChecked(Boolean.parseBoolean(keybindingsSettingsData.get("bind")));
    }

    private void initTable() {
        setDebug(false);
        add(label).expand().colspan(2);
        row();
        add(bind).expand();
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

    public HashMap<String, String> getCurrentKeybindingsSettingsData(){
        HashMap<String, String> keybindingsSettingsData = new HashMap<>();
        keybindingsSettingsData.put("bind", bind.isChecked()+"");

        return keybindingsSettingsData;
    }
}
