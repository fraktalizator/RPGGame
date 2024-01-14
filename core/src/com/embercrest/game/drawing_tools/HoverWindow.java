package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class HoverWindow extends Window {
    public HoverWindow(Skin skin, String text) {
        super("", skin);
        setSize(230, 150);
        Label label = new Label(text, skin);
        label.setWidth(getWidth());
        label.setWrap(true);
    }
}
