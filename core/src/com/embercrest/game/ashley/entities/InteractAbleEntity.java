package com.embercrest.game.ashley.entities;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.ashley.actions.Action;
import com.embercrest.game.ashley.componenets.InteractWindowComponent;
import com.embercrest.game.ashley.componenets.NameComponent;
import com.embercrest.game.ashley.componenets.TouchHandlingComponent;
import com.embercrest.game.ashley.systems.AshleyScene2dConector;
import com.embercrest.game.game.Items.Item;
import com.embercrest.game.game.hud.HUDStage;
import com.embercrest.game.game.hud.interact_window.InteractButtons;
import com.embercrest.game.game.hud.interact_window.InteractWindow;

public abstract class InteractAbleEntity extends RenderAbleEntity{
    protected InteractWindowComponent interactWindowComponent;

//    public InteractAbleEntity(Vector2 position, AssetDescriptor<Texture> textureAssetDescriptor) {
//        this(position, textureAssetDescriptor, true);
//    }

    public InteractAbleEntity(Vector2 position, Texture texture) {
        this(position, texture, true);
    }

    public InteractAbleEntity(Vector2 position, Texture texture, boolean animate) {
        super(position, texture, animate);
        add(new TouchHandlingComponent(Action.pass, rightClickAction));
        setUpInteractWindow();
    }

    public void setUpInteractWindow(){
        InteractButtons buttons = new InteractButtons();
        InteractWindow interactWindow = new InteractWindow("", Item.ItemRarity.Common);

        interactWindow.add(buttons.getCloseButton(this));
        interactWindow.setHeight(interactWindow.getHeight()+21);
        interactWindowComponent = new InteractWindowComponent(interactWindow);
        add(interactWindowComponent);
    }

    public void showInteractWindow(){
        AshleyScene2dConector ashleyScene2dConector = getEngine().getSystem(AshleyScene2dConector.class);
        HUDStage hudStage = ashleyScene2dConector.getHudStage();
        InteractWindow interactWindow = interactWindowComponent.getInteractWindow();

        Vector2 windowPos = new Vector2(ashleyScene2dConector.ashleyToStageCords(this)).add(new Vector2(32, 0));
        hudStage.displayInteractWindow(interactWindow, windowPos);
    }


    public String getName() {return getComponent(NameComponent.class) != null ? getComponent(NameComponent.class).getName() : "Default";}

    Action rightClickAction = (ent) ->{
        showInteractWindow();
        return true;
    };
}
