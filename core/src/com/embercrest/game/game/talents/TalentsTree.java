package com.embercrest.game.game.talents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.embercrest.game.game.abilities_Sellbook.Warrior.Strike;
import com.embercrest.game.game.entities.PartyCharacter;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.embercrest.game.game.talents.slayer.HerbPouch;
import com.embercrest.game.game.talents.warrior.HeavyArm;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class TalentsTree extends Table {
    protected HashMap<Talent, ArrayList<Talent>> DependantTalents = new HashMap<>();
    private final ArrayList<TalentSlot> talentSlots = new ArrayList<>();
    public int talentPoints = 0;
    private final PartyCharacter partyCharacter;

    //Texture bg = new Texture(Gdx.files.internal("Untitled.png"));

    public TalentsTree(PartyCharacter partyCharacter){
        this.partyCharacter = partyCharacter;
        ArrayList<Talent> talents = new ArrayList<>();
        talents.add(new HerbPouch(this));
        setDebug(false);
        //setBackground(new TextureRegionDrawable(bg));
        DependantTalents.put(new HerbPouch(this),talents );
    }

    public void addTalent(@Null Talent talent){
        TalentSlot firstEmpty = new TalentSlot();
        if(talent != null) firstEmpty.setTalent(talent);
        firstEmpty.setDrawBackground(false);

        talentSlots.add(firstEmpty);
        //add(firstEmpty).pad(3f);
        //add(firstEmpty).padBottom(1.5f).padTop(1.5f).padRight(10f).padLeft(10f);
        add(firstEmpty).expand();
    }

//    public <T extends Talent> T getTalent(Class<T> talentClass){
//        for (TalentSlot slot:talentSlots) {
//            slot.getTalent().
//        }
//    }

    public PartyCharacter getCharacter() {
        return partyCharacter;
    }
}
