package com.embercrest.game.game_tools;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.File;
import java.util.ArrayList;

public class SaveFileManager {
    private final int maxAutoSavesQuantity = 3;
    public static final FileHandle saveDir = new FileHandle("./RPGGame/players");

    public Json json = new Json(JsonWriter.OutputType.json);

    public ArrayList<Save> getSavesList(){
        ArrayList<Save> saves = new ArrayList<>();
        FileHandle saveDir = new FileHandle("./RPGGame/players");
        if(!saveDir.exists()) {
            createNecessaryDirectory();
            return new ArrayList<>();
        }
        File[] saveFiles = saveDir.file().listFiles();
        if(saveFiles == null || saveFiles.length == 0) return new ArrayList<>();

        for(File saveFile: saveFiles) {
            if(getExtension(saveFile.getName()).equals("sav")){
                saves.add(new Save(new FileHandle(saveFile)));
            }
        }
        return saves;
    }


    public static void createNecessaryDirectory(){
        FileHandle dir = new FileHandle("RPGGame");
        FileHandle settings = new FileHandle("RPGGame/settings.json");
        FileHandle keyBindings = new FileHandle("RPGGame/keyBindings.json");
        if(!dir.exists())
            dir.mkdirs();
        if(!settings.exists())
            settings.write(false);
        if(!keyBindings.exists())
            keyBindings.write(false);
        if(!saveDir.exists())
            saveDir.mkdirs();
    }


    private String getExtension (String name) {
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex == -1) return "";
        return name.substring(dotIndex + 1);
    }
}
