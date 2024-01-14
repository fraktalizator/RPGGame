package com.embercrest.game.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.embercrest.game.ashley.componenets.CombatComponent;
import com.embercrest.game.ashley.componenets.LevelComponent;
import com.embercrest.game.ashley.componenets.MoveComponent;
import com.embercrest.game.ashley.componenets.NameComponent;
import com.embercrest.game.game.CharacterClass;
import com.embercrest.game.Assets;

public class Xynia extends PartyCharacter{
    public Xynia(Vector2 position) throws Exception {
        super(position, Assets.TextureAssets.PlayerMovementAnimation.getTexture(), CharacterClass.PlayerClass.Slayer);
        add(new MoveComponent(17, MoveComponent.MoveType.Normal));
        add(new NameComponent("Xynia"));
        add(new LevelComponent());


        getComponent(CombatComponent.class).setVitalityBase(71);
        getComponent(CombatComponent.class).setStrengthBase(13);
        getComponent(CombatComponent.class).setAgilityBase(34);
        getComponent(CombatComponent.class).setIntellectBase(120);

        getComponent(CombatComponent.class).setArmorBase(0);
        getComponent(CombatComponent.class).setResilienceBase(22);
        getComponent(CombatComponent.class).setCritBase(57);
        getComponent(CombatComponent.class).setMasteryBase(157);
    }
}
