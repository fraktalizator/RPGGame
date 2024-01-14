package com.embercrest.game.game.talents.slayer;

import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.game.talents.TalentsTree;

public class SlayerTalentTree extends TalentsTree {
    public SlayerTalentTree(PartyCharacter partyCharacter) {
        super(partyCharacter);
        addTalent(new HerbPouch(this));
        addTalent(new HerbPouch(this));
        addTalent(new HerbPouch(this));
    }
}