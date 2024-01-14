package com.embercrest.game.game_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.SerializationException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SaveManager {
    private final int maxAutoSavesQuantity = 3;
    public ArrayList<File> currentSaves;
    public Json json = new Json(JsonWriter.OutputType.json);
    public FileHandle save;
    public String name;

    public HashMap<String, String>getSimpleSafeData(File file){
        //TODO AAAAAAAAAAAAAAAAAAAA
        return getSimpleData(file);
    }
    public HashMap<String, String>getSimpleData(File file){
        String rawFileData = new FileHandle(file).readString();
        try {
            HashMap<String, JsonValue> unConvertedData = json.fromJson(HashMap.class , rawFileData);
            return json.readValue(HashMap.class, String.class, unConvertedData.get("party0Looks"));
        }catch (SerializationException e){
            Gdx.app.log("Damaged save file(3)", file.getName());
            throw new NullPointerException();
        }
    }

    public HashMap<String, HashMap<String, String>> getDataFromFIle(File file){
        createNecessaryDirectory();
        try {
            return getUnsafeDataFromFIle(file);
        }catch (SerializationException e){
            e.printStackTrace();
            return null;
        }

        // to trzeba zmienic
    }

    public boolean saveGameStatus(HashMap<String, HashMap<String, String>> data, String playerName){
        createNecessaryDirectory();
        save = new FileHandle("RPGGame/players/"+playerName+".sav");

        if(playerName.equals("")){
            return false;
        }
        else {
            save.write(false);
        }
        name = playerName;

        String dataJsonString = json.toJson(data);
        save.writeString(dataJsonString, false);
        return true;
    }

    @SuppressWarnings("NewApi")
    public boolean autoSaveGameStatus(HashMap<String, HashMap<String, String>> data, String playerName){
        createNecessaryDirectory();
        ArrayList<File> playersAutoSaves = getAutoSavesList(playerName);

        // delete oldest autoSave if there is too much of them.
        if (playersAutoSaves.size() > maxAutoSavesQuantity-1){
            File oldestAutoSave = playersAutoSaves.get(0);
            for (File file : playersAutoSaves){
                if( file.lastModified() < oldestAutoSave.lastModified()) oldestAutoSave = file;
            }
            oldestAutoSave.delete();
        }
        save = new FileHandle("RPGGame/players/"+playerName+ LocalDateTime.now()+".Asav");

        if(playerName.equals("")){
            return false;
        }
        else {
            save.write(false);
        }
        name = playerName;
        data.get("PlayerData").put("IsAutoSave", "1");
        String dataJsonString = json.toJson(data);
        save.writeString(dataJsonString, false);
        return true;
    }


    public boolean createNewSave(String playerName, int playerClassId, ArrayList<Integer> playerTexturePartsId){
        createNecessaryDirectory();
        if(playerClassId == 0){
            return false;
        }
        HashMap<String, HashMap<String, String>> data = getDefaultData(playerName, playerClassId, playerTexturePartsId);
        save = new FileHandle("RPGGame/players/"+playerName+".sav");
        if(save.exists()){
            return false;
        }
        else if(playerName.equals("")){
            return false;
        }
        else {
            save.write(true);
        }
        name = playerName;

        String dataJsonString = json.toJson(data);
        save.writeString(dataJsonString, false);
        return true;
    }

    public ArrayList<File> getAutoSavesList(String playerName){
        ArrayList<File> playersAutoSaves = new ArrayList<>(10);
        ArrayList<File> saves = getSavesList();
        for(File file:saves){
            if(file.getName().length() > playerName.length()+4) playersAutoSaves.add(file);
        }
        return playersAutoSaves;
    }

    public ArrayList<File> getSavesList(){
        currentSaves = new ArrayList<>();
        File saveDir = new File("./RPGGame/players");
        for(File saveFile: Objects.requireNonNull(saveDir.listFiles())) {
            if(getExtension(saveFile.getName()).equals("sav")){
                currentSaves.add(saveFile);
            }
        }
        return currentSaves;
    }

    private HashMap<String, HashMap<String, String>> getUnsafeDataFromFIle(File file){
        if(!new FileHandle(file).exists()) return null;
        String rawFileData = new FileHandle(file).readString();
        HashMap<String, JsonValue> unConvertedData = json.fromJson(HashMap.class , rawFileData);

        HashMap<String, String> inventoryData= json.readValue(HashMap.class, String.class, unConvertedData.get("Inventory"));
        HashMap<String, String> playerData = json.readValue(HashMap.class, String.class, unConvertedData.get("PlayerData"));
        HashMap<String, String> equipmentData = json.readValue(HashMap.class, String.class, unConvertedData.get("Equipment"));
        HashMap<String, String> talentData = json.readValue(HashMap.class, String.class, unConvertedData.get("Talents"));
        HashMap<String, String> talentTreesData = json.readValue(HashMap.class, String.class, unConvertedData.get("TalentTrees"));
        HashMap<String, String> actionBarData = json.readValue(HashMap.class, String.class, unConvertedData.get("ActionBar"));
        HashMap<String, String> buffBarData = json.readValue(HashMap.class, String.class, unConvertedData.get("BuffBar"));
        HashMap<String, String> deBuffBarData = json.readValue(HashMap.class, String.class, unConvertedData.get("DeBuffBar"));
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        data.put("PlayerData", playerData);
        data.put("Inventory", inventoryData);
        data.put("Equipment", equipmentData);
        data.put("ActionBar", actionBarData);
        data.put("BuffBar", buffBarData);
        data.put("DeBuffBar", deBuffBarData);
        data.put("Talents", talentData);
        data.put("TalentTrees", talentTreesData);

        return data;
    }

    private HashMap<String, HashMap<String, String>> getDefaultData(String playerName, int playerClassId, ArrayList<Integer> playerTexturePartsId) {
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        HashMap<String, String> inventoryData = new HashMap<>();
        HashMap<String, String> playerData = new HashMap<>();
        HashMap<String, String> equipmentData = new HashMap<>();
        HashMap<String, String> talentData = new HashMap<>();
        HashMap<String, String> talentTreesData =new HashMap<>();
        HashMap<String, String> actionBarData = new HashMap<>();
        HashMap<String, String> buffBarData = new HashMap<>();
        HashMap<String, String> deBuffBarData = new HashMap<>();
        playerData.put("Name", playerName);
        playerData.put("Location", "tutorial-Island");
        playerData.put("IsAutoSave", "0");
        playerData.put("AliveAllies", "1");
        playerData.put("PartySize", "1");
        playerData.put("PlayTimeInSec", "0");
        playerData.put("QuestCompleted", "0");
        playerData.put("QuestPoints", "0");
        playerData.put("X", Integer.toString(32 * 10));
        playerData.put("Y", Integer.toString(32 * 10));
        playerData.put("Class", Integer.toString(playerClassId));
        playerData.put("Coins", "7");
        playerData.put("Level", "1");
        playerData.put("Exp", "0");
        playerData.put("GatheringLevel", "1");
        playerData.put("CraftingLevel", "1");
        playerData.put("ArmorCraftingLevel", "1");
        playerData.put("SlayerLevel", "1");
        playerData.put("HP", "14");
        playerData.put("MaxHP", "14");

        playerData.put("body", playerTexturePartsId.get(0).toString());
        playerData.put("hair", playerTexturePartsId.get(1).toString());
        playerData.put("hairColor", playerTexturePartsId.get(2).toString());
        playerData.put("cloth", playerTexturePartsId.get(3).toString());
//        playerData.put("eyeColor", playerTexturePartsId.get(4).toString());
//        playerData.put("legs", playerTexturePartsId.get(5).toString());
//        playerData.put("boots", playerTexturePartsId.get(6).toString());
        playerData.put("isFemale", playerTexturePartsId.get(4).toString());

        data.put("PlayerData", playerData);
        data.put("Inventory", inventoryData);
        data.put("Equipment", equipmentData);
        data.put("ActionBar", actionBarData);
        data.put("BuffBar", buffBarData);
        data.put("DeBuffBar", deBuffBarData);
        data.put("Talents", talentData);
        data.put("TalentTrees", talentTreesData);
        return data;
    }

    private void createNecessaryDirectory(){
        FileHandle dir = new FileHandle("RPGGame");
        FileHandle settings = new FileHandle("RPGGame/settings.json");
        FileHandle keyBindings = new FileHandle("RPGGame/keyBindings.json");
        FileHandle players = new FileHandle("RPGGame/players");
        if(!dir.exists())
            dir.mkdirs();
        if(!settings.exists())
            settings.write(false);
        if(!keyBindings.exists())
            keyBindings.write(false);
        if(!players.exists())
            players.mkdirs();
    }

    private String getExtension (String name) {
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex == -1) return "";
        return name.substring(dotIndex + 1);
    }

    private boolean checkExistence(String fileName){
        return new FileHandle(fileName).exists();
    }

//    public boolean clone(SaveButton selectedSave) {
//        HashMap<String, HashMap<String, String>> data = getDataFromFIle(selectedSave.getSaveFile());
//        if( data == null) return false;
//        //System.out.println(selectedSave.saveFile.getName().substring(0, selectedSave.saveFile.getName().length()-4));
//        String cloneFileName = selectedSave.getSaveFile().getName().substring(0, selectedSave.getSaveFile().getName().length()-4);
//        for(int i=0;i<50;i++) {
//            if(!checkExistence(cloneFileName+i)) saveGameStatus(data, cloneFileName+"_clone_"+i);
//            return true;
//        }
//        return false;
//    }

//    public boolean delete(SaveButton selectedSave) {
//        return selectedSave.getSaveFile().delete();
//    }
}
