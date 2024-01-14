package com.embercrest.game.game.talents;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.embercrest.game.drawing_tools.TextureActor;
import com.embercrest.game.game.hud.interact_window.DescriptionWindow;

import java.util.ArrayList;

public abstract class Talent extends TextureActor {
    private int maxStackAmount = 1;
    private int currentStackAmount = 0;
    private DescriptionWindow descriptionWindow;
    private boolean isLocked = true;
    public String name;
    private final TalentsTree talentsTree;
    public Label currentStackAmountLabel = new Label("0", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    public Talent(String name, Texture texture, TalentsTree talentsTree){
        super(texture);
        this.talentsTree = talentsTree;
        this.name = name;
        setTouchable(Touchable.enabled);
        Talent ten = this;
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isLocked) return true;
                ArrayList<Talent> talentsForUnlock = talentsTree.DependantTalents.get(ten);
                if(button == 0){
                    if(currentStackAmount < maxStackAmount){
                        currentStackAmount++;
                        currentStackAmountLabel.setText(currentStackAmount);
                        // use one talent point from pool
                    }
                }if(button == 1){
                    if(currentStackAmount > 0){
                        currentStackAmount--;
                        currentStackAmountLabel.setText(currentStackAmount);
                        // add one talent point to pool
                    }
                }
                if(talentsForUnlock == null) return true;
                if(currentStackAmount == maxStackAmount){
                    for (Talent talent: talentsForUnlock) {
                        talent.setLocked(false);
                    }
                }else {
                    for (Talent talent: talentsForUnlock) {
                        talent.setLocked(true);
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isLocked) {
            Color color = batch.getColor();
            batch.setColor(color.r, color.g, color.b, 0.4f);
            super.draw(batch, parentAlpha);
            batch.setColor(color.r, color.g, color.b, 1);
        }else super.draw(batch, parentAlpha);
        currentStackAmountLabel.setPosition(getX()+32-currentStackAmountLabel.getPrefWidth(),getY()+3);
        currentStackAmountLabel.draw(batch, parentAlpha);
    }

    protected void setUpDescriptionWindow(){
        descriptionWindow = new DescriptionWindow(name);
    }

    public abstract void applyEffect();

    public int getMaxStackAmount() {
        return maxStackAmount;
    }

    public void setMaxStackAmount(int maxStackAmount) {
        this.maxStackAmount = maxStackAmount;
    }

    public int getCurrentStackAmount() {
        return currentStackAmount;
    }

    public void setCurrentStackAmount(int currentStackAmount) {
        this.currentStackAmount = currentStackAmount;
    }

    public TalentsTree getTalentsTree() {
        return talentsTree;
    }

    public DescriptionWindow getDescriptionWindow() {
        return descriptionWindow;
    }

    public void setDescriptionWindow(DescriptionWindow descriptionWindow) {
        this.descriptionWindow = descriptionWindow;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
