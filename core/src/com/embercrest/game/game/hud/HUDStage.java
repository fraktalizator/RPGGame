package com.embercrest.game.game.hud;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.embercrest.game.game.Items.Item;
import com.embercrest.game.game.Items.consumables.Apple;
import com.embercrest.game.game.abilities_Sellbook.Spell;
import com.embercrest.game.game.abilities_Sellbook.SpellSlot;
import com.embercrest.game.game.hud.actionbar.ActionBar;
import com.embercrest.game.game.hud.interact_window.DescriptionWindow;
import com.embercrest.game.game.hud.interact_window.InteractWindow;
import com.embercrest.game.game.talents.Talent;

public class HUDStage extends Stage implements InputProcessor {
    private DescriptionWindow currentVisibleDescriptionWindow;
    private InteractWindow currentVisibleInteractWindow;
    private Actor currentHoveredActor;
    private ActionBar actionBar;
    public static Item SelectedItem;

    public HUDStage(Viewport viewport, SpriteBatch mainBatch) {
        super(viewport, mainBatch);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean handled = super.touchDown(screenX, screenY, pointer, button);
        Vector2 tempCoords = screenToStageCoordinates(new Vector2(screenX, screenY));
        Actor target = hit(tempCoords.x, tempCoords.y, true);

        closeAllRedundantWindows();

        if(button == 0) {
            if (target instanceof Item) {
                Item item = (Item) target;
                if (SelectedItem != null && !SelectedItem.equals(item)) {
                    SelectedItem.unSelect();
                }
                item.leftClick();
                item.getDescriptionWindow().toFront();
            }

            if (target instanceof Talent) {
                Talent talent = (Talent) target;
                talent.getDescriptionWindow().toFront();
            }

            if (target instanceof Spell) {
                Spell spell = (Spell) target;
                spell.getDescriptionWindow().toFront();
            }
        }else if(button == 1){
            if (target instanceof Item) {
                Item item = (Item) target;
                item.getDescriptionWindow().toFront();
                InteractWindow interactWindow = item.getInteractWindow();
                Vector2 windowPos = item.localToStageCoordinates(new Vector2(item.getX()+36, item.getY()-interactWindow.getHeight()+item.getHeight()));

                displayInteractWindow(interactWindow, windowPos);
            }

            if (target instanceof Talent) {
                Talent talent = (Talent) target;
                talent.getDescriptionWindow().toFront();
            }

            if (target instanceof Spell) {
                Spell spell = (Spell) target;
                spell.getDescriptionWindow().toFront();
                if(!spell.isInSpellBook){
                    target.setVisible(false);
                    Actor spellSlot = hit(tempCoords.x, tempCoords.y, true);
                    if(spellSlot instanceof SpellSlot) ((SpellSlot)spellSlot).setSpell(null);
                }
            }
        }

        return handled;
    }

    private void closeAllRedundantWindows() {
        if(currentVisibleInteractWindow != null){
            currentVisibleInteractWindow.remove();
            currentVisibleInteractWindow = null;
        }
        if(actionBar != null){
            actionBar.remove();
            actionBar = null;
        }
    }

    public void displayInteractWindow(InteractWindow interactWindow, Vector2 position){
        closeAllRedundantWindows();
        interactWindow.setPosition(position);
        interactWindow.toFront();
        interactWindow.setZIndex(Integer.MAX_VALUE);
        interactWindow.setVisible(true);
        currentVisibleInteractWindow = interactWindow;
        addActor(interactWindow);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean handled = super.touchUp(screenX, screenY, pointer, button);
        Vector2 tempCoords = screenToStageCoordinates(new Vector2(screenX, screenY));
        Actor target = hit(tempCoords.x, tempCoords.y, true);


        return handled;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        boolean handled = super.mouseMoved(screenX, screenY);
        Vector2 tempCoords = screenToStageCoordinates(new Vector2(screenX, screenY));
        Actor target = hit(tempCoords.x, tempCoords.y, true);
        if(target == null) {
            hoverOff();
            return handled;
        }
        hover(target);
        return true;
    }

    private boolean hover(Actor actor){
        if(actor.equals(currentHoveredActor)) return false;
        hoverOff();
        currentHoveredActor = actor;

        DescriptionWindow descriptionWindow = null;
        if(currentHoveredActor instanceof Spell){
            Spell spell = (Spell)currentHoveredActor;
            descriptionWindow = spell.getDescriptionWindow();
            //descriptionWindow.setPosition(tempCoords.x, tempCoords.y);
        }
        else if(currentHoveredActor instanceof Item){
            Item item = (Item)currentHoveredActor;
            descriptionWindow = item.getDescriptionWindow();
            //descriptionWindow.setPosition(tempCoords.x, tempCoords.y);
        }
        else if(currentHoveredActor instanceof Talent){
            Talent talent = (Talent)currentHoveredActor;
            descriptionWindow = talent.getDescriptionWindow();
            //descriptionWindow.setPosition(tempCoords.x, tempCoords.y);
        }
        if(descriptionWindow == null) return false;
        Vector2 windowPos = currentHoveredActor.localToStageCoordinates(new Vector2(currentHoveredActor.getX()+16-descriptionWindow.getWidth()/2f, currentHoveredActor.getY()+32+10));
        descriptionWindow.setVisible(true);
        descriptionWindow.setPosition(windowPos);
        descriptionWindow.toFront();
        descriptionWindow.setZIndex(Integer.MAX_VALUE);
        addActor(descriptionWindow);
        currentVisibleDescriptionWindow = descriptionWindow;
        return true;
    }

    private void hoverOff(){
        if(currentHoveredActor == null) return;

        if(currentHoveredActor instanceof Spell){
            ((Spell)currentHoveredActor).getDescriptionWindow().setVisible(false);
            ((Spell)currentHoveredActor).getDescriptionWindow().remove();
            currentHoveredActor = null;
        }
        else if(currentHoveredActor instanceof Item){
            ((Item)currentHoveredActor).getDescriptionWindow().setVisible(false);
            ((Item)currentHoveredActor).getDescriptionWindow().remove();
            currentHoveredActor = null;
        }
        else if(currentHoveredActor instanceof Talent){
            ((Talent)currentHoveredActor).getDescriptionWindow().setVisible(false);
            ((Talent)currentHoveredActor).getDescriptionWindow().remove();
            currentHoveredActor = null;
        }
    }

    public void displayActionBar(ActionBar actionBar){
        if(this.actionBar != null) {
            this.actionBar.remove();
        }
        this.actionBar = new ActionBar(actionBar);
        addActor(this.actionBar);
    }
}
