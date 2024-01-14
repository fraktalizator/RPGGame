package com.embercrest.game.game.hud.actionbar;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.game.abilities_Sellbook.Spell;
import com.embercrest.game.game.abilities_Sellbook.SpellSlot;
import com.embercrest.game.game.entities.PartyCharacter;

import java.util.ArrayList;

public class ActionBar extends Table {
    private ArrayList<SpellSlot> spellSlots = new ArrayList<>(14);
    public ActionBar(ActionBar actionBar) {
        super();
        for (int i = 0; i < 14; i++) {
            spellSlots.add(new SpellSlot());
        }
        for (int i = 0; i < 14; i++) {
            add(spellSlots.get(i));
            if(actionBar.getSpellSlots().get(i) != null) spellSlots.get(i).setSpell(actionBar.getSpellSlots().get(i).getSpell());
            if(i < 9 && i%6==0 && i > 0) row();
        }
        setPosition(actionBar.getX(), actionBar.getY());
    }

    public ActionBar() {
        super();
        for (int i = 0; i < 14; i++) {
            spellSlots.add(new SpellSlot());
        }
        for (int i = 0; i < 14; i++) {
            add(spellSlots.get(i));
            if(i < 9 && i%6==0 && i > 0) row();
        }
    }

    public void addSpell(Spell spell){
        SpellSlot firstEmpty = null;
        for (SpellSlot slot:spellSlots) {
            if(slot.getSpell() == null) {
                firstEmpty = slot;
                break;
            }
        }
        if(firstEmpty != null) firstEmpty.setSpell(spell);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //correctPosition();
        System.out.println(getX()+" SADAD"+ getY());
        super.draw(batch, parentAlpha);
    }

    public ArrayList<SpellSlot> getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(ArrayList<SpellSlot> spellSlots) {
        this.spellSlots = spellSlots;
    }
}
