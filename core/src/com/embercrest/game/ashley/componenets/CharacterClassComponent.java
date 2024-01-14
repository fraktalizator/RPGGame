package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.game.CharacterClass;


public class CharacterClassComponent implements Component {
    private final CharacterClass characterClass;

    public CharacterClassComponent(CharacterClass characterClass){
        this.characterClass = characterClass;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }
}
