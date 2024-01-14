package com.embercrest.game.game.hud.party_manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.ashley.componenets.RenderComponent;
import com.embercrest.game.drawing_tools.AnimatedTextureActor;
import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.game.hud.party_frame.PlayerFrame;

import java.util.ArrayList;

public class OnePlayerButton extends Table {
    static ArrayList<OnePlayerButton> buttons = new ArrayList<>();

    public AnimatedTextureActor animation;
    public boolean selected = false;

    private final PartyCharacter partyCharacter;

    public OnePlayerButton(PartyCharacter partyCharacter) {
        this.partyCharacter = partyCharacter;
        PlayerFrame playerFrame = new PlayerFrame(partyCharacter);
        animation = new AnimatedTextureActor(playerFrame.getEntity().getComponent(RenderComponent.class).getPlayerAnimation());
        animation.setPosFrame(2);
        animation.setSize(playerFrame.getHeight(), playerFrame.getHeight());
        animation.freeze(true);
        add(playerFrame);
        add(animation).width(animation.getWidth()).height(animation.getHeight());
        setTouchable(Touchable.enabled);
        setDebug(false);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                unSelectAll();
                selected = true;
                animation.freeze(false);
                //animation.getColor().a = 1f;
            }
        });
        buttons.add(this);
    }

    private void unSelectAll() {
        for (OnePlayerButton button : buttons) {
            button.selected = false;
            button.animation.freeze(true);
        }
    }

    public static OnePlayerButton getSelected() {
        for (OnePlayerButton button : buttons) {
            if (button.selected) return button;
        }
        return null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = batch.getColor();
        if (selected) super.draw(batch, parentAlpha);
        else super.draw(batch, 0.4f);
        batch.setColor(color.r, color.g, color.b, 1f);
    }


    public PartyCharacter getCharacter() {
        return partyCharacter;
    }
}
