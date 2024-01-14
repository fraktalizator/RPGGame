package com.embercrest.game.ashley.entities;

import static com.embercrest.game.ashley.componenets.PositionComponent.GRIDSIZE;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.ashley.componenets.RenderComponent;
import com.embercrest.game.screens.battle_screen.BattleScreen;

public abstract class RenderAbleEntity extends Entity {
    private int moveCost = 9999;

    public RenderAbleEntity(Vector2 position, Texture texture, boolean animate) {
        super();
        if(animate) {
            add(new PositionComponent(position.x, position.y, 32, 48));
            add(new RenderComponent(texture, getZIndexByPosition(), 32, 48));
        }else {
            add(new PositionComponent(position.x, position.y, 32, 48));
            add(new RenderComponent(texture, getZIndexByPosition()));

        }
    }

    public void setZIndexByPosition() {
        getComponent(RenderComponent.class).setZindex(getZIndexByPosition());
    }

    private int getZIndexByPosition() {
        return -(int) getComponent(PositionComponent.class).getY()/GRIDSIZE;
    }

    protected Engine getEngine() {
        return ((BattleScreen)((EmberCrest) Gdx.app.getApplicationListener()).getScreen()).getEngine();
    }

    public int getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }
}
