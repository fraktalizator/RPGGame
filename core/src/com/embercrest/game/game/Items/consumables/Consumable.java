package com.embercrest.game.game.Items.consumables;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.embercrest.game.game.Items.Item;

public abstract class Consumable extends Item {
    public Consumable(Texture texture) {
        super(texture);
    }
}
