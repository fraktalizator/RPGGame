package com.embercrest.game.game.abilities_Sellbook.Warrior;

import com.embercrest.game.game.abilities_Sellbook.SpellBook;
import com.embercrest.game.game.entities.PartyCharacter;

public class WarriorSpellBook extends SpellBook {
    public WarriorSpellBook(PartyCharacter partyCharacter) throws Exception {
        super(partyCharacter);
        addSpell(new Strike(this));
        for (int i = 0; i < 40; i++) {
            addSpell(new Strike(this));
        }
    }
}
