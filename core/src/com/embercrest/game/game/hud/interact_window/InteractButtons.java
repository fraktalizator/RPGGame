package com.embercrest.game.game.hud.interact_window;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.entities.InteractAbleEntity;
import com.embercrest.game.game.Items.Item;
import com.embercrest.game.game.entities.PartyCharacter;

public class InteractButtons {
    private final Skin skin = Assets.get().getSkin();

    public TextButton getMoveButton(PartyCharacter partyCharacter){
        TextButton move = new TextButton("move", skin);
        move.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                partyCharacter.move();
                //partyCharacter.getComponent(InteractWindowComponent.class).getInteractWindow().remove();
            }
        });
        return move;
    }

    public TextButton getCloseButton(InteractAbleEntity interactAbleEntity){
        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //interactAbleEntity.getComponent(InteractWindowComponent.class).getInteractWindow().remove();
            }
        });
        return close;
    }

    public TextButton getAbilitiesButton(PartyCharacter partyCharacter){
        TextButton move = new TextButton("abilities", skin);
        move.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                partyCharacter.showActionBar();
               // partyCharacter.getComponent(InteractWindowComponent.class).getInteractWindow().remove();
            }
        });
        return move;
    }

    public TextButton getDropButton(final Item item){
        TextButton drop = new TextButton("drop", skin);
        drop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                item.drop();
                item.getInteractWindow().setVisible(false);
            }
        });
        return drop;
    }

    public TextButton getCloseButton(Item item){
        TextButton close = new TextButton("close", skin);
        close.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                item.getInteractWindow().setVisible(false);
            }
        });
        return close;
    }

    public TextButton getSelectButton(Item item){
        TextButton select = new TextButton("use", skin);
        select.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                item.select();
            }
        });
        return select;
    }
}
