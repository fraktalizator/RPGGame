package com.embercrest.game.game.abilities_Sellbook;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Null;
import com.embercrest.game.Assets;

public class SpellSlot extends Group {
    private Spell spell;
    private final boolean drawBackground = true;
    private final Texture backgroundTexture = Assets.TextureAssets.InventorySlot.getTexture();

    public SpellSlot() {
        setSize(32, 32);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (drawBackground) batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(@Null Spell spell) {
        if(spell == null) {
            if(this.spell != null) this.spell.remove();
            this.spell = null;
            return;
        }
        this.spell = spell;
        addActor(spell);
    }
}
