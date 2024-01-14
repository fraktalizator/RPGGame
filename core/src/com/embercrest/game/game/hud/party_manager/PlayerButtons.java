package com.embercrest.game.game.hud.party_manager;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.game.entities.PartyCharacter;

import java.util.ArrayList;

public class PlayerButtons extends Table {
    private final ArrayList<OnePlayerButton> buttons = new ArrayList<>();

    public PlayerButtons(ArrayList<PartyCharacter> party) {
        for (PartyCharacter c : party) {
            buttons.add(new OnePlayerButton(c));
        }
        buttons.get(0).selected = true;
        buttons.get(0).animation.freeze(false);
        setDebug(false);
        setUpTable();
    }

    private void setUpTable() {
        int fixer = 5;
        for (OnePlayerButton button : buttons) {
            add(button).padTop(15).padBottom(15);
            row();
            fixer--;
        }
        for (int i = 0; i < fixer; i++) {
            add(new Actor()).width(buttons.get(0).getPrefWidth()).height(buttons.get(0).getPrefHeight()).padTop(15).padBottom(15);
            row();
        }
    }

}
