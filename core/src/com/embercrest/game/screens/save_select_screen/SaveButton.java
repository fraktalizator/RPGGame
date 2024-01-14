package com.embercrest.game.screens.save_select_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.AnimatedTextureActor;
import com.embercrest.game.settings.individual_settings_tables.VideoSettings;
import com.embercrest.game.game_tools.Save;
import com.embercrest.game.Assets;
import com.embercrest.game.screens.loading_screen.LoadingScreen;

import java.util.HashMap;

public class SaveButton extends Table {
    private final Texture bgTexture = Assets.PreGameTextureAssets.SaveButton.getTexture();
    private final  Texture bgTextureSelected = Assets.PreGameTextureAssets.SaveButtonSelected.getTexture();
    public boolean selected;
    private long lastClickedTime;

    //save data
    private final Save save;
    private final AnimatedTextureActor playerTexture;


    public SaveButton(Save save){
        this.save = save;

        setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));
        setTouchable(Touchable.enabled);


        playerTexture = generatePlayerTexture();
        playerTexture.freeze(true);
        setUpSaveButton();
        playerTexture.setPosFrame(2);

        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (selected){
                    if( (System.currentTimeMillis()- lastClickedTime) < 320){
                        if((hit(x, y, true) instanceof Image)) return false;
                        ((EmberCrest)Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(save));
                    }
                    lastClickedTime = System.currentTimeMillis();
                }else {
                    buttonClicked();
                }
                return true;
            }
        });

    }

    protected void buttonClicked() {
        // unselect all of the save buttons
        for(SaveButton saveButton: SaveSelectWindow.saveButtons){
            saveButton.buttonUnselect();
        }

        lastClickedTime = System.currentTimeMillis();
        selected = true;
        setBackground(new TextureRegionDrawable(new TextureRegion(bgTextureSelected)));
        playerTexture.freeze(false);
    }

    protected void buttonUnselect() {
        selected = false;
        // stop player move animation
        setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));
        playerTexture.freeze(true);
    }

    public static SaveButton getSelectedButton(){
        for(SaveButton saveButton: SaveSelectWindow.saveButtons){
            if(saveButton.selected)return saveButton;
        }
        return null;
    }


//    private AnimatedTextureActor generatePlayerTexture() {
//        HashMap<String, String> player0Looks = save.getPlayer0Looks();
//        int bodyId = Integer.parseInt(player0Looks.get("body"));
//        int hairId = Integer.parseInt(player0Looks.get("hair"));
//        int hairColorId = Integer.parseInt(player0Looks.get("hairColor"));
//        int clothId = Integer.parseInt(player0Looks.get("cloth"));
//        //int eyeColorId = Integer.parseInt(player0Looks.get("eyeColor"));
//        //int legsId = Integer.parseInt(player0Looks.get("legs"));
//        //int bootsId = Integer.parseInt(player0Looks.get("boots"));
//        boolean isFemale = Integer.parseInt(player0Looks.get("isFemale")) == 1;
//        Texture playerTexture = characterAnimTextureGenerator.GenFromPartsId(bodyId, hairId, hairColorId, clothId, -1, -1, -1, isFemale);//eyeColorId, legsId, bootsId, isFemale);
//        return new AnimatedTextureActor(playerTexture);
//    }

    private AnimatedTextureActor generatePlayerTexture() {
        HashMap<String, String> player0Looks = save.getPlayer0Looks();
        boolean isFemale = Integer.parseInt(player0Looks.get("isFemale")) == 1;
        if(isFemale){
            Assets.FemaleBodyTextureAssets femaleBodyTextureAssets = Assets.FemaleBodyTextureAssets.valueOf(player0Looks.get("body"));
            Assets.FemaleClothTextureAssets femaleClothTextureAssets = Assets.FemaleClothTextureAssets.valueOf(player0Looks.get("cloth"));
            Assets.FemaleHairTextureAssets femaleHairTextureAssets = Assets.FemaleHairTextureAssets.valueOf(player0Looks.get("hair"));
            //int eyeColorId = Integer.parseInt(player0Looks.get("eyeColor"));
            //int legsId = Integer.parseInt(player0Looks.get("legs"));
            //int bootsId = Integer.parseInt(player0Looks.get("boots"));

            Texture playerTexture = Assets.get().generateCharacterTexture(femaleBodyTextureAssets, femaleHairTextureAssets, femaleClothTextureAssets);
            return new AnimatedTextureActor(playerTexture);
        }else {
            //a sada sdasd
            // TODO
            return null;
        }

    }

    private void setUpSaveButton(){
        Skin skin = Assets.get().getSkin();
        resize();
        setBackground(new TextureRegionDrawable(new TextureRegion(bgTexture)));
        HashMap<String, String> playerData = save.getPlayerData();

        HashMap<String, String> player0Status = save.getParty0Status();

        // to display on the button
        Label goldCountLabel = new Label("Coins: " + save.getInventoryData().get("Coins"), skin);
        Label locationLabel = new Label("Location: " + playerData.get("Location"), skin);
        Label levelLabel = new Label("Lvl: " + player0Status.get("Level"), skin, "aleo_regular_small", new Color(0xdd3934ff));
        Label playerNameLabel = new Label("Name: " + player0Status.get("Name"), skin);
        Label playerHpLabel = new Label("Hp: " + player0Status.get("HP") + "/" + player0Status.get("MaxHP"), skin);
        Label playerQuestsCompletedCountLabel = new Label("Quest completed: " + playerData.get("QuestCompleted")+"/100", skin);
        //Label alliesAlliveLabel = new Label("Alive allies : " + playerData.get("AliveAllies") + "/" + playerData.get("PartySize"), skin);
        Label playTimeLabel = new Label("Play time: " + playerData.get("PlayTimeInSec"), skin);

        ProgressBar gameProgress = new ProgressBar(0, 100, 0.5f, false, skin);
        gameProgress.setValue(73f);

        Label party1Name = new Label("Xynia", skin);
        Label party2Name = new Label("Leo", skin);
        Label party3Name = new Label("Hardi", skin);
        Label party4Name = new Label("Tremor", skin);
        ProgressBar party1HP = new ProgressBar(0, 100, 0.5f, false, skin);
        ProgressBar party2HP = new ProgressBar(0, 100, 0.5f, false, skin);
        ProgressBar party3HP = new ProgressBar(0, 100, 0.5f, false, skin);
        ProgressBar party4HP = new ProgressBar(0, 100, 0.5f, false, skin);
        party1HP.setValue(30);
        party2HP.setValue(88);
        party3HP.setValue(3);
        party4HP.setValue(100);

        Texture deleteButtonUp = new Texture(Gdx.files.internal("Ui/SaveScreen/buttonsaveinfo.png"));
        Texture deleteButtonDown = new Texture(Gdx.files.internal("Ui/SaveScreen/buttonsaveinfoclick.png"));
        TextureRegionDrawable deleteButtonUpDrawable = new TextureRegionDrawable(new TextureRegion(deleteButtonUp));
        TextureRegionDrawable deleteButtonDownDrawable = new TextureRegionDrawable(new TextureRegion(deleteButtonDown));
        ImageButton deleteButton = new ImageButton(deleteButtonUpDrawable, deleteButtonDownDrawable);

        ImageButton statsButton = new ImageButton(deleteButtonUpDrawable, deleteButtonDownDrawable);

        ImageButton cloneButton = new ImageButton(deleteButtonUpDrawable, deleteButtonDownDrawable);



        //setDebug(true);

        float proportionWidth = (getWidth()/ bgTexture.getWidth());
        float proportionHeight = (getHeight()/ bgTexture.getHeight());


        Table playerAnimationTable = new Table();
        //playerAnimationTable.setDebug(true);
        playerAnimationTable.add(playerNameLabel).padTop(10);
        playerAnimationTable.row();
        playerAnimationTable.add(playerTexture).expand().center();
        playerAnimationTable.row();
        playerAnimationTable.add(playerHpLabel).padBottom(10);
        playerAnimationTable.setSize(228*proportionWidth, (228-0)*proportionHeight);

        Table playerInfoTable = new Table();
        //playerInfoTable.setDebug(true);
        playerInfoTable.add(locationLabel);
        playerInfoTable.row();
        playerInfoTable.add(goldCountLabel);
        playerInfoTable.setSize(238*proportionWidth, 107*proportionHeight);

        Table partyInfoTable = new Table();
        //partyInfoTable.setDebug(true);
        partyInfoTable.setSize(238*proportionWidth, 107*proportionHeight);
        partyInfoTable.add(party1Name).padBottom(4*proportionHeight);
        partyInfoTable.add(party1HP).width(partyInfoTable.getWidth()/1.7f).padBottom(4*proportionHeight);
        partyInfoTable.row();
        partyInfoTable.add(party2Name).padBottom(4*proportionHeight);
        partyInfoTable.add(party2HP).width(partyInfoTable.getWidth()/1.7f).padBottom(4*proportionHeight);
        partyInfoTable.row();
        partyInfoTable.add(party3Name).padBottom(4*proportionHeight);
        partyInfoTable.add(party3HP).width(partyInfoTable.getWidth()/1.7f).padBottom(4*proportionHeight);
        partyInfoTable.row();
        partyInfoTable.add(party4Name).padBottom(4*proportionHeight);
        partyInfoTable.add(party4HP).width(partyInfoTable.getWidth()/1.7f).padBottom(4*proportionHeight);
        partyInfoTable.row();


        Table playerInfoTableAndGameProgressTable = new Table();
        playerInfoTableAndGameProgressTable.add(playerInfoTable).height(playerInfoTable.getHeight()).width(playerInfoTable.getWidth());
        playerInfoTableAndGameProgressTable.row();
        playerInfoTableAndGameProgressTable.add().height(14*proportionHeight).width(14*proportionWidth);
        playerInfoTableAndGameProgressTable.row();
        playerInfoTableAndGameProgressTable.add(partyInfoTable).height(partyInfoTable.getHeight()).width(partyInfoTable.getWidth()).center();
        playerInfoTableAndGameProgressTable.setSize(playerInfoTable.getWidth(), playerInfoTable.getHeight()*2);

        Table gameProgressTable = new Table();
        //gameProgressTable.setDebug(true);
        gameProgressTable.add(gameProgress);
        gameProgressTable.row();
        gameProgressTable.add(playerQuestsCompletedCountLabel);
        gameProgressTable.row();
        gameProgressTable.add(levelLabel);
        gameProgressTable.row();
        gameProgressTable.add(playTimeLabel);
        gameProgressTable.setSize(238*proportionWidth, 107*proportionHeight);

        Table buttonsTable = new Table();
        buttonsTable.add(statsButton).pad(6);
        buttonsTable.add(cloneButton).pad(6);
        buttonsTable.add(deleteButton).pad(6);
        buttonsTable.setSize(238*proportionWidth, 107*proportionHeight);


        Table gameProgressAndButtonsTable = new Table();
        gameProgressAndButtonsTable.add(gameProgressTable).height(gameProgressTable.getHeight()).width(gameProgressTable.getWidth());
        gameProgressAndButtonsTable.row();
        gameProgressAndButtonsTable.add().height(14*proportionHeight).width(14*proportionWidth);
        gameProgressAndButtonsTable.row();
        gameProgressAndButtonsTable.add(buttonsTable).height(buttonsTable.getHeight()).width(buttonsTable.getWidth());
        gameProgressAndButtonsTable.setSize(buttonsTable.getWidth(), buttonsTable.getHeight()*2);




        // set up savebutton table
        top().left();
        add().width(getWidth()).height(25*proportionHeight).colspan(7);
        row();
        add().width(25*proportionWidth).height((25)*proportionHeight);

        add(playerAnimationTable).width(playerAnimationTable.getWidth()).height(playerAnimationTable.getHeight());

        add().width(47*proportionWidth).height((47)*proportionHeight);

        add(playerInfoTableAndGameProgressTable).width(playerInfoTableAndGameProgressTable.getWidth()).height(playerInfoTableAndGameProgressTable.getHeight());

        add().width(14*proportionWidth).height((14)*proportionHeight);

        add(gameProgressAndButtonsTable).width(gameProgressAndButtonsTable.getWidth()).height(gameProgressAndButtonsTable.getHeight());

        add().width(37*proportionWidth).height((37)*proportionHeight);


    }

    public void dispose(){
        playerTexture.dispose();
    }

    public Save getSave() {
        return save;
    }

    public void resize() {

        Vector2 resolution = EmberCrest.get().settingsWindow.videoSettings.resolution.getSize();
        float width = (resolution.x/ VideoSettings.Resolution.FullHD.getWidth()* bgTexture.getWidth());
        float height = (resolution.y/ VideoSettings.Resolution.FullHD.getHeight()* bgTexture.getHeight());

        setSize(width, height);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
