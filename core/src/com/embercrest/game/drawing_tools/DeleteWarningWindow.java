package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.screens.save_select_screen.SaveButton;

public class DeleteWarningWindow extends Window {
    private final TextButton backBtn;
    public TextButton functionalBtn;
    private final Label label;
    private final DeleteWarningWindow warningWindow;
    public TextField textField;
    public DeleteWarningWindow(Skin skin) {
        super("Warning", skin);
        setVisible(false);
        backBtn = new TextButton("Back", skin);
        functionalBtn = new TextButton("Delete", skin);
        label = new Label("Delete save \""+"null"+"\"", skin);
        setSize(250, 130);
        Table table = new Table();
        functionalBtn.setDisabled(false);
        textField = new TextField("", skin);
        table.add(label).expand().colspan(2).center().padBottom(15);
        table.row();
        table.add(textField).expand().colspan(2).center().padBottom(15);
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
        assert SaveButton.getSelectedButton() != null;
        //label.setText("Delete save \""+ SaveButton.getSelectedButton().name+"\"");
        setPosition(Gdx.graphics.getWidth()/2f- this.getWidth()/2f, Gdx.graphics.getHeight()/2f-this.getHeight()/2f);
        toFront();
        setVisible(true);
    }

}
