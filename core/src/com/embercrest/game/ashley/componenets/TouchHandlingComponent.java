package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.embercrest.game.ashley.actions.Action;

public class TouchHandlingComponent implements Component {
    private Action RightClickAction;
    private Action LeftClickAction;

    public TouchHandlingComponent(Action LeftClickAction, Action RightClickAction) {
        this.LeftClickAction = LeftClickAction;
        this.RightClickAction = RightClickAction;
    }

    public Action getRightClickAction() {
        return RightClickAction;
    }

    public Action getLeftClickAction() {
        return LeftClickAction;
    }

    public void setRightClickAction(Action rightClickAction) {this.RightClickAction = rightClickAction;}

    public void setLeftClickAction(Action leftClickAction) {
        LeftClickAction = leftClickAction;
    }
}
