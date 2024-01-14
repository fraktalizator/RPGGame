package com.embercrest.game.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.embercrest.game.Assets;


public class ItemSlot extends Group {
    private Item item;
    private SlotType slotType = SlotType.Default;
    private final boolean drawBackground = true;
    public boolean isHovered = false;
    //private final Texture backgroundTexture = ((EmberCrest)Gdx.app.getApplicationListener()).assetManager.get(((EmberCrest)Gdx.app.getApplicationListener()).assets.inventory_slot);
    private final Texture backgroundTexture = Assets.TextureAssets.InventorySlot.getTexture();

    public ItemSlot() {
        setSize(32, 32);
    }

    @Override
    public void act(float delta) {
        if(item != null && item.getCurrentStackAmount() == 0 ) {
            item.remove();
            item = null;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (drawBackground) batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }

    public enum SlotType {
        Default(),
        Head(),
        Neck(),
        Shoulders(),
        MainHand(),
        Offhand(),
        Chest(),
        Legs(),
        Ring(),
        Boots(),
        Gloves()
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if(item.getItemType() != getSlotType()) throw new IllegalCallerException("Prosze tu nie dzwonic panie caller");
        this.item = item;
        item.setPosition(0, 1);
        addActor(item);
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }
}
