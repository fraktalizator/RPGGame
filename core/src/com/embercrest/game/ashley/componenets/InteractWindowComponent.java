package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.game.hud.interact_window.InteractWindow;

public class InteractWindowComponent implements Component {
    private InteractWindow interactWindow;
    //boolean isVisible = false;

    public InteractWindowComponent(InteractWindow interactWindow){
        this.interactWindow = interactWindow;
    }

    public InteractWindow getInteractWindow() {
        return interactWindow;
    }

    public void setInteractWindow(InteractWindow interactWindow) {
        if(interactWindow == null) throw new IllegalArgumentException("InteractWindow cannot be null");
        this.interactWindow = interactWindow;
    }


}
