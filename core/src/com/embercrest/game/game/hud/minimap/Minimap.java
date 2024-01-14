package com.embercrest.game.game.hud.minimap;

import static com.embercrest.game.ashley.componenets.PositionComponent.GRIDSIZE;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.game.maps.Xentos;

public class Minimap extends Window {
    private final Xentos xentos;
    private final OrthographicCamera gameScreenCamera;
    public Minimap(Xentos xentos, OrthographicCamera gameScreenCamera) {
        super("", Assets.get().getSkin());
        this.xentos = xentos;
        this.gameScreenCamera = gameScreenCamera;
        setSize(400, 400);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    private Drawable generateDrawableMinimap(){

        // TODO
        //check if position of camera have changed if so then:
        // get minimap image based on the current player location
        // draw part of the image based on the camera possition
        // draw entities(enemies, walls, interactables...)
        //return and draw minimap
        Pixmap pixmap = new Pixmap(400, 400, Pixmap.Format.RGB888);
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable();
        float positionX = gameScreenCamera.position.x-gameScreenCamera.viewportWidth/2f;
        float positionY = gameScreenCamera.position.y-gameScreenCamera.viewportHeight/2f;
        Vector2 iterationPosition = new Vector2(positionX - positionX%GRIDSIZE, positionY - positionY%GRIDSIZE);
        xentos.getTileCost(iterationPosition);
        //pixmap.drawRectangle();
        return null;
    }
}
