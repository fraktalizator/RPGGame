package com.embercrest.game.screens.save_create_screen.character_setup.customize_options;

import com.embercrest.game.Assets;
import com.embercrest.game.screens.save_create_screen.character_setup.CustomizeOption;

public class ClothCustomizeOption extends CustomizeOption {
    public ClothCustomizeOption() {
        super("Base clothing: ", Assets.FemaleClothTextureAssets.values().length);
    }
}
