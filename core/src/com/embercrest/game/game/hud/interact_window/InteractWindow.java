package com.embercrest.game.game.hud.interact_window;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.game.Items.Item;

public class InteractWindow extends Window {
    public InteractWindow(String title, Item.ItemRarity rarity) {
        super(title, Assets.get().getSkin());
        getTitleLabel().setColor(rarity.getColor());
        setMovable(true);
        setSize(90, 50);
        setTouchable(Touchable.enabled);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setVisible(false);
    }


    public void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
