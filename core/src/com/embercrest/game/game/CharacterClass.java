package com.embercrest.game.game;

import com.embercrest.game.game.abilities_Sellbook.SpellBook;
import com.embercrest.game.game.abilities_Sellbook.Warrior.WarriorSpellBook;
import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.game.talents.TalentsTree;
import com.embercrest.game.game.talents.slayer.SlayerTalentTree;
import com.embercrest.game.game.talents.warrior.WarriorTalentsTree;

public class CharacterClass {
    TalentsTree talentsTree;
    SpellBook spellbook;

    public CharacterClass(TalentsTree talentsTree, SpellBook spellbook){
        this.talentsTree = talentsTree;
        this.spellbook = spellbook;
    }
    public TalentsTree getTalentsTree() {
        return talentsTree;
    }

    public SpellBook getSpellbook() {
        return spellbook;
    }


    public enum PlayerClass{
        Warrior(),
        Slayer();

        public CharacterClass getNew(PartyCharacter partyCharacter) throws Exception {
            if(this == PlayerClass.Warrior){
                return new CharacterClass(new WarriorTalentsTree(partyCharacter), new WarriorSpellBook(partyCharacter));
            }else if(this == PlayerClass.Slayer){
                return new CharacterClass(new SlayerTalentTree(partyCharacter), new WarriorSpellBook(partyCharacter));
            }
            return null;
        }
    }
}

