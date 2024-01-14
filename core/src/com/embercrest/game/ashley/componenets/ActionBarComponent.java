package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.game.hud.actionbar.ActionBar;

public class ActionBarComponent implements Component {
    private final ActionBar actionBar;
    public ActionBarComponent(ActionBar actionBar){
        this.actionBar = actionBar;
    }

    public ActionBar getActionBar() {
        return actionBar;
    }
}
