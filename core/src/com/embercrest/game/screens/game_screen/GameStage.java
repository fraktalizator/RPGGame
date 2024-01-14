package com.embercrest.game.screens.game_screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage implements InputProcessor {
    public GameStage(Viewport viewport, SpriteBatch mainBatch) {
        super(viewport, mainBatch);
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        Vector2 coord = screenToStageCoordinates(new Vector2((float)screenX, (float)screenY));
        Actor hitActor =  hit(coord.x, coord.y, true);
        return hitActor != null;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        return false;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        super.touchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override
    public boolean mouseMoved (int screenX, int screenY) {
        super.mouseMoved(screenX, screenY);
        return false;
    }


}
