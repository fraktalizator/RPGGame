package com.embercrest.game.game.abilities_Sellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.componenets.ActionBarComponent;
import com.embercrest.game.game.entities.PartyCharacter;

import java.util.ArrayList;

public abstract class SpellBook extends Table {
    private final ArrayList<SpellSlot> spellSlots = new ArrayList<>();
    private final PartyCharacter partyCharacter;
    private int page = 0;
    private TextButton backPageBTN, nextPageBTN;


    public SpellBook(PartyCharacter partyCharacter) throws Exception {
        this.partyCharacter = partyCharacter;
        if(partyCharacter.getComponent(ActionBarComponent.class).getActionBar() == null) throw new Exception("character has to have action bar defined for the spellbook to be asigned");
        setDebug(false);
        setUpButtons();

        openPage(page);
//        addListener(new InputListener(){
//            @Override
//            public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
//                System.out.println("SCROLLED"+ amountX+" "+amountY);
//                boolean handled = super.scrolled(event, x, y, amountX, amountY);
//                if(amountY > 0){
//                    if(backPageBTN.isDisabled()) return handled;
//                    page--;
//                    openPage(page);
//                }
//                if(amountY < 0){
//                    if(nextPageBTN.isDisabled()) return handled;
//                    page++;
//                    openPage(page);
//                }
//                return handled;
//            }
//        });
    }

    private void openPage(int page) {
        clear();
        for (int i = 16*page; i < 16*(page+1); i++) {
            if(i%4 == 0) row();
            if(i < spellSlots.size()) add(spellSlots.get(i)).expand();
            else {
                Actor actor = new Actor();
                actor.setSize(32, 32);
                add(actor).expand();
            }
        }
        row();
        backPageBTN.setDisabled(!(page > 0));
        add(backPageBTN);

        add(partyCharacter.getComponent(ActionBarComponent.class).getActionBar()).colspan(2);

        nextPageBTN.setDisabled(!(16*(page+1) < spellSlots.size()));
        add(nextPageBTN);
    }

    public void addSpell(Spell spell){
        SpellSlot firstEmpty = new SpellSlot();
        spellSlots.add(firstEmpty);

        firstEmpty.setSpell(spell);
        spell.setOwner(partyCharacter);

        openPage(page);
    }

    private void setUpButtons() {
        backPageBTN = new TextButton("<-", Assets.get().getSkin());
        nextPageBTN = new TextButton("->", Assets.get().getSkin());
        backPageBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(backPageBTN.isDisabled()) return;
                page--;
                openPage(page);
            }
        });

        nextPageBTN.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(nextPageBTN.isDisabled()) return;
                page++;
                openPage(page);
            }
        });
    }
}
