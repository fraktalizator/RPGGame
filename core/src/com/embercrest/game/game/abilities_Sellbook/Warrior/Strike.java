package com.embercrest.game.game.abilities_Sellbook.Warrior;

import com.badlogic.gdx.Gdx;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.game.Items.consumables.Apple;
import com.embercrest.game.game.abilities_Sellbook.Spell;
import com.embercrest.game.game.abilities_Sellbook.SpellBook;
import com.embercrest.game.game.entities.PartyCharacter;
import com.embercrest.game.screens.battle_screen.BattleScreen;
import com.embercrest.game.Assets;

public class Strike extends Spell {
    SpellBook spellbook;
    public Strike(SpellBook spellbook) {
        super("Strike", Assets.TextureAssets.HeavyArm.getTexture(), spellbook);
        this.spellbook = spellbook;
        setUpDescriptionWindow();
        getDescriptionWindow().setDescription("Strikes ENEMY HARD !");
    }

    @Override
    public void applyEffect(PartyCharacter partyCharacter) {
        if(isInSpellBook) {
            super.applyEffect(partyCharacter);
            return;
        }
        System.out.println("Striked ");
        //partyCharacter.getComponent(CharacterClassComponent.class).getCharacterClass().getTalentsTree()
        //TODO modify if good talents taken
        if(!partyCharacter.getComponent(CombatComponent.class).dealDamage(10)) System.out.println("KILLLLLLLLLLLLLLEDDDDDDDDDDD");
        ((BattleScreen)((EmberCrest)Gdx.app.getApplicationListener()).getScreen()).getHud().AddItemToInventory(new Apple(7));

    }

    @Override
    public Spell getNew() {
        Strike strike = new Strike(spellbook);
        strike.setOwner(getOwner());
        strike.isInSpellBook = false;
        return strike;
    }
}
