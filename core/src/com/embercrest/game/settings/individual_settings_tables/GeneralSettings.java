package com.embercrest.game.settings.individual_settings_tables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;

import java.util.HashMap;

public class GeneralSettings extends Table {
    private final Label label = new Label("General Settings", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    private final Skin skin = Assets.get().getSkin();
    private final HashMap<String, String> generalSettingsData;

    public CheckBox autoSave, autoLoot, scrollCombatText;

    public GeneralSettings(HashMap<String, String> generalSettingsData, TextButton resetAllSettings) {
        this.generalSettingsData = generalSettingsData;

        initSettings();

        initData();

        initTable(resetAllSettings);

    }

    private void initData() {
        autoLoot.setChecked(Boolean.parseBoolean(generalSettingsData.get("AutoLoot")));
        autoSave.setChecked(Boolean.parseBoolean(generalSettingsData.get("AutoSave")));
        scrollCombatText.setChecked(Boolean.parseBoolean(generalSettingsData.get("ScrollCombatText")));
    }

    private void initSettings() {
        autoSave = new CheckBox("Enables auto Save On(auto save after every won battle(up to 3 auto saves))", skin);
        autoLoot = new CheckBox("Enables auto Loot, if enough place in inventory", skin);
        scrollCombatText = new CheckBox("Displays out of combat heal and damage text", skin);
    }

    private void initTable(TextButton resetAllSettings) {
        setDebug(false);
        add(label).expand().colspan(2);
        row();
        add(autoSave).expand().colspan(2);
        row();
        add(autoLoot).expand().colspan(2);
        row();
        add(scrollCombatText).expand().colspan(2);
        row();
        add().expand().colspan(2);
        row();
        add().expand().colspan(2);
        row();
        add().expand().colspan(2);
        row();
        add().expand();
        add(resetAllSettings).expand().left();
    }

    public HashMap<String, String> GetCurrentGeneralSettingsData(){
        HashMap<String, String> generalSettingsData = new HashMap<>();
        generalSettingsData.put("AutoLoot", autoLoot.isChecked()+"");
        generalSettingsData.put("AutoSave", autoSave.isChecked()+"");
        generalSettingsData.put("ScrollCombatText", scrollCombatText.isChecked()+"");

        return generalSettingsData;
    }
}
