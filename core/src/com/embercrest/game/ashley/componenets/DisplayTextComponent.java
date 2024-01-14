package com.embercrest.game.ashley.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;

public class DisplayTextComponent implements Component {
    private String text;
    private Label label;

    public DisplayTextComponent(String text) {
        this.text = text;
        label = new Label(text, Assets.get().getSkin());
    }

    public void setText(String text) {
        this.text = text;
        label.setText(text);
    }

    public String getText() {
        return text;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
        text = String.valueOf(label.getText());
    }
}
