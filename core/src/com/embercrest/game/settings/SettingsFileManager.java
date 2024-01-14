package com.embercrest.game.settings;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.embercrest.game.settings.individual_settings_tables.VideoSettings;

import java.util.HashMap;

public class SettingsFileManager {
    public Json json = new Json(JsonWriter.OutputType.json);
    public FileHandle settingsFile = Gdx.files.absolute("RPGGame/settings.json");


    public void saveSettings(HashMap<String, HashMap<String, String>> settingsData){
        String dataJsonString = json.toJson(settingsData);
        settingsFile.writeString(dataJsonString, false);
    }

    public HashMap<String, HashMap<String, String>> getSafeData(){
        HashMap<String, HashMap<String, String>> settingsData;
        //TODO costam z exceptionami lepiej mozna by zrobic a nie nullpointer
        try {
            settingsData = getUnsafeSettingsData();
            HashMap<String, String> generalSettingsData = settingsData.get("GeneralSettings");
            HashMap<String, String> videoSettingsData = settingsData.get("VideoSettings");
            HashMap<String, String> interfaceSettingsData = settingsData.get("InterfaceSettings");
            HashMap<String, String> keybindingsSettingsData = settingsData.get("KeybindingsSettings");
            HashMap<String, String> audioSettingsData =settingsData.get("AudioSettings");

            // ---------------- GENERAL SETTINGS CHECK ---------------- //
            if(generalSettingsData.get("AutoLoot") == null) throw new NullPointerException();
            Boolean.parseBoolean(generalSettingsData.get("AutoLoot"));

            if(generalSettingsData.get("AutoSave") == null) throw new NullPointerException();
            Boolean.parseBoolean(generalSettingsData.get("AutoSave"));

            if(generalSettingsData.get("ScrollCombatText") == null) throw new NullPointerException();
            Boolean.parseBoolean(generalSettingsData.get("ScrollCombatText"));


            // ---------------- VIDEO SETTINGS CHECK ---------------- //
            Integer.parseInt(videoSettingsData.get("ResolutionWidth"));

            Integer.parseInt(videoSettingsData.get("ResolutionHeight"));

            if(videoSettingsData.get("FullScreen") == null) throw new NullPointerException();
            Boolean.parseBoolean(videoSettingsData.get("FullScreen"));

            // ---------------- INTERFACE SETTINGS CHECK ---------------- //
            if(interfaceSettingsData.get("inf") == null) throw new NullPointerException();
            Boolean.parseBoolean(interfaceSettingsData.get("inf"));


            // ---------------- KEYBINDINGS SETTINGS CHECK ---------------- //
            if(keybindingsSettingsData.get("bind") == null) throw new NullPointerException();
            Boolean.parseBoolean(keybindingsSettingsData.get("bind"));

            // ---------------- AUDIO SETTINGS CHECK ---------------- //
            if(audioSettingsData.get("audio") == null) throw new NullPointerException();
            Boolean.parseBoolean(audioSettingsData.get("audio"));


        }catch (Exception e){
            createNecessaryDirectory();
            createDefaultSettings();
            settingsData = getUnsafeSettingsData();
        }
        return settingsData;
    }

    private HashMap<String, HashMap<String, String>> getUnsafeSettingsData(){
        String rawFileData = settingsFile.readString();
        HashMap<String, JsonValue> unConvertedData = json.fromJson(HashMap.class , rawFileData);

        HashMap<String, String> generalSettings= json.readValue(HashMap.class, String.class, unConvertedData.get("GeneralSettings"));
        HashMap<String, String> interfaceSettings = json.readValue(HashMap.class, String.class, unConvertedData.get("InterfaceSettings"));
        HashMap<String, String> videoSettings = json.readValue(HashMap.class, String.class, unConvertedData.get("VideoSettings"));
        HashMap<String, String> keybindingsSettings = json.readValue(HashMap.class, String.class, unConvertedData.get("KeybindingsSettings"));
        HashMap<String, String> audioSettings = json.readValue(HashMap.class, String.class, unConvertedData.get("AudioSettings"));
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        data.put("GeneralSettings", generalSettings);
        data.put("VideoSettings", videoSettings);
        data.put("InterfaceSettings", interfaceSettings);
        data.put("KeybindingsSettings", keybindingsSettings);
        data.put("AudioSettings", audioSettings);
        return data;
    }


    public void createDefaultSettings(){
        createNecessaryDirectory();

        saveSettings(getDefaultSettings());
    }

    private HashMap<String, HashMap<String, String>> getDefaultSettings() {
        HashMap<String, HashMap<String, String>> settingsData = new HashMap<>();
        HashMap<String, String> generalSettingsData = new HashMap<>();
        HashMap<String, String> videoSettingsData = new HashMap<>();
        HashMap<String, String> interfaceSettingsData = new HashMap<String, String>();
        HashMap<String, String> keybindingsSettingsData = new HashMap<String, String>();
        HashMap<String, String> audioSettingsData =new HashMap<String, String>();
        generalSettingsData.put("AutoLoot", "false");
        generalSettingsData.put("AutoSave", "true");
        generalSettingsData.put("ScrollCombatText", "false");

        videoSettingsData.put("ResolutionWidth", VideoSettings.Resolution.HD.getWidth()+"");
        videoSettingsData.put("ResolutionHeight", VideoSettings.Resolution.HD.getHeight()+"");
        videoSettingsData.put("FullScreen", "false");

        interfaceSettingsData.put("inf", "false");

        keybindingsSettingsData.put("bind", "false");

        audioSettingsData.put("audio", "false");

        settingsData.put("GeneralSettings", generalSettingsData);
        settingsData.put("VideoSettings", videoSettingsData);
        settingsData.put("InterfaceSettings", interfaceSettingsData);
        settingsData.put("KeybindingsSettings", keybindingsSettingsData);
        settingsData.put("AudioSettings", audioSettingsData);
        return settingsData;
    }

    private void createNecessaryDirectory(){
        FileHandle mainDir = Gdx.files.absolute("RPGGame");
        //FileHandle settings = new FileHandle("RPGGame/settings.json");
        FileHandle settingsFile = Gdx.files.absolute("RPGGame/settings.json");//new FileHandle(Gdx.files.getExternalStoragePath()+"/RPGGame/settings.json");
        FileHandle keyBindingsFile = Gdx.files.absolute("RPGGame/keyBindings.json");//new FileHandle(Gdx.files.getExternalStoragePath()+"/RPGGame/keyBindings.json");
        FileHandle playersDir = Gdx.files.absolute("RPGGame/players");//new FileHandle(Gdx.files.getExternalStoragePath()+"/RPGGame/players");
        if(!mainDir.exists())
            mainDir.mkdirs();
        if(!settingsFile.exists()) {
            settingsFile.write(false);
        }
        if(!keyBindingsFile.exists())
            keyBindingsFile.write(false);
        if(!playersDir.exists())
            playersDir.mkdirs();
    }
}
