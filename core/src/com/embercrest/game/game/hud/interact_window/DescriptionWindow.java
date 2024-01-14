package com.embercrest.game.game.hud.interact_window;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.embercrest.game.Assets;
import com.embercrest.game.EmberCrest;
import com.embercrest.game.game.Items.Item;

public class DescriptionWindow extends Window {
    private final TextArea description = new TextArea("", Assets.get().getSkin());

    public DescriptionWindow(String title, Item.ItemRarity rarity) {
        super(title, Assets.get().getSkin());
        getTitleLabel().setColor(rarity.getColor());
        setTouchable(Touchable.disabled);
        setMovable(true);
        setSize(90, 50);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setVisible(false);
    }

    public DescriptionWindow(String title) {
        super(title, Assets.get().getSkin());
        getTitleLabel().setColor(Item.ItemRarity.Common.getColor());
        setTouchable(Touchable.disabled);
        setMovable(true);
        setSize(90, 50);
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

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(String text) {
        description.setText(text);
        clearChildren();
        add(description);
        setSize(description.getWidth()+32, description.getHeight()+32);
    }
}
