package com.embercrest.game.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.game.hud.HUD;
import com.embercrest.game.game.hud.HUDStage;
import com.embercrest.game.game.hud.interact_window.InteractWindow;

public class AshleyScene2dConector extends EntitySystem {
    private final HUDStage hudStage;
    private final HUD hud;
    private final OrthographicCamera gameScreenCamera;

    public AshleyScene2dConector(HUDStage stage, HUD hud, OrthographicCamera camera) {
        this.hudStage = stage;
        this.hud = hud;
        this.gameScreenCamera = camera;
    }

    public Vector2 ashleyToStageCords(Entity entity){
        Vector3 camTrans = getGameScreenCamera().position;
        Vector2 stagePos = new Vector2(entity.getComponent(PositionComponent.class).getPosition());
        stagePos.x = stagePos.x - camTrans.x+ Gdx.graphics.getWidth()/2f;
        stagePos.y = stagePos.y - camTrans.y+Gdx.graphics.getHeight()/2f;
        return stagePos;
    }

    public void addActorToHudStage(Actor actor) {
        hudStage.addActor(actor);
    }
    public HUDStage getHudStage() {
        return hudStage;
    }
    public HUD getHud() {
        return hud;
    }
    public OrthographicCamera getGameScreenCamera() {
        return gameScreenCamera;
    }

}
