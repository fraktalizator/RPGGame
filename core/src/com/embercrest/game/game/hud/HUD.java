package com.embercrest.game.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.drawing_tools.WarningText;
import com.embercrest.game.game.Items.Item;
import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.game.hud.inwentory.Inventory;
import com.embercrest.game.game.hud.minimap.Minimap;
import com.embercrest.game.game.hud.party_frame.PartyFrame;
import com.embercrest.game.game.hud.party_manager.PartyManager;
import com.embercrest.game.game.maps.Xentos;

import java.util.ArrayList;
import java.util.Arrays;

public class HUD {
    private final Table hudTable;
    public final HUDStage stage;
    public final OrthographicCamera camera;

    private Inventory inventory;
    private PartyFrame partyFrame;
    private PartyManager partyManager;
    private Minimap minimap;

    private HudMenuButtons hudMenuButtons;
    private final WarningText warningText = new WarningText(null, 4);


    public HUD(SpriteBatch spriteBatch) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        final ScreenViewport viewport = new ScreenViewport(camera);
        stage = new HUDStage(viewport, spriteBatch);

        hudTable = new Table();
        hudTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudTable.setFillParent(true);
        hudTable.setDebug(false);
    }

    public void init() {
        inventory = new Inventory();
        inventory.setPosition((int)(Gdx.graphics.getWidth() - inventory.getWidth()*1.5f), 150);

        hudTable.add(partyFrame).left().top().padTop(100).padLeft(50);

        minimap.setSize(150, 150);
        hudTable.add(minimap).expand().width(minimap.getWidth()).height(minimap.getHeight()).top().right().padTop(50).padRight(50);
        hudTable.row();
        hudTable.add(new Actor());
        hudMenuButtons = new HudMenuButtons(inventory, partyManager);
        hudTable.add(hudMenuButtons).right().top().padBottom(50).padRight(50);

        stage.addActor(partyManager);
        stage.addActor(hudTable);
        stage.addActor(inventory);
        stage.addActor(warningText);
        stage.addActor(EmberCrest.get().settingsWindow);
    }


    public void resize(int width, int height) {
        //viewport.update(width, height);
        camera.setToOrtho(false, width, height);
        stage.getViewport().update(width, height);
        hudTable.setSize(width, height);
        camera.update();
        partyFrame.resize(width, height);
        partyManager.resize();
    }

    public void setPartyFrameAndManager(ArrayList<PartyCharacter> party){
        partyFrame = new PartyFrame(party);
        partyManager = new PartyManager(party);
    }

    public void setMinimap(Xentos xentos, OrthographicCamera gameScreenCamera){
        minimap = new Minimap(xentos, gameScreenCamera);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void AddItemToInventory(Item item){
        if(!inventory.addItem(item)) warningText.popMessage("Inventory Is Full");
    }

    public void dispose(){
        stage.dispose();
    }
}
