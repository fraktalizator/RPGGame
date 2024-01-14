package com.embercrest.game.game.Items;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.embercrest.game.Assets;
import com.embercrest.game.drawing_tools.TextureActor;
import com.embercrest.game.game.hud.HUDStage;
import com.embercrest.game.game.hud.interact_window.DescriptionWindow;
import com.embercrest.game.game.hud.interact_window.InteractButtons;
import com.embercrest.game.game.hud.interact_window.InteractWindow;

public abstract class Item extends TextureActor {
    private ItemRarity itemRarity = ItemRarity.Common;
    private ItemSlot.SlotType itemType = ItemSlot.SlotType.Default;
    private int vendorPrice = 100;
    private int maxStackAmount = 20;
    private int currentStackAmount = 1;
    private int itemID;
    public boolean selected = false;
    private InteractWindow interactWindow;
    private DescriptionWindow descriptionWindow;
    private String name;
    private Label amountLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    public Item(Texture texture) {
        super(texture);
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(selected) {
            Color color = batch.getColor();
            batch.setColor(color.r, color.g, color.b, 0.5f);
            super.draw(batch, parentAlpha);
            batch.setColor(color.r, color.g, color.b, 1);
        }else super.draw(batch, parentAlpha);

        if(currentStackAmount > 1){
            amountLabel.setPosition(getX()+32-amountLabel.getPrefWidth(),getY()+3);
            //amountLabel.setFontScale(2f);
            //amountLabel.setColor(Color.RED);
            amountLabel.draw(batch, parentAlpha);
        }
    }

    public void leftClick(){
        if(!selected) select();
        else unSelect();
    }

    public abstract void useOnEntity(Entity entity);

    protected void setUpInteractWindow() {
        Skin skin = Assets.get().getSkin();
        InteractButtons buttons = new InteractButtons();
        interactWindow = new InteractWindow(name, itemRarity);

        interactWindow.add(buttons.getDropButton(this));
        interactWindow.row();
        interactWindow.add(buttons.getCloseButton(this));
        interactWindow.setHeight(interactWindow.getHeight()+80);
    }

    protected void setUpDescriptionWindow() {
        descriptionWindow = new DescriptionWindow(name, itemRarity);
    }


    public void drop() {
        currentStackAmount = 0;
    }
    public void removeOneAmount() {
        currentStackAmount = currentStackAmount -1;
        amountLabel.setText(currentStackAmount);
        if(currentStackAmount == 0 && selected) HUDStage.SelectedItem = null;
    }

    public void select() {
        selected = true;
        HUDStage.SelectedItem= this;
    }

    public void unSelect() {
        selected = false;
        HUDStage.SelectedItem = null;
    }
    //protected DescriptionWindow descriptionWindow = new DescriptionWIndow();


    public enum ItemRarity {
        Common(Color.BLACK, 1),
        UnCommon(Color.GREEN, 1),
        Rare(Color.BLUE, 1),
        Epic(Color.PURPLE, 1),
        Legendary(Color.CYAN, 1);

        public Color getColor() {
            return color;
        }
        public float getChances() {
            return chances;
        }

        private final Color color;
        private final float chances;

        ItemRarity(Color color, float chances) {
            this.color = color;
            this.chances = chances;

        }
    }

    public int getCurrentStackAmount() {
        return currentStackAmount;
    }
    public void setCurrentStackAmount(int currentStackAmount) {
        this.currentStackAmount = currentStackAmount;
        amountLabel.setText(currentStackAmount);
    }
    public InteractWindow getInteractWindow() {
        return interactWindow;
    }
    public DescriptionWindow getDescriptionWindow() {
        return descriptionWindow;
    }

    public ItemRarity getItemRarity() {
        return itemRarity;
    }

    public void setItemRarity(ItemRarity itemRarity) {
        this.itemRarity = itemRarity;
    }

    public int getVendorPrice() {
        return vendorPrice;
    }

    public void setVendorPrice(int vendorPrice) {
        this.vendorPrice = vendorPrice;
    }

    public int getMaxStackAmount() {
        return maxStackAmount;
    }

    public void setMaxStackAmount(int maxStackAmount) {
        this.maxStackAmount = maxStackAmount;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Label getAmountLabel() {
        return amountLabel;
    }

    public void setAmountLabel(Label amountLabel) {
        this.amountLabel = amountLabel;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public ItemSlot.SlotType getItemType() {
        return itemType;
    }

    public void setItemType(ItemSlot.SlotType itemType) {
        this.itemType = itemType;
    }
}
