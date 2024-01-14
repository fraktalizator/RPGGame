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

public class InterfaceSettings extends Table {
    private final Label label = new Label("Interface Settings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private final Skin skin = Assets.get().getSkin();
    private final HashMap<String, String> interfaceSettingsData;

    public CheckBox inf;
    public InterfaceSettings(HashMap<String, String> interfaceSettingsData) {
        this.interfaceSettingsData = interfaceSettingsData;

        initSettings();

        initData();

        initTable();

    }

    private void initSettings() {
        inf = new CheckBox("interface", skin);
    }

    private void initData() {
        inf.setChecked(Boolean.parseBoolean(interfaceSettingsData.get("inf")));
    }

    private void initTable() {
        setDebug(false);
        add(label).expand().colspan(2);
        row();
        add(inf).expand();
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

    public HashMap<String, String> getCurrentInterfaceSettingsData(){
        HashMap<String, String> interfaceSettingsData = new HashMap<>();
        interfaceSettingsData.put("inf", inf.isChecked()+"");

        return interfaceSettingsData;
    }
}
