package com.embercrest.game.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.game.hud.inwentory.Inventory;
import com.embercrest.game.game.hud.party_manager.PartyManager;

public class HudMenuButtons extends Table {
    private ImageButton showInventoryBtn, showPartyManagerBtn, saveBtn, settingsBtn, exitGameBtn;
    private final Inventory inventory;
    private final PartyManager partyManager;
    public HudMenuButtons(Inventory inventory, PartyManager partyManager) {
        super();
        this.inventory = inventory;
        this.partyManager = partyManager;
        setDebug(true);
        setUpButtons();
    }

    private void setUpButtons() {
        showInventoryBtn = new ImageButton(Assets.get().getSkin());
        showPartyManagerBtn = new ImageButton(Assets.get().getSkin());
        saveBtn = new ImageButton(Assets.get().getSkin());
        settingsBtn = new ImageButton(Assets.get().getSkin());
        exitGameBtn = new ImageButton(Assets.get().getSkin());

        showInventoryBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inventory.setVisible(!inventory.isVisible());
            }
        });

        showPartyManagerBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                partyManager.setVisible(!partyManager.isVisible());
            }
        });

        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                EmberCrest.get().settingsWindow.show();
            }
        });

        exitGameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        add(showInventoryBtn);
        add(showPartyManagerBtn);
        add(saveBtn);
        add(settingsBtn);
        add(exitGameBtn);
    }
}

