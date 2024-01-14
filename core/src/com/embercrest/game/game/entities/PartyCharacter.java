package com.embercrest.game.game.entities;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.ashley.actions.Action;
import com.embercrest.game.ashley.componenets.ActionBarComponent;
import com.embercrest.game.ashley.componenets.CharacterClassComponent;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.ashley.componenets.EquipmentComponent;
import com.embercrest.game.ashley.componenets.InteractWindowComponent;
import com.embercrest.game.ashley.componenets.TouchHandlingComponent;
import com.embercrest.game.ashley.entities.InteractAbleEntity;
import com.embercrest.game.ashley.systems.AshleyScene2dConector;
import com.embercrest.game.ashley.systems.MovementSystem;
import com.embercrest.game.game.CharacterClass;
import com.embercrest.game.game.Items.Item;
import com.embercrest.game.game.equipment.EquipmentManager;
import com.embercrest.game.game.hud.HUDStage;
import com.embercrest.game.game.hud.actionbar.ActionBar;
import com.embercrest.game.game.hud.interact_window.InteractButtons;
import com.embercrest.game.game.hud.interact_window.InteractWindow;

public class PartyCharacter extends InteractAbleEntity {

    public PartyCharacter(Vector2 position, Texture texture, CharacterClass.PlayerClass klasa) throws Exception {
        super(position, texture);

        getComponent(TouchHandlingComponent.class).setLeftClickAction(leftClickAction);

        add(new ActionBarComponent(new ActionBar()));
        add(new CombatComponent(100, 70, 100, 32));
        add(new CharacterClassComponent(klasa.getNew(this)));
        add(new EquipmentComponent(new EquipmentManager(this)));
    }

    @Override
    public void setUpInteractWindow(){
        InteractButtons buttons = new InteractButtons();
        InteractWindow interactWindow = new InteractWindow("", Item.ItemRarity.Common);

        interactWindow.add(buttons.getMoveButton(this));
        interactWindow.row();
        interactWindow.add(buttons.getAbilitiesButton(this));
        interactWindow.row();
        interactWindow.add(buttons.getCloseButton(this));
        interactWindow.setHeight(interactWindow.getHeight()+80);
        interactWindowComponent = new InteractWindowComponent(interactWindow);
        add(interactWindowComponent);
    }

    public void move(){
        getEngine().getSystem(MovementSystem.class).SelectEntity(this);
    }

    public void showActionBar() {
        AshleyScene2dConector ashleyScene2dConector = getEngine().getSystem(AshleyScene2dConector.class);
        HUDStage hudStage = ashleyScene2dConector.getHudStage();
        ActionBar actionBar = getComponent(ActionBarComponent.class).getActionBar();
        Vector2 actionBarPos = ashleyScene2dConector.ashleyToStageCords(this);
        actionBar.setPosition(actionBarPos.x, actionBarPos.y);
        hudStage.displayActionBar(actionBar);
    }


    Action leftClickAction = (ent) ->{
        move();
        return true;
    };

}
