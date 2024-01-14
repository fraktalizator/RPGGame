package com.embercrest.game.game.hud.inwentory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.embercrest.game.drawing_tools.WindowWithTopRightCornerCloseButton;
import com.embercrest.game.game.Items.Item;
import com.embercrest.game.game.Items.ItemSlot;
import com.embercrest.game.Assets;

import java.util.ArrayList;

public class Inventory extends WindowWithTopRightCornerCloseButton {
    private final ArrayList<ItemSlot> itemSlots = new ArrayList<>();
    private int coins = 0;
    private final Texture background = Assets.TextureAssets.InventoryBG.getTexture();
    public static Label coinsLabel;
    public final Skin skin = Assets.get().getSkin();

    public Inventory() {
        super("", Assets.get().getSkin());

        resize();
        setTouchable(Touchable.enabled);
        setMovable(true);
        setBackground(new TextureRegionDrawable(new TextureRegion(background)));
        setDebug(false);
        coinsLabel = new Label("Coins: "+coins, skin);
        add(coinsLabel).colspan(4);
        row();

        for (int i = 0; i < 28; i++) {
            itemSlots.add(new ItemSlot());
        }

        for (int i = 0; i < 28; i++) {
            add(itemSlots.get(i)).pad(1.5f);
            if((i+1)%4 == 0) row();
        }

        //listener for touchDragging inventory window
        addListener(new InputListener(){
            private final Vector2 dragOffset = new Vector2();
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dragOffset.x = x;
                dragOffset.y = y;
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Vector2 pos = localToScreenCoordinates(new Vector2(x,y));
                setPosition(pos.x-dragOffset.x, -pos.y+Gdx.graphics.getHeight()-dragOffset.y);
            }
        });
    }

    public boolean addItem(Item item) {

        // add item amount to item already in the bag
        for (int i = 0; i < itemSlots.size(); i++) {
            Item iterationItem = itemSlots.get(i).getItem();
            if (iterationItem == null || iterationItem.getItemID() != item.getItemID()) continue;

            //check if there is too much to hold in one stack
            if (iterationItem.getCurrentStackAmount() + item.getCurrentStackAmount() > item.getMaxStackAmount()) {
                int whatLeft = iterationItem.getCurrentStackAmount() + item.getCurrentStackAmount() - item.getMaxStackAmount();
                iterationItem.setCurrentStackAmount(iterationItem.getMaxStackAmount());
                item.setCurrentStackAmount(whatLeft);
            } else {
                // if not then just add stacks
                iterationItem.setCurrentStackAmount(iterationItem.getCurrentStackAmount() + item.getCurrentStackAmount());
                return true;
            }
        }

        ItemSlot itemSlot = getFirstEmptySLot();
        if (itemSlot == null) return false;
        itemSlot.setItem(item);
        return true;
    }

    public ItemSlot getFirstEmptySLot() {
        for (int i = 0; i < itemSlots.size(); i++) {
            if (itemSlots.get(i).getItem() == null) {
                return itemSlots.get(i);
            }
        }
        return null;
    }

    public void addCoins(int amount){
        coins += amount;
    }

    public boolean removeCoins(int amount){
        if(coins-amount < 0) return false;
        coins -= amount;
        return true;
    }

    public ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>(28);
        for(ItemSlot itemSlot : itemSlots){
            if(itemSlot.getItem() != null) {
                items.add(itemSlot.getItem());
            }
        }
        return items;
    }

    public ArrayList<ItemSlot> getInventorySlots() {
        return itemSlots;
    }

    public void resize(){
        //Vector2 resolution = EmberCrest.get().settingsWindow.videoSettings.resolution.getSize();
        float width = background.getWidth();//*resolution.x/ VideoSettings.Resolution.FullHD.getWidth();
        float height = background.getHeight();//*resolution.y/ VideoSettings.Resolution.FullHD.getHeight();
        System.out.println(width+"ADSAD"+height);
        setSize(width, height);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
