package com.embercrest.game.drawing_tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Null;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;

import java.util.ArrayList;
import java.util.List;

public class WarningText extends Group {

    //private Color fontColor = Color.RED;

    private final ArrayList<Label> currentlyDisplayedLabels = new ArrayList<>();
    private final ArrayList<String> messages;
    private final int labelsCount;

    public WarningText(@Null List<String> messages, int labelsCount){
        this.labelsCount = labelsCount;
        this.messages = new ArrayList<>();
        if(messages != null)this.messages.addAll(messages);
        setTouchable(Touchable.disabled);
    }

    public void popMessage(){
        if(messages.size() < 1) return;
        toFront();
        deleteLastMessage();
        displayNewMessage(messages.get(0));
    }

    public void popMessage(int id){
        if(messages.size() < id+1) return;
        toFront();
        deleteLastMessage();
        displayNewMessage(messages.get(id));
    }

    public void popMessage(String message){
        toFront();
        deleteLastMessage();
        displayNewMessage(message);

    }

    private void displayNewMessage(String message){
        Label labelForDisplay = new Label(message, Assets.get().getSkin());
        addActor(labelForDisplay);
        float posX = Gdx.graphics.getWidth()/2f-labelForDisplay.getPrefWidth()/2f;
        float posY = Gdx.graphics.getHeight()/2f-labelForDisplay.getPrefHeight()/2f;
        labelForDisplay.setPosition(posX, posY);
        labelForDisplay.setVisible(true);
        labelForDisplay.addAction(Actions.alpha(1, 0));
        labelForDisplay.addAction(Actions.alpha(0, 3.5f));
        labelForDisplay.addAction(Actions.moveTo(labelForDisplay.getX(), labelForDisplay.getY()+ Gdx.graphics.getHeight()/3f, 5));
        currentlyDisplayedLabels.add(labelForDisplay);
    }

    private void deleteLastMessage() {
        if(labelsCount <= currentlyDisplayedLabels.size()){
            currentlyDisplayedLabels.get(0).clear();
            currentlyDisplayedLabels.get(0).remove();
            currentlyDisplayedLabels.remove(currentlyDisplayedLabels.get(0));
        }
    }

}

//
//    public void setColor(Color color){
//        this.fontColor = color;
//        for(int i=0;i< labels.size();i++){
//            labels.get(i).getStyle().fontColor = color;
//        }
//    }
