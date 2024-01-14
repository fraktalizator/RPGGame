package com.embercrest.game.game.hud.party_manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.componenets.CharacterClassComponent;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.ashley.componenets.EquipmentComponent;
import com.embercrest.game.game.abilities_Sellbook.SpellBook;
import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.game.equipment.EquipmentManager;
import com.embercrest.game.game.talents.TalentsTree;

import java.util.ArrayList;

public class PartyManager extends Window {

    private final TextButton closeButton = new TextButton("Manage Party", Assets.get().getSkin());

    private TalentsTree talentsTree;
    private SpellBook spellbook;
    private EquipmentManager equipmentManager;
    private final Table equipmentAndStats = new Table();

    private final Table manageButtons = new Table();
    private final PlayerButtons playersButtons;

    //private final Texture bgTexture;
    private int currentPage=0;
    private PartyCharacter selectedPartyCharacter;

    public PartyManager(ArrayList<PartyCharacter> party) {
        super("", Assets.get().getSkin());

        selectedPartyCharacter = party.get(0);

        talentsTree = selectedPartyCharacter.getComponent(CharacterClassComponent.class).getCharacterClass().getTalentsTree();
        spellbook = selectedPartyCharacter.getComponent(CharacterClassComponent.class).getCharacterClass().getSpellbook();
        equipmentManager = selectedPartyCharacter.getComponent(EquipmentComponent.class).getEquipmentManager();
        Table statsTable = selectedPartyCharacter.getComponent(CombatComponent.class).getStatsTable();

        equipmentAndStats.add(equipmentManager).expand();
        equipmentAndStats.add(statsTable).expand();

        setVisible(true);
        setTouchable(Touchable.enabled);
        playersButtons = new PlayerButtons(party);
        setMovable(true);
        setDebug(false);
        setUpButtons();

        playersButtons.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                equipmentAndStats.clear();
                selectedPartyCharacter = OnePlayerButton.getSelected().getCharacter();

                talentsTree = selectedPartyCharacter.getComponent(CharacterClassComponent.class).getCharacterClass().getTalentsTree();
                spellbook = selectedPartyCharacter.getComponent(CharacterClassComponent.class).getCharacterClass().getSpellbook();
                equipmentManager = selectedPartyCharacter.getComponent(EquipmentComponent.class).getEquipmentManager();
                Table statsTable = selectedPartyCharacter.getComponent(CombatComponent.class).getStatsTable();

                equipmentAndStats.add(equipmentManager).expand();
                equipmentAndStats.add(statsTable).expand();

                setPartyOption(currentPage);


            }
        });

        addListener(new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                return true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

    }

    private void setUpButtons() {
        TextButton talentsButton = new TextButton("Talents", Assets.get().getSkin());
        TextButton spellBookButton = new TextButton("Spell Book", Assets.get().getSkin());
        TextButton equipmentButton = new TextButton("Equipment", Assets.get().getSkin());
        manageButtons.add(talentsButton).padRight(getWidth()/4).padLeft(getWidth()/4);
        manageButtons.add(spellBookButton).padRight(getWidth()/4).padLeft(getWidth()/4);
        manageButtons.add(equipmentButton).padRight(getWidth()/4).padLeft(getWidth()/4);
        talentsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentPage = 0;
                setPartyOption(currentPage);
            }
        });
        spellBookButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentPage =1;
                setPartyOption(currentPage);
            }
        });
        equipmentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentPage = 2;
                setPartyOption(currentPage);
            }
        });
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
    }

    public void setPartyOption(int i){
        switch (i){
            case 0:
                clearChildren();
                add(closeButton).center();
                add(manageButtons).right().top().width(getWidth()*95/136).height(getHeight()*14/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                row();
                add(playersButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*15/284).padBottom(getHeight()*18/178);
                add(talentsTree).expand().right().top().width(getWidth()*95/136).height(getHeight()*86/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 0;
                break;
            case 1:
                clearChildren();
                add(closeButton).left();
                add(manageButtons).right().top().width(getWidth()*95/136).height(getHeight()*14/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                row();
                add(playersButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*15/284).padBottom(getHeight()*18/178);
                add(spellbook).expand().right().top().width(getWidth()*95/136).height(getHeight()*86/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                //getStage().setScrollFocus(spellbook);
                currentPage = 1;
                break;
            case 2:
                clearChildren();
                add(closeButton).left();
                add(manageButtons).right().top().width(getWidth()*95/136).height(getHeight()*14/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                row();
                add(playersButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*15/284).padBottom(getHeight()*18/178);
                add(equipmentAndStats).expand().right().top().width(getWidth()*95/136).height(getHeight()*86/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
//                add(playerButtons).expand().left().top().width(getWidth()*5/32).height(getHeight()*10/13).padLeft(getWidth()*10/284).padTop(getHeight()*12/178);
//                add(equipmentManager).expand().right().top().width(getWidth()*100/136).height(getHeight()*100/128).padRight(getWidth()*10/284).padTop(getHeight()*10/178);
                currentPage = 2;
                break;
        }
    }

//    public void resize(int width, int height) {
//        width = Math.max(width, 1280);
//        height = (int) (width/(1280/720));
//
//        setSize(width*2/3f, height*2/3f);
//        setPosition(width/2f-getWidth()/2f, height/2f-getHeight()/2f);
//        setBounds(getX(), getY(), getWidth(), getHeight());
//        setPartyOption(currentPage);
//    }

    public void resize() {
        Vector2 resolution = EmberCrest.get().settingsWindow.videoSettings.resolution.getSize();
        float width = resolution.x*0.8f;
        float height =  resolution.y*0.8f;

        setSize(width, height);
        setPosition(Gdx.graphics.getWidth() /2f-getWidth()/2f, Gdx.graphics.getHeight()/2f-getHeight()/2f);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setPartyOption(currentPage);
    }

    public PartyCharacter getSelectedCharacter() {
        return selectedPartyCharacter;
    }

    public void setSelectedCharacter(PartyCharacter selectedPartyCharacter) {
        this.selectedPartyCharacter = selectedPartyCharacter;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(visible) setPartyOption(currentPage);
    }
}