package com.embercrest.game.game.hud.party_frame;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.game.entities.PartyCharacter;

import java.util.ArrayList;

public class PartyFrame extends Table {
    private ArrayList<PartyCharacter> party;

    public PartyFrame(ArrayList<PartyCharacter> party) {
        for(PartyCharacter c : party){
            add(new PlayerFrame(c));
            row();
        }
    }

    public void resize(int width, int height) {
        // TODO
    }
}
