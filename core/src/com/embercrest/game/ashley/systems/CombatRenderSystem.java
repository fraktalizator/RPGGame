package com.embercrest.game.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.embercrest.game.ashley.componenets.ActionBarComponent;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.ashley.componenets.RenderComponent;
import com.embercrest.game.drawing_tools.StatusBar;
import com.embercrest.game.game.hud.actionbar.ActionBar;

public class CombatRenderSystem extends SortedIteratingSystem {
    private final SpriteBatch batch;
    private final Camera camera;

    public CombatRenderSystem(SpriteBatch batch, Camera camera) {
        super(Family.all(PositionComponent.class, RenderComponent.class, CombatComponent.class).get(), new RenderComparator());
        this.camera = camera;
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderCombatHud(entity);
    }

    private void renderCombatHud(Entity entity) {
        RenderComponent renderComponent = entity.getComponent(RenderComponent.class);
        if(!renderComponent.isVisible()) return;
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);

        CombatComponent combatComp = entity.getComponent(CombatComponent.class);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawHealthAndResourceBars(positionComponent, combatComp);

        //ActionBarComponent actionBarComponent = entity.getComponent(ActionBarComponent.class);
        //if(actionBarComponent != null)drawActionBar(positionComponent, actionBarComponent);

        //TODO
        //drawDebuffsAndBuffs();

        batch.end();
    }

    private void drawHealthAndResourceBars(PositionComponent positionComponent, CombatComponent combatComp) {
        //ProgressBar healthBar = combatComp.getHealthBar();
        StatusBar healthBar = combatComp.getHealthBar();
        if(healthBar.getValue() != combatComp.getHP()) healthBar.setValue(combatComp.getHP());
        healthBar.setPosition(positionComponent.getX()+16-healthBar.getWidth()/2f, positionComponent.getY()+48+18);

        //ProgressBar resourceBar = combatComp.getResourceBar();
        StatusBar resourceBar = combatComp.getResourceBar();
        if(resourceBar.getValue() != combatComp.getResource()) resourceBar.setValue(combatComp.getResource());
        resourceBar.setPosition(positionComponent.getX()+16-resourceBar.getWidth()/2f, positionComponent.getY()+48+8);

        healthBar.draw(batch, 1);
        resourceBar.draw(batch, 1);
    }

    private void drawDebuffsAndBuffs() {
    }
}
