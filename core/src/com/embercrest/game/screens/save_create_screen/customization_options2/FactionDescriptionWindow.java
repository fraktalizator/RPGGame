package com.embercrest.game.screens.save_create_screen.customization_options2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class FactionDescriptionWindow extends Window {
    private final Label description;
    private final String mageDescription = "Mage is a class that shoots fireballs from their assholes";
    private final String mechDescription = "mech";
    public FactionDescriptionWindow(Skin skin) {
        super("", skin);
        setSize(Gdx.graphics.getWidth()*0.17f,Gdx.graphics.getHeight()*0.45f);
        setMovable(false);
        setBounds(getX(),getY(),getWidth(),getHeight());
        description = new Label("", skin);
        description.setWidth(getWidth());
        description.setWrap(true);
        add(description).width(getWidth()*0.9f);
    }

    public void setFaction(int factionId){
        if(factionId == 0) description.setText(mageDescription);
        else if( factionId == 1) description.setText(mechDescription);
    }
}
