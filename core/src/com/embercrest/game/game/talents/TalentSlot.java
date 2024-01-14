package com.embercrest.game.game.talents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.embercrest.game.Assets;

public class TalentSlot extends Group {
    private Talent talent;
    private boolean drawBackground = true;
    private final Texture backgroundTexture = Assets.TextureAssets.InventorySlot.getTexture();

    public TalentSlot() {
        setSize(32, 32);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (drawBackground) batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
        addActor(talent);
    }

    public boolean isDrawBackground() {
        return drawBackground;
    }

    public void setDrawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
    }
}
