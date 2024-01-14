package com.embercrest.game.game_tools;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Save {
    private final FileHandle saveFileHandle;
    private HashMap<String, HashMap<String, String>> saveData;
    private final Json json = new Json(JsonWriter.OutputType.json);

    private HashMap<String, String> inventoryData;
    private HashMap<String, String> playerData;
    private HashMap<String, String> partyData;

    private HashMap<String, String> party0Equipment;
    private HashMap<String, String> party0ETalents;
    private HashMap<String, String> party0Status;
    private HashMap<String, String> party0Buffs;
    private HashMap<String, String> party0ActionBar;
    private HashMap<String, String> party0Looks;

    private HashMap<String, String> party1Equipment;
    private HashMap<String, String> party1ETalents;
    private HashMap<String, String> party1Status;
    private HashMap<String, String> party1Buffs;
    private HashMap<String, String> party1ActionBar;
    private HashMap<String, String> party1Looks;

    private HashMap<String, String> party2Equipment;
    private HashMap<String, String> party2ETalents;
    private HashMap<String, String> party2Status;
    private HashMap<String, String> party2Buffs;
    private HashMap<String, String> party2ActionBar;
    private HashMap<String, String> party2Looks;

    private HashMap<String, String> party3Equipment;
    private HashMap<String, String> party3ETalents;
    private HashMap<String, String> party3Status;
    private HashMap<String, String> party3Buffs;
    private HashMap<String, String> party3ActionBar;
    private HashMap<String, String> party3Looks;

    private HashMap<String, String> party4Equipment;
    private HashMap<String, String> party4ETalents;
    private HashMap<String, String> party4Status;
    private HashMap<String, String> party4Buffs;
    private HashMap<String, String> party4ActionBar;
    private HashMap<String, String> party4Looks;



    public Save(FileHandle saveFileHandle) {
        this.saveFileHandle = saveFileHandle;
        safeReadDataFromFile();
    }
    private void safeReadDataFromFile() {
        try {
            readDataFromFile();
            checkValidData();
            // check if data is valid
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readDataFromFile() throws FileNotFoundException {
        if(!saveFileHandle.exists()) throw new FileNotFoundException();

        String rawFileData = saveFileHandle.readString();

        HashMap<String, JsonValue> unConvertedData = json.fromJson(HashMap.class , rawFileData);

        inventoryData= json.readValue(HashMap.class, String.class, unConvertedData.get("Inventory"));
        playerData = json.readValue(HashMap.class, String.class, unConvertedData.get("PlayerData"));

        partyData= json.readValue(HashMap.class, String.class, unConvertedData.get("Party"));

        party0Equipment= json.readValue(HashMap.class, String.class, unConvertedData.get("Party0Equipment"));
        party0ETalents= json.readValue(HashMap.class, String.class, unConvertedData.get("Party0Talents"));
        party0Status= json.readValue(HashMap.class, String.class, unConvertedData.get("Party0Status")); //level, xp, hp...
        party0Buffs= json.readValue(HashMap.class, String.class, unConvertedData.get("Party0Buffs"));
        party0ActionBar= json.readValue(HashMap.class, String.class, unConvertedData.get("Party0ActionBar"));
        party0Looks = json.readValue(HashMap.class, String.class, unConvertedData.get("party0Looks"));

        party1Equipment= json.readValue(HashMap.class, String.class, unConvertedData.get("Party1Equipment"));
        party1ETalents= json.readValue(HashMap.class, String.class, unConvertedData.get("Party1Talents"));
        party1Status= json.readValue(HashMap.class, String.class, unConvertedData.get("Party1Status")); //level, xp, hp...
        party1Buffs= json.readValue(HashMap.class, String.class, unConvertedData.get("Party1Buffs"));
        party1ActionBar= json.readValue(HashMap.class, String.class, unConvertedData.get("Party1ActionBar"));
        party1Looks = json.readValue(HashMap.class, String.class, unConvertedData.get("party1Looks"));

        party2Equipment= json.readValue(HashMap.class, String.class, unConvertedData.get("Party2Equipment"));
        party2ETalents= json.readValue(HashMap.class, String.class, unConvertedData.get("Party2Talents"));
        party2Status= json.readValue(HashMap.class, String.class, unConvertedData.get("Party2Status")); //level, xp, hp...
        party2Buffs= json.readValue(HashMap.class, String.class, unConvertedData.get("Party2Buffs"));
        party2ActionBar= json.readValue(HashMap.class, String.class, unConvertedData.get("Party2ActionBar"));
        party2Looks = json.readValue(HashMap.class, String.class, unConvertedData.get("party2Looks"));

        party3Equipment= json.readValue(HashMap.class, String.class, unConvertedData.get("Party3Equipment"));
        party3ETalents= json.readValue(HashMap.class, String.class, unConvertedData.get("Party3Talents"));
        party3Status= json.readValue(HashMap.class, String.class, unConvertedData.get("Party3Status")); //level, xp, hp...
        party3Buffs= json.readValue(HashMap.class, String.class, unConvertedData.get("Party3Buffs"));
        party3ActionBar= json.readValue(HashMap.class, String.class, unConvertedData.get("Party3ActionBar"));
        party3Looks = json.readValue(HashMap.class, String.class, unConvertedData.get("party3Looks"));

        party4Equipment= json.readValue(HashMap.class, String.class, unConvertedData.get("Party4Equipment"));
        party4ETalents= json.readValue(HashMap.class, String.class, unConvertedData.get("Party4Talents"));
        party4Status= json.readValue(HashMap.class, String.class, unConvertedData.get("Party4Status")); //level, xp, hp...
        party4Buffs= json.readValue(HashMap.class, String.class, unConvertedData.get("Party4Buffs"));
        party4ActionBar= json.readValue(HashMap.class, String.class, unConvertedData.get("Party4ActionBar"));
        party4Looks = json.readValue(HashMap.class, String.class, unConvertedData.get("party4Looks"));


        saveData = new HashMap<>();
        saveData.put("Inventory", inventoryData);

        saveData.put("Party", partyData);

        saveData.put("Party0Equipment", party0Equipment);
        saveData.put("Party0Talents", party0ETalents);
        saveData.put("Party0Status", party0Status);
        saveData.put("Party0Buffs", party0Buffs);
        saveData.put("Party0ActionBar", party0ActionBar);
        saveData.put("party0Looks", party0Looks);

        saveData.put("Party1Equipment", party1Equipment);
        saveData.put("Party1Talents", party1ETalents);
        saveData.put("Party1Status", party1Status);
        saveData.put("Party1Buffs", party1Buffs);
        saveData.put("Party1ActionBar", party1ActionBar);
        saveData.put("party1Looks", party1Looks);

        saveData.put("Party2Equipment", party2Equipment);
        saveData.put("Party2Talents", party2ETalents);
        saveData.put("Party2Status", party2Status);
        saveData.put("Party2Buffs", party2Buffs);
        saveData.put("Party2ActionBar", party2ActionBar);
        saveData.put("party2Looks", party2Looks);

        saveData.put("Party3Equipment", party3Equipment);
        saveData.put("Party3Talents", party3ETalents);
        saveData.put("Party3Status", party3Status);
        saveData.put("Party3Buffs", party3Buffs);
        saveData.put("Party3ActionBar", party3ActionBar);
        saveData.put("party3Looks", party3Looks);

        saveData.put("Party4Equipment", party4Equipment);
        saveData.put("Party4Talents", party4ETalents);
        saveData.put("Party4Status", party4Status);
        saveData.put("Party4Buffs", party4Buffs);
        saveData.put("Party4ActionBar", party4ActionBar);
        saveData.put("party4Looks", party4Looks);

        saveData.put("PlayerData", playerData);


    }

    private void checkValidData() {
        // TODO
    }

    public void save(){
        saveData.get("PlayerData").put("IsAutoSave", "0");
        String dataJsonString = json.toJson(saveData);
        saveFileHandle.writeString(dataJsonString, false);
    }

    public void AutoSave(){
        saveData.get("PlayerData").put("IsAutoSave", "1");
        String dataJsonString = json.toJson(saveData);
        saveFileHandle.writeString(dataJsonString, false);
    }


    public Save(String name, int classID, ArrayList<Integer> playerTexturePartsId){
        if(name == null || name.equals("") || playerTexturePartsId == null ) throw new IllegalArgumentException();
        //SaveFileManager.createNecessaryDirectory();
        saveFileHandle = new FileHandle(SaveFileManager.saveDir.path()+"/"+name+".sav");
        if(saveFileHandle.exists()) throw new IllegalArgumentException();
        saveData = new HashMap<>();



        inventoryData= new HashMap<>();
        playerData = new HashMap<>();

        partyData= new HashMap<>();

        party0Equipment= new HashMap<>();
        party0ETalents= new HashMap<>();
        party0Status= new HashMap<>();
        party0Buffs= new HashMap<>();
        party0ActionBar= new HashMap<>();
        party0Looks= new HashMap<>();

        party1Equipment= new HashMap<>();
        party1ETalents= new HashMap<>();
        party1Status= new HashMap<>();
        party1Buffs= new HashMap<>();
        party1ActionBar= new HashMap<>();
        party1Looks= new HashMap<>();

        party2Equipment= new HashMap<>();
        party2ETalents= new HashMap<>();
        party2Status= new HashMap<>();
        party2Buffs= new HashMap<>();
        party2ActionBar= new HashMap<>();
        party2Looks= new HashMap<>();

        party3Equipment= new HashMap<>();
        party3ETalents= new HashMap<>();
        party3Status= new HashMap<>();
        party3Buffs= new HashMap<>();
        party3ActionBar= new HashMap<>();
        party3Looks= new HashMap<>();

        party4Equipment= new HashMap<>();
        party4ETalents= new HashMap<>();
        party4Status= new HashMap<>();
        party4Buffs= new HashMap<>();
        party4ActionBar= new HashMap<>();
        party4Looks= new HashMap<>();




        playerData.put("Location", "tutorial-Island");
        playerData.put("IsAutoSave", "0");
        playerData.put("PlayTimeInSec", "0");
        playerData.put("QuestCompleted", "0");
        playerData.put("QuestPoints", "0");
        playerData.put("X", Integer.toString(32 * 10));
        playerData.put("Y", Integer.toString(32 * 10));

        playerData.put("GatheringLevel", "1");
        playerData.put("CraftingLevel", "1");
        playerData.put("ArmorCraftingLevel", "1");
        playerData.put("SlayerLevel", "1");

        partyData.put("PartySize", "1");
        inventoryData.put("Coins", "7");
        party0Status.put("Name", name);
        party0Status.put("Class", Integer.toString(classID));
        party0Status.put("Level", "1");
        party0Status.put("Exp", "0");
        party0Status.put("HP", "14");
        party0Status.put("MaxHP", "14");






        party0Looks.put("body", playerTexturePartsId.get(0).toString());
        party0Looks.put("hair", playerTexturePartsId.get(1).toString());
        party0Looks.put("hairColor", playerTexturePartsId.get(2).toString());
        party0Looks.put("cloth", playerTexturePartsId.get(3).toString());
//        party0Looks.put("eyeColor", playerTexturePartsId.get(4).toString());
//        party0Looks.put("legs", playerTexturePartsId.get(5).toString());
//        party0Looks.put("boots", playerTexturePartsId.get(6).toString());
        party0Looks.put("isFemale", playerTexturePartsId.get(4).toString());

        saveData.put("Inventory", inventoryData);

        saveData.put("Party", partyData);

        saveData.put("Party0Equipment", party0Equipment);
        saveData.put("Party0Talents", party0ETalents);
        saveData.put("Party0Status", party0Status);
        saveData.put("Party0Buffs", party0Buffs);
        saveData.put("Party0ActionBar", party0ActionBar);
        saveData.put("party0Looks", party0Looks);

        saveData.put("Party1Equipment", party1Equipment);
        saveData.put("Party1Talents", party1ETalents);
        saveData.put("Party1Status", party1Status);
        saveData.put("Party1Buffs", party1Buffs);
        saveData.put("Party1ActionBar", party1ActionBar);
        saveData.put("party1Looks", party1Looks);

        saveData.put("Party2Equipment", party2Equipment);
        saveData.put("Party2Talents", party2ETalents);
        saveData.put("Party2Status", party2Status);
        saveData.put("Party2Buffs", party2Buffs);
        saveData.put("Party2ActionBar", party2ActionBar);
        saveData.put("party2Looks", party2Looks);

        saveData.put("Party3Equipment", party3Equipment);
        saveData.put("Party3Talents", party3ETalents);
        saveData.put("Party3Status", party3Status);
        saveData.put("Party3Buffs", party3Buffs);
        saveData.put("Party3ActionBar", party3ActionBar);
        saveData.put("party3Looks", party3Looks);

        saveData.put("Party4Equipment", party4Equipment);
        saveData.put("Party4Talents", party4ETalents);
        saveData.put("Party4Status", party4Status);
        saveData.put("Party4Buffs", party4Buffs);
        saveData.put("Party4ActionBar", party4ActionBar);
        saveData.put("party4Looks", party4Looks);

        saveData.put("PlayerData", playerData);
        save();
    }

    public HashMap<String, String> getPlayerData(){
        return playerData;
    }

    public HashMap<String, String> getPlayer0Looks(){
        return party0Looks;
    }

    public HashMap<String, String> getParty0Status(){
        return party0Status;
    }

    public HashMap<String, String> getInventoryData() {
        return inventoryData;
    }

    public HashMap<String, String> getPartyData() {
        return partyData;
    }

    public HashMap<String, String> getParty0Equipment() {
        return party0Equipment;
    }

    public HashMap<String, String> getParty0ETalents() {
        return party0ETalents;
    }

    public HashMap<String, String> getParty0Buffs() {
        return party0Buffs;
    }

    public HashMap<String, String> getParty0ActionBar() {
        return party0ActionBar;
    }

    public HashMap<String, String> getParty0Looks() {
        return party0Looks;
    }

    public HashMap<String, String> getParty1Equipment() {
        return party1Equipment;
    }

    public HashMap<String, String> getParty1ETalents() {
        return party1ETalents;
    }

    public HashMap<String, String> getParty1Status() {
        return party1Status;
    }

    public HashMap<String, String> getParty1Buffs() {
        return party1Buffs;
    }

    public HashMap<String, String> getParty1ActionBar() {
        return party1ActionBar;
    }

    public HashMap<String, String> getParty1Looks() {
        return party1Looks;
    }

    public HashMap<String, String> getParty2Equipment() {
        return party2Equipment;
    }

    public HashMap<String, String> getParty2ETalents() {
        return party2ETalents;
    }

    public HashMap<String, String> getParty2Status() {
        return party2Status;
    }

    public HashMap<String, String> getParty2Buffs() {
        return party2Buffs;
    }

    public HashMap<String, String> getParty2ActionBar() {
        return party2ActionBar;
    }

    public HashMap<String, String> getParty2Looks() {
        return party2Looks;
    }

    public HashMap<String, String> getParty3Equipment() {
        return party3Equipment;
    }

    public HashMap<String, String> getParty3ETalents() {
        return party3ETalents;
    }

    public HashMap<String, String> getParty3Status() {
        return party3Status;
    }

    public HashMap<String, String> getParty3Buffs() {
        return party3Buffs;
    }

    public HashMap<String, String> getParty3ActionBar() {
        return party3ActionBar;
    }

    public HashMap<String, String> getParty3Looks() {
        return party3Looks;
    }

    public HashMap<String, String> getParty4Equipment() {
        return party4Equipment;
    }

    public HashMap<String, String> getParty4ETalents() {
        return party4ETalents;
    }

    public HashMap<String, String> getParty4Status() {
        return party4Status;
    }

    public HashMap<String, String> getParty4Buffs() {
        return party4Buffs;
    }

    public HashMap<String, String> getParty4ActionBar() {
        return party4ActionBar;
    }

    public HashMap<String, String> getParty4Looks() {
        return party4Looks;
    }
}
