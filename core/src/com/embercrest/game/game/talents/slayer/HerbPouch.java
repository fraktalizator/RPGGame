package com.embercrest.game.game.talents.slayer;

import com.embercrest.game.game.talents.Talent;
import com.embercrest.game.game.talents.TalentsTree;
import com.embercrest.game.Assets;

public class HerbPouch extends Talent {
    public HerbPouch(TalentsTree talentsTree) {
        super("HerbPouch", Assets.TextureAssets.HerbPouch.getTexture(), talentsTree);
        setUpDescriptionWindow();
        getDescriptionWindow().setDescription("immm herbs");
    }

    @Override
    public void applyEffect() {

    }
}
