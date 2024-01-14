package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WarningWindow extends Window {
    private final TextButton backBtn;
    public TextButton functionalBtn;
    private final Label label;
    private final WarningWindow warningWindow;
    public WarningWindow(String title,String functionalBtnText, String labelText, Skin skin) {
        super(title, skin);
        setVisible(false);
        backBtn = new TextButton("Back", skin);
        functionalBtn = new TextButton(functionalBtnText, skin);
        label = new Label(labelText, skin);
        setSize(250, 130);
        Table table = new Table();
        //table.setFillParent(true);
        table.add(label).expand().colspan(2).center().padBottom(15);
        table.row();
        table.add(backBtn).padRight(15);
        table.add(functionalBtn).padLeft(15);
        warningWindow = this;
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                warningWindow.setVisible(false);
            }
        });
        add(table);
    }

    public void pop(){
        setPosition(Gdx.graphics.getWidth()/2f- this.getWidth()/2f, Gdx.graphics.getHeight()/2f-this.getHeight()/2f);
        toFront();
        setVisible(true);
    }
}
