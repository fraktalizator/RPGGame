package com.embercrest.game.game.Items.consumables;

import com.badlogic.ashley.core.Entity;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.Assets;

public class Apple extends Consumable{
    public Apple(int amount) {
        super(Assets.TextureAssets.BadApple.getTexture());
        setMaxStackAmount(25);
        if(amount > getMaxStackAmount()) throw new IllegalArgumentException("amount must be less than maxStackAmount");
        setName("apple");
        setItemRarity(ItemRarity.Rare);
        setItemID(1);

        setUpInteractWindow();
        setUpDescriptionWindow();
        getDescriptionWindow().setDescription("Heals unit for 27 HP");
        setCurrentStackAmount(amount);
    }

    @Override
    public void useOnEntity(Entity entity) {
        CombatComponent cbp = entity.getComponent(CombatComponent.class);
        if(cbp == null) throw new IllegalStateException("COMBAT COMP CAN NOT BE NULL HERE");
        cbp.setHP((int) Math.min(cbp.getMaxHP(), cbp.getHP()+ 27));
        this.removeOneAmount();
    }
}
