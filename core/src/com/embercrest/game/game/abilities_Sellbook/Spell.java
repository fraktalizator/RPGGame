package com.embercrest.game.game.abilities_Sellbook;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.ashley.componenets.ActionBarComponent;
import com.embercrest.game.drawing_tools.TextureActor;
import com.embercrest.game.game.hud.interact_window.DescriptionWindow;
import com.embercrest.game.game.entities.PartyCharacter;

public abstract class Spell extends TextureActor {
    // properties of the spells
    private int cd = 0;
    private final int range = 0;
    private final int levelRequired = 0;
    private final boolean isPassive = false;
    private final int requiresResource = 0;
    public String name;

    // rest of necessary properties
    private DescriptionWindow descriptionWindow;
    SpellBook spellbook;
    private PartyCharacter owner;
    private final float alpha = 0.6f;
    public boolean isInSpellBook = true;

    public Label cdLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    public Spell(String name, Texture texture, SpellBook spellbook){
        super(texture);
        this.spellbook = spellbook;
        this.name = name;
        setTouchable(Touchable.enabled);

        addListener(new ClickListener(){

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if(cd > 0){
                    System.out.println("THIS ABILITY ISNT READY YET");
                    return;
                }
                applyEffect(owner);
            }
        });
    }

    public abstract Spell getNew();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(cd > 0) {
            Color color = batch.getColor();
            batch.setColor(color.r, color.g, color.b, alpha);
            super.draw(batch, parentAlpha);
            batch.setColor(color.r, color.g, color.b, 1);

            cdLabel.setPosition(getX() +10,getY()+15);
            cdLabel.setFontScale(2f);
            cdLabel.setColor(Color.RED);
            cdLabel.draw(batch, parentAlpha);
        }else super.draw(batch, parentAlpha);
    }

    public void applyEffect(PartyCharacter partyCharacter){
        partyCharacter.getComponent(ActionBarComponent.class).getActionBar().addSpell(getNew());
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
        cdLabel.setText(cd);
    }

    public void setOwner(PartyCharacter owner) {
        this.owner = owner;
    }

    public PartyCharacter getOwner(){ return owner;}

    protected void setUpDescriptionWindow() {
        descriptionWindow = new DescriptionWindow(name);
    }

    public DescriptionWindow getDescriptionWindow() {
        return descriptionWindow;
    }
}
