package com.embercrest.game.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.embercrest.game.ashley.componenets.DisplayTextComponent;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.ashley.componenets.RenderComponent;

public class RenderSystem extends SortedIteratingSystem {
    private final SpriteBatch batch;
    private final Camera camera;

    public RenderSystem(SpriteBatch batch, Camera camera) {
        super(Family.all(PositionComponent.class, RenderComponent.class).get(), new RenderComparator());
        this.camera = camera;
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent renderComponent = entity.getComponent(RenderComponent.class);
        if(!renderComponent.isVisible()) return;

        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);

        if(!renderComponent.getFreezeAnimation()) renderComponent.setElapsedTime(renderComponent.getElapsedTime() + deltaTime);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if(renderComponent.isAnimate()){
            batch.draw(renderComponent.getPlayerAnimation().get(renderComponent.getPosFrame()).getKeyFrame(renderComponent.getElapsedTime(), true), positionComponent.getX(), positionComponent.getY());
        }else {
            batch.draw(renderComponent.getTexture(), positionComponent.getX(), positionComponent.getY(), positionComponent.getWidth(), positionComponent.getHeight());
        }
        batch.end();
        if(entity.getComponent(DisplayTextComponent.class) != null){
            Label text = entity.getComponent(DisplayTextComponent.class).getLabel();
            text.setPosition(positionComponent.getX()+5,positionComponent.getY()+26);
            text.draw(batch, 1);
        }


    }
}
