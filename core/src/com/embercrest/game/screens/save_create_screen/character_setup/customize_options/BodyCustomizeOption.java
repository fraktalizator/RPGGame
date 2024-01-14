package com.embercrest.game.screens.save_create_screen.character_setup.customize_options;

import com.embercrest.game.Assets;
import com.embercrest.game.screens.save_create_screen.character_setup.CustomizeOption;

public class BodyCustomizeOption extends CustomizeOption {
    public BodyCustomizeOption() {
        super("Body: ", Assets.FemaleBodyTextureAssets.values().length);
    }
}
