package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.ashley.actions.Action;

public class HoverHandlingComponent implements Component {
    private boolean hovered = false;
    private Action OnHoverAction, OnHoverOffAction;

    public HoverHandlingComponent(Action OnHoverAction, Action OnHoverOffAction){
        this.OnHoverAction = OnHoverAction;
        this.OnHoverOffAction = OnHoverOffAction;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public Action getOnHoverAction() {
        return OnHoverAction;
    }

    public void setOnHoverAction(Action onHoverAction) {
        OnHoverAction = onHoverAction;
    }

    public Action getOnHoverOffAction() {
        return OnHoverOffAction;
    }

    public void setOnHoverOffAction(Action onHoverOffAction) {
        OnHoverOffAction = onHoverOffAction;
    }

}
