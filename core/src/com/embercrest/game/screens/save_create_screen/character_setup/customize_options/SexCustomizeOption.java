package com.embercrest.game.screens.save_create_screen.character_setup.customize_options;

import com.embercrest.game.screens.save_create_screen.character_setup.CustomizeOption;

public class SexCustomizeOption extends CustomizeOption {
    public SexCustomizeOption() {
        super("Sex: ", 2);
    }
    @Override
    public void setCustomLabel() {
        String sexName = (getCurrentStyle() == 1  ? "male" : "female");
        currentSelectedLabel.setText(optionName+" "+sexName);
    }
}
