package com.embercrest.game.ashley.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.ashley.actions.Action;
import com.embercrest.game.ashley.componenets.HoverHandlingComponent;
import com.embercrest.game.ashley.componenets.PathToTileComponent;
import com.embercrest.game.ashley.componenets.PositionComponent;
import com.embercrest.game.ashley.componenets.RenderComponent;
import com.embercrest.game.ashley.componenets.TouchHandlingComponent;
import com.embercrest.game.game_tools.Pathfinding;
import com.embercrest.game.Assets;

import java.util.ArrayList;

public class MoveTile extends InteractAbleEntity {
    private final Texture moveTileTexture = Assets.TextureAssets.MoveTile.getTexture();
    private final Texture moveTileSelectedTexture = Assets.TextureAssets.MoveTileSelected.getTexture();

    public MoveTile(Vector2 position, ArrayList<Pathfinding.Direction> pathTo) {
        super(position, Assets.TextureAssets.MoveTile.getTexture(), false);
        PathToTileComponent pathToTileComponent = new PathToTileComponent(pathTo);
        getComponent(PositionComponent.class).setHeight(32);
        HoverHandlingComponent hoverHandlingComponent = new HoverHandlingComponent(Action.pass, Action.pass);
        TouchHandlingComponent touchHandlingComponent = new TouchHandlingComponent(Action.pass, Action.pass);
        getComponent(RenderComponent.class).setZindex(0);
        this.add(pathToTileComponent);
        this.add(hoverHandlingComponent);
        this.add(touchHandlingComponent);
    }

    public void select(){
        getComponent(RenderComponent.class).setTexture(moveTileSelectedTexture);
    }

    public void unSelect(){
        getComponent(RenderComponent.class).setTexture(moveTileTexture);
    }
}
