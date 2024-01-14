package com.embercrest.game.game.hud.party_frame;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.ashley.componenets.NameComponent;
import com.embercrest.game.drawing_tools.StatusBar;
import com.embercrest.game.game.hud.HUDStage;

public class PlayerFrame extends Table {
    StatusBar healthBar;
    StatusBar manaBar;
    String name;
    private final Entity entity;

    public PlayerFrame(Entity entity){
        this.entity = entity;
        CombatComponent cbp = entity.getComponent(CombatComponent.class);
        NameComponent nameComp = entity.getComponent(NameComponent.class);
        if(cbp == null) throw new IllegalArgumentException("can not create player frame out of character with no combat componenet!");
        if(nameComp == null) throw new IllegalArgumentException("can not create player frame out of character with no combat componenet!");
        name = nameComp.getName();
        //healthBar = new ProgressBar(0, cbp.getMaxHP(), 1, false, Assets.get()2.getSkin());
        healthBar = new StatusBar(cbp.getMaxHP());
        healthBar.setValue(cbp.getHP());
        healthBar.setHeight(64);
        healthBar.setWidth(128);
        //manaBar = new ProgressBar(0, cbp.getMaxResource(), 1, false, Assets.get()2.getSkin());
        manaBar = new StatusBar(cbp.getMaxResource(), new StatusBar.StatusBarStyle(cbp.getResourceType().getI()));
        manaBar.setValue(cbp.getResource());
        manaBar.setHeight(64);
        manaBar.setWidth(128);
        add(new Label(name, Assets.get().getSkin()));
        row();
        add(healthBar).pad(6);
        row();
        add(manaBar).pad(6);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(HUDStage.SelectedItem != null){
                    HUDStage.SelectedItem.useOnEntity(entity);
                    if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) )HUDStage.SelectedItem.unSelect();
                }
            }
        });
        setSize(128, 54);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        CombatComponent cbp = entity.getComponent(CombatComponent.class);
        if(cbp == null) throw new IllegalArgumentException("can not create player frame out of character with no combat componenet!");
        healthBar.setValue(cbp.getHP());
        manaBar.setValue(cbp.getResource());
    }

    public void update() {
        CombatComponent cbp = entity.getComponent(CombatComponent.class);
        if(cbp == null) throw new IllegalArgumentException("can not create player frame out of character with no combat componenet!");
        healthBar.setValue(cbp.getHP());
        manaBar.setValue(cbp.getResource());
    }

    public Entity getEntity() {
        return entity;
    }
}
