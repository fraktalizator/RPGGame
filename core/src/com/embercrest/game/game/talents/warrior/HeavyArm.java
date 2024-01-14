package com.embercrest.game.game.talents.warrior;

import com.embercrest.game.game.talents.Talent;
import com.embercrest.game.game.talents.TalentsTree;
import com.embercrest.game.Assets;

public class HeavyArm extends Talent {
    public HeavyArm(TalentsTree talentsTree) {
        super("heavy arm", Assets.TextureAssets.HeavyArm.getTexture(), talentsTree);
        setMaxStackAmount(4);

        setUpDescriptionWindow();
        getDescriptionWindow().setDescription("increase strength");
    }

    @Override
    public void applyEffect() {
        System.out.println("TALENT USED");
    }
}
